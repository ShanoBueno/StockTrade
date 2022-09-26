package com.example;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

	@Autowired
	UserRepo repo;
	
	Logger log = Logger.getAnonymousLogger();
	
	@RequestMapping("/")
	public ModelAndView loadpage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =  new ModelAndView();
		mv.setViewName("login.jsp");
		return mv;
	}
	
	
	@RequestMapping("/login")
	public ModelAndView loginlogic(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv =  new ModelAndView();
		log.info("entered into the login request mapping");
		
		String username = request.getParameter("user");
		String password=request.getParameter("pwd");
		log.info("got the data from the front page");
		
		if(repo.findbyuser(username)!=null&&repo.findbypassword(password)!=null) {
			
			if(repo.findbyuser(username).equals(repo.findbypassword(password))) 
			{
				log.info("login is success");
	
				mv.setViewName("display.jsp");
				mv.addObject("userid",username);
				
				log.info("control passed to display.jsp");
				
			}
			else
			{
					
				
			mv.setViewName("fail.jsp");
			log.info("control pass to fail.jsp");
				
			}
		}
		else
		{
				
			
		mv.setViewName("fail.jsp");
		log.info("control pass to fail.jsp");
			
		}
		
		return mv;
	}
	
RestTemplate temp=new RestTemplate();
	
	@ResponseBody
	@RequestMapping("/registermsconfig")
	public ModelAndView registermsconfig(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mv=new ModelAndView();
		String user=request.getParameter("username");
		String password=request.getParameter("password");
		String firstname=request.getParameter("fname");
		String lastname=request.getParameter("lname");
		log.info("inside register ms and data set");
		
		String url="http://localhost:8081/register-user/"+firstname+"/"+lastname+"/"+user+"/"+password;
		temp.getForObject(url, String.class);
		log.info("control went to register ms");
		mv.setViewName("DoneRegistration.jsp");
		return mv;
	}
	
	
}
