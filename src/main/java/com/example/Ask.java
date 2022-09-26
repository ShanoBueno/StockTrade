package com.example;



import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Ask {

	@Id
	private Double price; 
	private String symbol;
	private int quantity; 

	
	
}