package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class BidDAO {
	@Autowired
	BidRepo br; 

	@Autowired
	AskRepo ar; 
	@Autowired
	AskDAO ad; 

	public Bid insert(Bid b) {
		return br.save(b);
	}
	public List<Bid> getall(){
		return br.findAll() ;
	}
	
	public void executeOrder(Bid b) {
		
		List<Ask> AskList = ar.findbyprice(b.getPrice(),b.getSymbol());
		AskList.sort((o1, o2)-> o1.getPrice().compareTo(o2.getPrice()));
		System.out.println(AskList); 

		
		if (AskList.size() != 0 ) {
		for (Ask aa:AskList) {
			if (b.getPrice() >= aa.getPrice() && b.getQuantity() > aa.getQuantity()){
				b.setQuantity(b.getQuantity() - aa.getQuantity()); 
				ar.delete(aa); 
				executeOrder(b); 
				
			
			}
			
			else if (b.getPrice() >= aa.getPrice()&& b.getQuantity() == aa.getQuantity()) {
				ar.delete(aa);
				break; 
			} else if (b.getPrice()>= aa.getPrice() && b.getQuantity() < aa.getQuantity()){
				aa.setQuantity(aa.getQuantity()-b.getQuantity());
				ad.insert(aa); 
				break; 
			}
	
			
		}
		}
		else {
			br.save(b); 
		}
	}
}