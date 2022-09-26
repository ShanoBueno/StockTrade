package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BidRepo extends JpaRepository<Bid, String>{
	@Query("select bid from Bid bid where bid.price>=?1 and bid.symbol=?2")
	public List<Bid> findbybidsprice(Double price, String Stock);
}
