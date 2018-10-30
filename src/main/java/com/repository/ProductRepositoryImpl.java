package com.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dto.Category;
import com.dto.Product;
import com.dto.Search;
import com.exception.DatabaseException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DatabaseException.class)
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate template;

	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public Product getProductById(int productId) throws DatabaseException {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		Product product = null;
		try {
			CriteriaQuery<Product> cq = cb.createQuery(Product.class);
			Root<Product> root = cq.from(Product.class);
			cq.where(cb.equal(root.get("productId"), productId));
			product = session.createQuery(cq).uniqueResult();
			if (product == null)
				throw new DatabaseException("Unable to get product information.");
		} finally {
			session.close();
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Product> getProductsByKeyword(String keyword, List<Category> categories, int index, int maxNum) {
		Session session = sessionFactory.openSession();
		List<Product> products = new ArrayList<>();
		String hql = null;
		Query<Search> query = null;

		try {
			if (keyword == null || keyword.trim().length() == 0) {
				hql = "from search s where s.category in (:ids)";
				query = session.createQuery(hql);
				query.setParameterList("ids", categories);
			} else {
				hql = "from search s where (";
				String[] keywords = keyword.split("\\s+");
				for (int i = 0; i < keywords.length; i++) {
					hql += "lower(s.keywords) like lower(:keyword" + i + ")";
					if (i != keywords.length - 1)
						hql += " or";
				}
				hql += ") and s.category in (:ids)";
				System.out.println(hql);
				query = session.createQuery(hql);
				for (int i = 0; i < keywords.length; i++) {
					query.setParameter("keyword" + i, "%" + keywords[i] + "%");
				}
				query.setParameterList("ids", categories);
			}
			query.setFirstResult((index - 1) * maxNum);
			query.setMaxResults(maxNum);
			List<Search> searches = query.list();
			for (Search search : searches) {
				products.add(search.getProduct());
			}
		} finally {
			session.close();
		}

		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public int getProductsListSize(String keyword, List<Category> categories) {
		Session session = sessionFactory.openSession();
		String hql = null;
		Query<Search> query = null;
		int size = 0;
		try {
			if (keyword == null || keyword.trim().length() == 0) {
				hql = "from search s where s.category in (:ids)";
				query = session.createQuery(hql);
				query.setParameterList("ids", categories);
			} else {
				hql = "from search s where (";
				String[] keywords = keyword.split("\\s+");
				for (int i = 0; i < keywords.length; i++) {
					hql += "lower(s.keywords) like lower(:keyword" + i + ")";
					if (i != keywords.length - 1)
						hql += " or";
				}
				hql += ") and s.category in (:ids)";
				System.out.println(hql);
				query = session.createQuery(hql);
				for (int i = 0; i < keywords.length; i++) {
					query.setParameter("keyword" + i, "%" + keywords[i] + "%");
				}
				query.setParameterList("ids", categories);
			}
			size = query.list().size();
		} finally {
			session.close();
		}

		return size;
	}

	@Override
	public void updateProduct(Product product) throws DatabaseException {
//		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		try {
//			session.update(product);
//			transaction.commit();
//		} catch (HibernateException e) {
//			throw new DatabaseException("Unable to update product information.");
//		} finally {
//			session.close();
//		}
		template.update(product);
	}
	
	@Override
	public List<Product> updateProducts(Map<Integer, Integer> cartItems) throws DatabaseException {
		Iterator<Map.Entry<Integer, Integer>> iterator = cartItems.entrySet().iterator();
		List<Product> productList = new ArrayList<>(); 
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            Product product = getProductById(entry.getKey());
            if (product.getStock() < entry.getValue()) {
            	throw new DatabaseException("Not enough stock.");
            }
			product.setStock(product.getStock() - entry.getValue());
			productList.add(product);
        }
        return productList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
	public List<Product> getIndexList() {
		Session session = sessionFactory.openSession();
		String hql = "from product ORDER BY productId desc";
		List<Product> products = new ArrayList<>();
		try {
			Query<Product> query = session.createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(4);
			products = query.list();
		} finally {
			session.close();
		}
		return products;
	}

}
