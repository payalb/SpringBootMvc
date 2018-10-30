package com.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CartItem;
import com.dto.Login;
import com.dto.Product;
import com.dto.User;
import com.exception.DatabaseException;
import com.service.CartItemService;
import com.service.ProductService;
import com.util.FormatUtil;


@Controller
public class CartItemController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CartItemService cartItemService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getCartItem")
	public ModelAndView getCart(HttpSession session, ModelAndView mv) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		List<CartItem> itemList = null;
		if (login == null || login.getLoginId() == null) {
			Object obj = session.getAttribute("cart");
			if (obj != null) {
				itemList = (List<CartItem>) obj;
			} else {
				itemList = new LinkedList<>();
			}
		} else {
			User user = (User) session.getAttribute("user");
			itemList = cartItemService.getCartItemByUser(user);
			if (itemList == null) itemList = new LinkedList<>();
		}
		mv.addObject("cartList", itemList);
		mv.setViewName("cartList");
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addCartItem")
	public ModelAndView addCart(@RequestParam("id") Integer productId, @RequestParam("quantity") Optional<String> quantity
			, HttpSession session, ModelAndView mv) throws DatabaseException {
		Product product = productService.getProductById(productId);
		if (product == null || productId == null || !quantity.isPresent() 
				|| (quantity.isPresent() && !FormatUtil.strIsInteger(quantity.get()))) {
			throw new DatabaseException("Invalid input information.");
		}
		Integer amount = Integer.parseInt(quantity.get());
		
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			Object obj = session.getAttribute("cart");
			List<CartItem> itemList = new ArrayList<>();
			boolean exist = false;
			if (obj != null) {
				itemList = (List<CartItem>) obj;
				for (CartItem item : itemList) {
					if (productId == item.getProduct().getProductId()) {
						item.setAmount(item.getAmount() + amount);
						exist = true;
						break;
					}
				}
			}
			if (obj == null || !exist) {
				CartItem item = new CartItem();
				item.setProduct(product);
				item.setAmount(amount);
				itemList.add(item);
			}
			session.setAttribute("cart", itemList);
		} else {
			User user = (User) session.getAttribute("user");
			List<CartItem> itemList = cartItemService.getCartItemByUser(user);
			boolean exist = false;
			for (CartItem item : itemList) {
				if (productId == item.getProduct().getProductId()) {
					item.setAmount(item.getAmount() + amount);
					exist = true;
					cartItemService.updateCartItem(item);
					break;
				}
			}
			if (!exist) {
				CartItem item = new CartItem();
				item.setProduct(product);
				item.setUser(user);
				item.setAmount(amount);
				cartItemService.addCartItem(item);
			}
		}
		mv.setViewName("redirect:getCartItem");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateCartItem")
	public ModelAndView updateCart(@RequestParam("id") Integer productId, @RequestParam("quantity") Optional<String> quantity
			, HttpSession session, ModelAndView mv) throws DatabaseException {

		Product product = productService.getProductById(productId);
		if (product == null || productId == null || !quantity.isPresent() 
				|| (quantity.isPresent() && !FormatUtil.strIsInteger(quantity.get()))) {
			throw new DatabaseException("Invalid input information.");
		}
		Integer amount = Integer.parseInt(quantity.get());
		if (amount <= 0 || amount > product.getStock()) {
			mv.setViewName("forward:getCartItem");
			mv.addObject("errorMsg", "Invalid quantity information.");
			return mv;
		}
		
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			Object obj = session.getAttribute("cart");
			List<CartItem> itemList = new ArrayList<>();
			boolean exist = false;
			if (obj != null) {
				itemList = (List<CartItem>) obj;
				Iterator<CartItem> iter = itemList.iterator();
				while(iter.hasNext()) {
					CartItem item = iter.next();
					if (productId == item.getProduct().getProductId()) {
						item.setAmount(amount);
						exist = true;
						break;
					}
				}
			}
			if (obj == null || !exist) {
				throw new DatabaseException("Invalid cart information.");
			}
			session.setAttribute("cart", itemList);
		} else {
			User user = (User) session.getAttribute("user");
			List<CartItem> itemList = cartItemService.getCartItemByUser(user);
			boolean exist = false;
			for (CartItem item : itemList) {
				if (productId == item.getProduct().getProductId()) {
					if (amount == 0) {
						cartItemService.deleteCartItemById(productId);
					} else {
						item.setAmount(amount);
						cartItemService.updateCartItem(item);
					}
					exist = true;
					break;
				}
			}
			if (!exist) {
				throw new DatabaseException("Invalid cart information.");
			}
		}
		mv.setViewName("redirect:getCartItem");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteCartItem")
	public ModelAndView deleteCart(@RequestParam("id") Integer productId, HttpSession session
			, ModelAndView mv) throws DatabaseException {

		Product product = productService.getProductById(productId);
		if (product == null || productId == null) {
			throw new DatabaseException("Invalid product information.");
		}
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			Object obj = session.getAttribute("cart");
			List<CartItem> itemList = new ArrayList<>();
			boolean exist = false;
			if (obj != null) {
				itemList = (List<CartItem>) obj;
				Iterator<CartItem> iter = itemList.iterator();
				while (iter.hasNext()) {
					if (productId == iter.next().getProduct().getProductId()) {
						iter.remove();
						exist = true;
						break;
					}
				}
			}
			if (obj == null && !exist) {
				throw new DatabaseException("Invalid cart information.");
			}
			session.setAttribute("cart", itemList);
		} else {
			User user = (User) session.getAttribute("user");
			List<CartItem> itemList = cartItemService.getCartItemByUser(user);
			boolean exist = false;
			for (CartItem item : itemList) {
				if (productId == item.getProduct().getProductId()) {
					cartItemService.deleteCartItemById(item.getCartItemId());
					exist = true;
					break;
				}
			}
			if (!exist) {
				throw new DatabaseException("Invalid cart information.");
			}
		}
		mv.setViewName("redirect:getCartItem");
		return mv;
	}
}
