package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class BidController {
	@Autowired
	BidRepo br; 
	
	@Autowired
	BidDAO bd; 
	
	@Autowired 
	AskRepo ar;
	
	@Autowired
	AskDAO ad; 
	
	
	@RequestMapping("/addBidform")
	public ModelAndView addAskForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("NewBid.jsp"); 
		return mv; 
	}

	
	@RequestMapping("/addBidsubmit")
	public ModelAndView AskInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(); 
		Bid b = new Bid(); 
		
		b.setSymbol(request.getParameter("symbol")); 
		b.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		b.setPrice(Double.parseDouble(request.getParameter("price"))); 
		
	
		
	
	
		bd.executeOrder(b); 
		mv.setViewName("display.jsp"); 
		return mv; 
	}
	
	@GetMapping("/allbids")
	public ModelAndView get(Model model) {
		ModelAndView mv = new ModelAndView(); 
		List<Bid> Bidlist = new ArrayList<Bid>(bd.getall()); 
		System.out.println(Bidlist);
		mv.addObject("bidlist", Bidlist);
		mv.setViewName("Bids.jsp"); 
		return mv;  
		
	}
	

}
