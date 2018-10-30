package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.Address;
import com.dto.Login;
import com.dto.User;
import com.exception.DatabaseException;
import com.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;

	@RequestMapping(value = "/addressListPage")
	public ModelAndView addressListPage(ModelAndView mv, HttpSession session) {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		List<Address> addressSet = addressService.getAddressByUser(login.getUser());
		if (addressSet == null || addressSet.size() == 0) {
			mv.addObject("errorMsg", "No address records found.");
		}
		mv.addObject("addressSet", addressSet);
		mv.setViewName("addressList");
		return mv;
	}
	
	@RequestMapping(value = "/addressPage")
	public ModelAndView addressPage(ModelAndView mv, HttpSession session) {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		mv.setViewName("addAddress");
		return mv;
	}
	
	@RequestMapping(value = "/addAddress")
	public ModelAndView addAddress(@Valid @ModelAttribute Address address, BindingResult result,
			ModelAndView mv, HttpSession session, HttpServletRequest request) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		if (result.hasErrors()) {
			mv.addObject("errorMsg", result.getAllErrors());
			mv.setViewName("forward:addAddress");
			return mv;
		}
		List<User> users = new ArrayList<>();
		users.add(login.getUser());
		address.setUsers(users);
		addressService.addAddress(address);
		mv.setViewName("redirect:/addressListPage");
		return mv;
	}
	
	@RequestMapping(value = "/editAddressPage")
	public ModelAndView editAddressPage(@RequestParam("id") Integer addressId
			, ModelAndView mv, HttpSession session) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		Address address = addressService.getAddressById(addressId);
		mv.addObject("address", address);
		mv.setViewName("editAddress");
		return mv;
	}
	
	@RequestMapping(value = "/editAddress")
	public ModelAndView editAddress(@Valid @ModelAttribute Address address, BindingResult result
			, ModelAndView mv, HttpSession session) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		if (result.hasErrors()) {
			mv.addObject("errorMsg", result.getAllErrors());
			mv.setViewName("forward:editAddress");
			return mv;
		}
		addressService.updateAddress(address);
		mv.setViewName("redirect:/addressListPage");
		return mv;
	}
	
	@RequestMapping(value = "/deleteAddress")
	public ModelAndView deleteAddress(@RequestParam("id") Integer addressId, ModelAndView mv
			, HttpSession session) throws DatabaseException {
		Login login = (Login) session.getAttribute("login");
		if (login == null || login.getLoginId() == null) {
			mv.setViewName("login");
			return mv;
		}
		addressService.deleteAddressById(addressId);
		mv.setViewName("redirect:/addressListPage");
		return mv;
	}
}
