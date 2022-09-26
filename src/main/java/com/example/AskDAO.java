package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class AskDAO {
	@Autowired
	AskRepo ar; 
	@Autowired
	BidRepo bd; 

	public Ask insert(Ask a) {
		return ar.save(a);
	}
	public List<Ask> getall(){
		return ar.findAll() ;
	}
	
	
}
