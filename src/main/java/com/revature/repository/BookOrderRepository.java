package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.BookOrder;
import com.revature.model.BookUser;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
	
	public List<BookOrder> findAll();
	public BookOrder findByOrderId(int orderid);
	
	public List<BookOrder> findByBookuser(BookUser bookuser);
	
	@Query(value = "select * from bookorder b where isapproved = false",
			nativeQuery=true)
	public List<BookOrder> findPendingBookOrders();
	
	@Query(value = "select * from bookorder b where isapproved = true",
			nativeQuery=true)
	public List<BookOrder> findApprovedBookOrders();

}
