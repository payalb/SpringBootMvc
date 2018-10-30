package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.Login;
import com.dto.User;
import com.service.LoginService;

@Controller
public class UserController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/profile")
	public ModelAndView profilePage(HttpSession session, ModelAndView mv) {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		mv.setViewName("profile");
		mv.addObject("login", login);
		mv.addObject("user", login.getUser());
		return mv;
	}
	
	@RequestMapping(value="/profile.do")
	public ModelAndView updateProfile(@Validated(Login.RegisterValidation.class) @ModelAttribute("login") Login login
			, @ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView mv, HttpSession session) {
		
		login.setUser(user);
		login = loginService.updateLogin(login);
		session.setAttribute("login", login);
		
		mv.setViewName("redirect:/");
		return mv;
	}
}
