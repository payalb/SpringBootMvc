package com.service;

import java.util.List;

import com.dto.Category;
import com.dto.Product;
import com.exception.DatabaseException;

public interface ProductService {
	
	public Product getProductById(Integer productId) throws DatabaseException;

	public List<Product> getProductsByKeyword(String keyword, List<Category> categories, int index, int maxNum);
	
	int getProductsListSize(String keyword, List<Category> categories);

	void updateProduct(Product product) throws DatabaseException;
	
	List<Product> getIndexList();
}
