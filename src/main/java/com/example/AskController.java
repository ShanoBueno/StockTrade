package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class AskController {
	@Autowired
	AskRepo ar; 
	
	@Autowired
	AskDAO ad; 
	
	@RequestMapping("/addAskform")
	public ModelAndView addAskForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("NewAsk.jsp"); 
		return mv; 
	}
	
	@RequestMapping("/addAsksubmit")
	public ModelAndView AskInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(); 
		Ask a = new Ask(); 
		
		a.setSymbol(request.getParameter("symbol")); 
		a.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		a.setPrice(Double.parseDouble(request.getParameter("price"))); 
		
		ar.save(a);
		
		mv.setViewName("display.jsp"); 
		return mv; 
	}
	
	@GetMapping("/allasks")
	public ModelAndView get(Model model) {
		ModelAndView mv = new ModelAndView(); 
		List<Ask> Asklist = new ArrayList<Ask>(ad.getall()); 
		System.out.println(Asklist);
		mv.addObject("asklist", Asklist);
		mv.setViewName("Asks.jsp"); 
		return mv;  
		
		
	}
	

}
