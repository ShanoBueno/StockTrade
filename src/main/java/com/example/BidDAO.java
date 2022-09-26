package com.example;

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
//		AskList.sort((o1, o2)-> o1.getPrice().compareTo(o2.getPrice()));
		System.out.println(AskList.get(0)); 

		int i = 0;
		if (AskList.size() != 0 ) {
			while(b.getQuantity() > 0) {
				if (b.getPrice() >= AskList.get(i).getPrice() && b.getQuantity() > AskList.get(i).getQuantity()){
					b.setQuantity(b.getQuantity() - AskList.get(i).getQuantity()); 
					ar.delete(AskList.get(i)); 
					
					i++;
				}else if (b.getPrice() >= AskList.get(i).getPrice() && b.getQuantity() == AskList.get(i).getQuantity()) {
					ar.delete(AskList.get(i));
					i++;
					break; 
				} else if (b.getPrice()>= AskList.get(i).getPrice() && b.getQuantity() < AskList.get(i).getQuantity()){
					AskList.get(i).setQuantity(AskList.get(i).getQuantity()-b.getQuantity());
					ad.insert(AskList.get(i)); 
					i++;
					break; 
				}
			}
		}else {
			br.save(b); 
		}
//		for (Ask aa:AskList) {
//			if (b.getPrice() >= aa.getPrice() && b.getQuantity() > aa.getQuantity()){
//				b.setQuantity(b.getQuantity() - aa.getQuantity()); 
//				ar.delete(aa); 
//				executeOrder(b); 
//				
//			
//			}
//			
//			else if (b.getPrice() >= aa.getPrice()&& b.getQuantity() == aa.getQuantity()) {
//				ar.delete(aa);
//				break; 
//			} else if (b.getPrice()>= aa.getPrice() && b.getQuantity() < aa.getQuantity()){
//				aa.setQuantity(aa.getQuantity()-b.getQuantity());
//				ad.insert(aa); 
//				break; 
//			}
//	
//			
//		}
//		}
//		else {
//			br.save(b); 
//		}
	}
}