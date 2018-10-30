package com.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.Category;
import com.exception.DatabaseException;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	@Autowired
	EntityManagerFactory em;
	
	@Override
	public Category getParentCategoryByName(String categoryName) throws DatabaseException {
		
		Session session = em.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		Category category = null;
		try {
			CriteriaQuery<Category> cq = cb.createQuery(Category.class);
			Root<Category> root = cq.from(Category.class);
			cq.where(cb.equal(root.get("categoryName"), categoryName));
			category = session.createQuery(cq).uniqueResult();
			
			if (category == null)
				throw new DatabaseException("Invalid category name.");
		} finally {
			session.close();
		}
		
		return category;
	}

	@Override
	public List<Category> getChildCategoriesByName(String categoryName) throws DatabaseException {
		
		Session session = em.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaBuilder cb2 = session.getCriteriaBuilder();
		List<Category> list = new ArrayList<>();
		try {
			CriteriaQuery<Category> cq1 = cb1.createQuery(Category.class);
			Root<Category> root1 = cq1.from(Category.class);
			cq1.where(cb1.equal(root1.get("categoryName"), categoryName));
			Category category = session.createQuery(cq1).uniqueResult();
			if (category == null) {
				throw new DatabaseException("Invalid category name.");
			}
			
			CriteriaQuery<Category> cq2 = cb2.createQuery(Category.class);
			Root<Category> root2 = cq2.from(Category.class);
			cq2.where(cb2.equal(root2.get("parentId"), category.getCategoryId()));
			list = session.createQuery(cq2).list();
			if (list.size() == 0 && category != null)
				list.add(category);
		} finally {
			session.close();
		}
		
		return list;
	}

}
