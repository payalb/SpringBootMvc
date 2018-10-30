package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Category;
import com.dto.Product;
import com.exception.DatabaseException;
import com.repository.ProductRepository;

@Service
@Transactional(rollbackFor=DatabaseException.class)
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Product> getProductsByKeyword(String keyword, List<Category> categories, int index, int maxNum) {
		return productRepository.getProductsByKeyword(keyword, categories, index, maxNum);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Product getProductById(Integer productId) throws DatabaseException {
		return productRepository.getProductById(productId);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public int getProductsListSize(String keyword, List<Category> categories) {
		return productRepository.getProductsListSize(keyword, categories);
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Product> getIndexList() {
		return productRepository.getIndexList();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateProduct(Product product) throws DatabaseException {
		productRepository.updateProduct(product);
	}

}
