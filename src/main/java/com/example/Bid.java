package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Bid {
	private String symbol;
	private int quantity; 
	@Id
	private double price; 

}
