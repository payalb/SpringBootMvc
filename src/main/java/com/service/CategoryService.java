package com.service;

import java.util.List;

import com.dto.Category;
import com.exception.DatabaseException;

public interface CategoryService {

public Category getParentCategoryByName(String categoryName) throws DatabaseException;
	
	public List<Category> getChildCategoriesByName(String categoryName) throws DatabaseException;
}
