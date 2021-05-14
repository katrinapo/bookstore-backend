package com.revature.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.BookOrder;
import com.revature.model.BookUser;
import com.revature.service.BookOrderService;
import com.revature.service.BookService;
import com.revature.service.UserService;

@RestController
@RequestMapping(value="/bookorders")
@CrossOrigin(origins="*")
public class BookOrderController {
	
	private BookOrderService oServ;
	
	private UserService uServ;
	
	private BookService bServ;

	public BookOrderController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public BookOrderController(BookOrderService oServ, UserService uServ, BookService bServ) {
		super();
		this.oServ = oServ;
		this.uServ = uServ;
		this.bServ = bServ;
	}
	
	@GetMapping("/initial")
	public ResponseEntity<String> insertInitialValues(){
		LocalDate date = LocalDate.now();
		BookUser bookuser1 = uServ.getUserByUserName("testUser1");
		BookUser bookuser2 = uServ.getUserByUserName("testUser2");
		
		List<BookOrder> oList = new ArrayList<BookOrder>(Arrays.asList(new BookOrder(1,200.00,true,date,bookuser1,null), new BookOrder(2,230.00,true,date,bookuser1,null),new BookOrder(3,450.00,true,date,bookuser2,null)));

		for(BookOrder bookorder: oList) {
			oServ.insertOrder(bookorder);
		}
		
		return new ResponseEntity<String>("order inserted", HttpStatus.CREATED);
	}	
	
	
	@GetMapping()
	public ResponseEntity<List<BookOrder>> getAllOrders() {
		return new ResponseEntity<List<BookOrder>>(oServ.getAllBookOrders(), HttpStatus.OK);
	}
	

	@GetMapping("/bookorder")
	public ResponseEntity<BookOrder> getBookOrderByIdPathParam(@RequestParam("orderid") int id) {
		BookOrder bookOrder = oServ.getByOrderId(id);
		
		if(bookOrder == null) {
			return new ResponseEntity<BookOrder>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookOrder>(bookOrder, HttpStatus.OK);
		
	}
	
	@PostMapping()
	public ResponseEntity<Object> insertOrder(@RequestBody BookOrder bookorder){
		oServ.insertOrder(bookorder);
		return new ResponseEntity<Object>(oServ.getByOrderId(bookorder.getOrderId()), HttpStatus.CREATED);
	}
	
	@PutMapping("/approve")
	public BookOrder approveBookOrder(@RequestBody BookOrder bookorder) {
		return oServ.approveBookOrder(bookorder);
	}
	

}
