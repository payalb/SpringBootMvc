package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.Category;
import com.dto.Product;
import com.exception.DatabaseException;
import com.service.CategoryService;
import com.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/indexList")
	public ModelAndView getIndexList(ModelAndView mv) {
		List<Product> productList = new ArrayList<>();
		productList = productService.getIndexList();
		mv.addObject("productList", productList);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "/productList")
	public ModelAndView getProductList(@RequestParam("categoryName") Optional<String> categoryName, 
			@RequestParam("keyword") Optional<String> keyword, @RequestParam("page") Optional<Integer> page 
			, HttpSession session, ModelAndView mv) throws DatabaseException {
		List<Product> productList = new ArrayList<>();
		if (categoryName.isPresent()) {
			String category = categoryName.orElse("");
			String key = keyword.orElse("");
			List<Category> categoryList = categoryService.getChildCategoriesByName(category);
			if (categoryList == null || categoryList.size() == 0) {
				throw new DatabaseException("Invalid category information.");
			}
			
			if (!page.isPresent()) {
				int searchResult = productService.getProductsListSize(key, categoryList);
				session.setAttribute("totalPage", (searchResult + 4) / 5);
			}
			mv.addObject("keyword", key);
			mv.addObject("categoryName", category);
			mv.addObject("page", page.orElse(1));
			productList = productService.getProductsByKeyword(key, categoryList, page.orElse(1), 5);
		}
		mv.addObject("productList", productList);
		mv.setViewName("productList");
		return mv;
	}
	
	@RequestMapping(value = "/getProduct")
	public ModelAndView getProduct(@RequestParam("id") Integer productId, ModelAndView mv) 
			throws DatabaseException {
		Product product = productService.getProductById(productId);
		if(product == null) {
			throw new DatabaseException("Invalid product information.");
		}
		
		mv.addObject("product", product);
		mv.setViewName("productDetail");
		return mv;
	}
	
}
