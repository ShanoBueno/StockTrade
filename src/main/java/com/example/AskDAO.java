package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class AskDAO {
	@Autowired
	AskRepo ar; 
	@Autowired
	BidRepo br; 

	public Ask insert(Ask a) {
		return ar.save(a);
	}
	public List<Ask> getall(){
		return ar.findAll() ;
	}
	
	public void executeAskOrder(Ask a) {
		
		List <Bid> BidList = br.findbybidsprice(a.getPrice(), a.getSymbol());
		//BidList.sort((o1, o2)-> o1.getPrice().compareTo(o2.getPrice()));
		
		int i = 0; 
		
		if (BidList.size() != 0) {
			while(a.getQuantity() > 0 ) {
				if (a.getPrice() <= BidList.get(i).getPrice() && a.getQuantity() < BidList.get(i).getQuantity()) {
					BidList.get(i).setQuantity(BidList.get(i).getQuantity() - a.getQuantity()); 
					br.save(BidList.get(i)); 
					break; 	
					}
				else if(a.getPrice()<= BidList.get(i).getPrice() && a.getQuantity() == BidList.get(i).getQuantity()) {
					br.delete(BidList.get(i));
					break; 
				}else if (a.getPrice()<= BidList.get(i).getPrice() && a.getQuantity()> BidList.get(i).getQuantity()) {
					a.setQuantity(a.getQuantity() - BidList.get(i));
					br.delete(BidList.get(i));
					
					executeAskOrder(a); 
				}
	
				
			}
		} else {
			ar.save(a); 
		}
	}
	
}
