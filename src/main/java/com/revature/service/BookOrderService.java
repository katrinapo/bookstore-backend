package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.model.Book;
import com.revature.model.BookOrder;
import com.revature.model.BookUser;
import com.revature.repository.BookOrderRepository;
import com.revature.repository.BookRepository;

@Service
public class BookOrderService {

	private BookOrderRepository oRepo;


	public BookOrderService(BookOrderRepository oRepo) {
		super();
		this.oRepo = oRepo;
	}
	
	public List<BookOrder> getAllBookOrders() {
		return oRepo.findAll();
	}
	
	public void insertOrder(BookOrder bookorder) {
		oRepo.save(bookorder);
	}
	
	public void deleteOrder(BookOrder bookorder) {
		oRepo.delete(bookorder);
	}
	
	public BookOrder getByOrderId(int orderid) {
		return oRepo.findByOrderId(orderid);
	}
	
	public List<BookOrder> getByBookUser(BookUser bookuser) {
		return (List<BookOrder>) oRepo.findByBookuser(bookuser);
	}
	
	public BookOrder approveBookOrder(BookOrder order) {
		int id= order.getOrderId();
		
		BookOrder bOrder = oRepo.findByOrderId(id);
		bOrder.setIsapproved(true);
		return oRepo.save(bOrder);
		
	}
	
	public List<BookOrder> getPendingBookOrders() {
		return oRepo.findPendingBookOrders();
	}
	
	public List<BookOrder> getApprovedBookOrders() {
		return oRepo.findApprovedBookOrders();
	}
}
