package com.niit.ShoppingCart.DAO;

import java.util.List;

import com.niit.ShoppingCart.model.Login;

public interface LoginDAO {
	//it is an interface class we will do CRUD operations through this ...we just write the methods they are not implemented 
	


	
	public List<Login> list();//it will get the list of login users

	
	public Login get(int id);
	
	public Login getSingleUser(int id);
	
	public void save(Login user);
    //we can save and update the particular user through this methods

	
	public void update(Login user);
	
	public List getAllUsers();
	
	public void delete(int id);//to delete an particular user
}


