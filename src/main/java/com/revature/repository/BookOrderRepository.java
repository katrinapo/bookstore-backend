package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.BookOrder;
import com.revature.model.BookUser;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
	
	public List<BookOrder> findAll();
	public BookOrder findByOrderId(int orderid);
	
	public BookOrder findByBookuser(BookUser bookuser);

}
