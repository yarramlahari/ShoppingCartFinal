package com.niit.ShoppingCart.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.DAO.LoginDAO;
import com.niit.ShoppingCart.DAO.SupplierDAO;
import com.niit.ShoppingCart.DAO.UserDetailsDAO;
import com.niit.ShoppingCart.model.Login;
import com.niit.ShoppingCart.model.Supplier;
import com.niit.ShoppingCart.model.UserDetails;


@Controller
//it is used to denote the class is controller.it contains method which serve the http requests,request mappings are done at metod or class level
public class HomeController 
{
	
	@Autowired
	//to inject dependency at runtime by spring
	UserDetailsDAO ud;//reference variable is created for UserDetailsDAO class
	@Autowired
	SupplierDAO sd;
	@Autowired
	LoginDAO ld;
	
	
	@RequestMapping("Register")//used to map web request url.it is applied on the specific handler methods or classes
	
	public ModelAndView regi()
	{
		ModelAndView m1=new ModelAndView("Register");//used to pass the respected jsp pages
		return m1;//it returns the object
	}

	@RequestMapping(value={"/","/Home"})
	public ModelAndView home()
	{
		ModelAndView m1=new ModelAndView("Home");
		return m1;
		
	}
		@RequestMapping("/Contactus")
	public ModelAndView Contactuspage()
	{
		ModelAndView mv6=new ModelAndView("Contactus");
		return mv6;
	}
	
	@RequestMapping("Login")
	public ModelAndView Login()
	{
		ModelAndView m1=new ModelAndView("Login");
		return m1;
	}
	
	 @ModelAttribute("Login")//to provide reference  data for the model
	    public Login createuser(){
	    	return new Login();
	    }

	@RequestMapping("/addsupplier")
	public ModelAndView display3() {

		ModelAndView mv3 = new ModelAndView("addsupplier");
		return mv3;
	}
	@RequestMapping("storesupplier")
	public String addsupplier(HttpServletRequest request, @Valid @ModelAttribute("Supplier") Supplier supplier,
			BindingResult result) {
		if (result.hasErrors()) {
			//form validation error
			return "addsupplier";
		}
		sd.save(supplier);//save means it save the object of Supplier
		return "addsupplier";

	}

	
	@ModelAttribute("UserDetails")
	public UserDetails registerUser() {
		return new UserDetails();

	}
	@ModelAttribute("Supplier")
	public Supplier lahari(){
		return new  Supplier();

	}
	
	@RequestMapping(value = "storeUser", method = RequestMethod.POST)
	//value="path"
	public String addUser(@Valid @ModelAttribute("UserDetails") UserDetails registeruser,BindingResult result) {
		//@valid means is common to validate a model after binding a user input to it
		//binding result is used to holds the result  of the validation and binding and contains errors that may have occurred
		if (result.hasErrors()) {
			 // Register failed so return to the Register form with errors
			System.out.println("Errors");
			return "Register";
		}
		System.out.println(registeruser.getUsername());//if no errors then user details will be saved
		ud.save(registeruser);
		Login loginuser = new Login();//login object is created
		loginuser.setId(registeruser.getId());//values are set and retrieved
		loginuser.setUsername(registeruser.getUsername());
		loginuser.setPassword(registeruser.getPassword());
		loginuser.setStatus(registeruser.isStatus());
		ld.save(loginuser);
		System.out.println("REGISTRATION FINISHED SUCCESSFULLY");
		return "Home";
	}
	
    
    @RequestMapping("/checkuser")
    public ModelAndView checkedUser(@Valid @ModelAttribute("Login")Login user,BindingResult result,@RequestParam("userName") String userName,@RequestParam("password")String password) 
    //@RequestParam is used to specifies the particular parameter 
    { 
    	System.out.println("UserName is............."+userName);
    	System.out.println("Password is............."+password);
		return null;
    	
    }
    
    @RequestMapping("/Admin")
    public ModelAndView wish()
    {
    	return new ModelAndView("Admin");
    }
       @RequestMapping("logoutsuccess")
	public ModelAndView logoutpage(){
		ModelAndView mv9 = new ModelAndView("logoutsuccess");
		return mv9;
	}

	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		// doGet() and doPost() methods throw javax.servlet.ServletException and IOException, 
		HttpSession newsession = request.getSession(false);
		//session is used to create the objects
		if (newsession != null) 
			 // Not created yet.
	    {
	         newsession.invalidate();

	    }
		response.sendRedirect("j_spring_security_logout");	
		}
}


	
