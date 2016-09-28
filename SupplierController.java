 package com.niit.ShoppingCart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.niit.ShoppingCart.DAO.SupplierDAO;
import com.niit.ShoppingCart.model.Supplier;


@Controller
public class SupplierController {

	@Autowired
	SupplierDAO sd;

	@ModelAttribute("Supplier")
	public Supplier supplier() {
		return new Supplier();
	}

	
	@RequestMapping("/Managesuppliers")
	public ModelAndView display6() {
		ModelAndView m4 = new ModelAndView("ManageSuppliers");
		return m4;
	}
	@RequestMapping(value = "listsup", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList()
	{
		List list = sd.list();
		Gson x = new Gson();
		String json = x.toJson(list);
		return json;
	}
	@RequestMapping("/viewsuppliers")
	public ModelAndView display8() {
		ModelAndView m4 = new ModelAndView("viewsuppliers");
		return m4;
	}
	
	@RequestMapping(value = "viewsupplier", method = RequestMethod.GET)
	public ModelAndView viewSup(@RequestParam int id, @ModelAttribute Supplier suppliers) {
		Supplier supplier = sd.get(id);
		return new ModelAndView("viewsupplier", "supplier", supplier);
	}
	 @RequestMapping("/editsupplier")
		public ModelAndView display15() {
			ModelAndView m6 = new ModelAndView("editsupplier");
			return m6;

		}		 		 
	 @RequestMapping(value="editsupplier", method=RequestMethod.GET)
	    public ModelAndView editSup(@RequestParam int id){
		 Supplier supplier1=sd.get(id);
	    	return new ModelAndView("editsupplier","Supplier",supplier1);
	    }
	 
	 @RequestMapping(value="updatesupplier",method=RequestMethod.POST)
	    public ModelAndView updateSupplier(HttpServletRequest request,@Valid @ModelAttribute("Supplier")Supplier editsup,BindingResult result,Model model)
	    {
			sd.update(editsup);
			return new ModelAndView("ManageSuppliers");
	    }	 


	 @RequestMapping("/deletesupplier")
		public ModelAndView deleteSupplier(@RequestParam int id) {
			sd.delete(id);
			ModelAndView m1 = new ModelAndView("ManageSuppliers");
			return m1;
		}
		
}