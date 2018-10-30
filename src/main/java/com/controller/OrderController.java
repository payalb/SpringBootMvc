package com.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.Address;
import com.dto.Card;
import com.dto.CartItem;
import com.dto.Login;
import com.dto.Order;
import com.dto.User;
import com.exception.DatabaseException;
import com.service.AddressService;
import com.service.CardService;
import com.service.CartItemService;
import com.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CardService cardService;
	@Autowired
	private CartItemService cartService;
	
	@RequestMapping(value="/checkoutPage")
	public ModelAndView checkoutPage(HttpSession session, ModelAndView mv) {
		Login login = (Login)session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		User user = (User)session.getAttribute("user");
		List<Address> addressList = addressService.getAddressByUser(user);
		if (addressList == null || addressList.size() == 0) {
			mv.addObject("errorMsg", "You need to add an address.");
			mv.setViewName("redirect:addressListPage");
			return mv;
		}
		
		List<Card> cardList = cardService.getCardsByUser(user);
		if (cardList == null || cardList.size() == 0) {
			mv.addObject("errorMsg", "You need to add a card.");
			mv.setViewName("redirect:cardListPage");
			return mv;
		}
		
		List<CartItem> cartList = cartService.getCartItemByUser(user);
		mv.addObject("cardList", cardList);
		mv.addObject("addressList", addressList);
		mv.addObject("cartList", cartList);
		mv.setViewName("checkout");
		return mv;
	}
	
	@RequestMapping(value="/checkout")
	public ModelAndView checkout(@RequestParam("addId") Optional<Integer> addId
			, @RequestParam("cardId") Optional<Integer> cardId, HttpSession session
			, ModelAndView mv) throws DatabaseException {
		if (addId == null || cardId == null) {
			mv.addObject("errorMsg", "Invalid address or card information.");
			mv.setViewName("checkoutPage");
			return mv;
		}
		
		Login login = (Login)session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		
		User user = login.getUser();
//		Address address = addressService.getAddressById(addId.orElse(0));
//		Card card = cardService.getCardById(cardId.orElse(0));
//		List<CartItem> items = cartService.getCartItemByUser(user);
//		Map<Product, Integer> products = new HashMap<>();
//		int amount = 0;
//		for (int i = 0; i < items.size(); i++) {
//			amount += items.get(i).getAmount();
//			Product product = items.get(i).getProduct();
//			if (product.getStock() - items.get(i).getAmount() < 0) {
//				throw new DatabaseException("We don't have enough stock.");
//			}
//			product.setStock(product.getStock() - items.get(i).getAmount());
//			productService.updateProduct(product);
//			products.put(product, items.get(i).getAmount());
//		}
//		
//		Order order = new Order(user, address, card, products, new Date(new java.util.Date().getTime())
//				, amount, OrderStatus.ACTIVE);
//		orderService.addOrder(order);
//		cartService.deleteCartItemByUser(user);
		
		orderService.checkOut(user, addId.orElse(0), cardId.orElse(0));
		mv.addObject("successMsg", "Your order has been placed.");
		mv.setViewName("success");
		return mv;
	}
	
	@RequestMapping(value="/orderHistory")
	public ModelAndView orderHistory(HttpSession session, ModelAndView mv) {
		Login login = (Login)session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		User user = login.getUser();
		List<Order> orders = orderService.getOrdersByUser(user);
		mv.addObject("orderList", orders);
		mv.setViewName("orderList");
		return mv;
	}
}
