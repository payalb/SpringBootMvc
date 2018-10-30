package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Category;
import com.exception.DatabaseException;
import com.repository.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category getParentCategoryByName(String categoryName) throws DatabaseException {
		return categoryRepository.getParentCategoryByName(categoryName);
	}

	@Override
	public List<Category> getChildCategoriesByName(String categoryName) throws DatabaseException {
		return categoryRepository.getChildCategoriesByName(categoryName);
	}

}
