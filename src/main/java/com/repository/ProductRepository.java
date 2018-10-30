package com.repository;

import java.util.List;
import java.util.Map;

import com.dto.Category;
import com.dto.Product;
import com.exception.DatabaseException;

public interface ProductRepository {

	List<Product> getProductsByKeyword(String keyword, List<Category> categories, int index, int maxNum);

	Product getProductById(int productId) throws DatabaseException;
	
	int getProductsListSize(String keyword, List<Category> categories);
	
	void updateProduct(Product product) throws DatabaseException;
	
	List<Product> updateProducts(Map<Integer, Integer> cartItems) throws DatabaseException;
	
	List<Product> getIndexList();
}
