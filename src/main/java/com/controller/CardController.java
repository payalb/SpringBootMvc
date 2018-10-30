package com.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.Card;
import com.dto.Login;
import com.exception.DatabaseException;
import com.service.CardService;
import com.util.DateUtil;

@Controller
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "expiration", new DateUtil());
	}

	@RequestMapping(value = "/cardListPage")
	public ModelAndView cardListPage(ModelAndView mv, HttpSession session) {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		List<Card> cartList = cardService.getCardsByUser(login.getUser());
		if (cartList == null || cartList.size() == 0) {
			mv.addObject("errorMsg", "No card records found.");
		}
		mv.addObject("cardSet", cartList);
		mv.setViewName("cardList");
		return mv;
	}
	
	@RequestMapping(value = "/cardPage")
	public ModelAndView cardPage(ModelAndView mv, HttpSession session) {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		mv.setViewName("addCard");
		return mv;
	}
	
	@RequestMapping(value = "/addCard")
	public ModelAndView addCard(@Valid @ModelAttribute Card card, BindingResult result, ModelAndView mv
			, HttpSession session, HttpServletRequest request) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		if (result.hasErrors()) {
			mv.addObject("errorMsg", result.getAllErrors());
			mv.setViewName("forward:cardPage");
			return mv;
		}
		card.setUser(login.getUser());
		cardService.addCard(card);
		mv.setViewName("redirect:/cardListPage");
		return mv;
	}
	
	@RequestMapping(value = "/editCardPage")
	public ModelAndView editCardPage(@RequestParam("id") Integer cardId
			, ModelAndView mv, HttpSession session) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		Card card = cardService.getCardById(cardId);
		mv.addObject("card", card);
		mv.setViewName("editCard");
		return mv;
	}
	
	@RequestMapping(value = "/editCard")
	public ModelAndView editCard(@Valid @ModelAttribute Card card, BindingResult result,
			ModelAndView mv, HttpSession session) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		if (result.hasErrors()) {
			mv.addObject("errorMsg", result.getAllErrors());
			mv.setViewName("forward:editCardPage");
			return mv;
		}
		cardService.updateCard(card);
		mv.setViewName("redirect:/cardListPage");
		return mv;
	}
	
	@RequestMapping(value = "/deleteCard")
	public ModelAndView deleteCard(@RequestParam("id") Integer cardId, ModelAndView mv
			, HttpSession session) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		cardService.deleteCardById(cardId);
		mv.setViewName("redirect:/cardListPage");
		return mv;
	}
}
