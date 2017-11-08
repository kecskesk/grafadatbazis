package hu.javafw.menetrend2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hu.javafw.menetrend2.dao.UserDAO;
import hu.javafw.menetrend2.model.User;

/**
 * Controller to the user manager and new/edit user pages. 
 * 
 * @author kkrisz
 */
@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/user")
	public ModelAndView listUser(ModelAndView model) throws IOException{
		List<User> listUser = userDAO.list();
		model.addObject("listUser", listUser);
		model.setViewName("user");
		
		return model;
	}
	
	@RequestMapping(value = "/user/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView model) {
		User newUser = new User();
		model.addObject("user", newUser);
		model.setViewName("UserForm");
		return model;
	}
	
	@RequestMapping(value = "/user/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) {
		userDAO.saveOrUpdate(user);		
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/user/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDAO.delete(userId);
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/user/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDAO.get(userId);
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", user);
		
		return model;
	}
}
