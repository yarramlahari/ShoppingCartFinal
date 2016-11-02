package com.niit.ShoppingCart.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ShoppingCart.model.Product;

@Repository("productDAO")
//@Repository it is a annotation that marks the specific class as a data access object
public class ProductDAOImpl implements ProductDAO {
	
	
	@Autowired
	private SessionFactory sessionFactory;//to inject dependency at run time by spring 
	
	// SessionFactory it is not a singleton.it will create the session objects
	
	public ProductDAOImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
		// This keyword to assign the data instance variables from the local variables we are using constructor
	}
@Transactional
public boolean save(Product product){
	try {
		sessionFactory.getCurrentSession().save(product);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
}
@Transactional
public boolean update(Product product){
	try {
		sessionFactory.getCurrentSession().update(product);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
}
	@Transactional
	public boolean create(Product product){
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	/*@Transactional
	public boolean delete(Product product){
		try {
			sessionFactory.getCurrentSession().delete(product);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}*/
	@Transactional
	public void delete(int id) {
		Product ProductToDelete =new Product();
		ProductToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(ProductToDelete);
	}
	
	@Transactional
	public Product  get(int id){
		
		String hql =" from Product where id = "+"'"+id+"'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Product> list = query.list();
		
		if(list == null){
			return null;
	   }else{
		   return list.get(0);
	   }
		
	} 
	@Transactional
	public List<Product>  list(){
		
		String hql = "from Product";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		
	}
	
	
}
