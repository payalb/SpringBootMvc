package com.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CartItem;
import com.dto.Login;
import com.dto.User;
import com.dto.UserRole;
import com.exception.DatabaseException;
import com.service.CartItemService;
import com.service.LoginService;

@Controller
public class LoginRegisterController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private CartItemService cartItemService;

	@RequestMapping(value = "/login")
	public ModelAndView loginPage(ModelAndView mv, HttpSession session) {
		Login login = (Login) session.getAttribute("login");
		if (login != null && login.getLoginId() != null) {
			mv.setViewName("index");
			return mv;
		}
		mv.setViewName("login");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userLogin.do")
	public ModelAndView login(@Validated(Login.LoginValidation.class) @ModelAttribute("user") Login login,
			BindingResult bindingResult, HttpSession session, ModelAndView mv) throws DatabaseException {

		if (bindingResult.hasErrors() || login == null) {
			mv.addObject("invalid", "Invalid email or password.");
			mv.setViewName("login");
			return mv;
		}
		login.setRole(UserRole.USER);
		login = loginService.getLogin(login);

		if (login == null) {
			mv.addObject("invalid", "Invalid email or password.");
			mv.setViewName("login");
			return mv;
		}
		Object obj = session.getAttribute("cart");
		if (obj != null) {
			List<CartItem> cartList = (List<CartItem>) obj;
			for (CartItem cartItem : cartList) {
				cartItem.setUser(login.getUser());
				int existItem = cartItemService.haveCartItem(cartItem);
				if (existItem > 0) {
					cartItem.setAmount(existItem + cartItem.getAmount());
					cartItemService.updateCartItem(cartItem);
				} else {
					cartItemService.addCartItem(cartItem);
				}
			}
			session.removeAttribute("cart");
		}
		session.setAttribute("login", login);
		session.setAttribute("user", login.getUser());
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@RequestMapping(value="/existEmail")
	@ResponseBody
	public String existEmail(@RequestParam("email") Optional<String> email
			, ModelAndView mv) throws DatabaseException {
		if (!email.isPresent() || email.get() == null || email.get().trim() == "")
			throw new DatabaseException("Invalid email information.");
		if (loginService.existEmail(email.get())) {
			return "true";
		}
		return "false";
	} 

	@RequestMapping(value = "/userLogout.do")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		Login login = (Login) session.getAttribute("login");
		if (login != null && login.getLoginId() != null) {
			session.removeAttribute("login");
			session.removeAttribute("user");
		}
		mv.setViewName("redirect:/");
		return mv;
	}

	@RequestMapping(value = "/register")
	public ModelAndView registerPage(HttpSession session, ModelAndView mv) {
		Login login = (Login) session.getAttribute("login");
		if (login != null && login.getLoginId() != null) {
			mv.setViewName("index");
			return mv;
		}
		mv.setViewName("register");
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/register.do")
	public ModelAndView register(@Validated(Login.RegisterValidation.class) @ModelAttribute("user") Login login,
			BindingResult bindingResult, ModelAndView mv, HttpSession session) throws DatabaseException {
		if (bindingResult.hasErrors() || login == null) {
			mv.addObject("invalid", "Invalid email or password.");
			mv.setViewName("register");
			return mv;
		}
		login.setRole(UserRole.USER);
		if (loginService.existEmail(login.getEmail())) {
			mv.addObject("invalid", "Email has been registered.");
			mv.setViewName("register");
			return mv;
		}
		User user = new User();
		login.setUser(user);
		login = loginService.addLogin(login);
		
		Object obj = session.getAttribute("cart");
		if (obj != null) {
			List<CartItem> cartList = (List<CartItem>) obj;
			for (CartItem cartItem : cartList) {
				cartItem.setUser(user);
				int existItem = cartItemService.haveCartItem(cartItem);
				if (existItem > 0) {
					cartItem.setAmount(existItem + cartItem.getAmount());
					cartItemService.updateCartItem(cartItem);
				} else {
					cartItemService.addCartItem(cartItem);
				}
			}
			session.removeAttribute("cart");
		}
		
		session.setAttribute("login", login);
		session.setAttribute("user", login.getUser());
		mv.setViewName("redirect:/");
		return mv;
	}

}
