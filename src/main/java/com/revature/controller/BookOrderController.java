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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Book;
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
		
		List<BookOrder> oList = new ArrayList<BookOrder>(Arrays.asList(new BookOrder(3.00,true,date,bookuser1,null)));

		for(BookOrder bookorder: oList) {
			oServ.insertOrder(bookorder);
		}
		
		return new ResponseEntity<String>("order inserted", HttpStatus.CREATED);
	}	
	
	
	@GetMapping()
	public ResponseEntity<List<BookOrder>> getAllOrders() {
		return new ResponseEntity<List<BookOrder>>(oServ.getAllBookOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/pending")
	public ResponseEntity<List<BookOrder>> getPendingOrders() {
		return new ResponseEntity<List<BookOrder>>(oServ.getPendingBookOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/approved")
	public ResponseEntity<List<BookOrder>> getApprovedOrders() {
		return new ResponseEntity<List<BookOrder>>(oServ.getApprovedBookOrders(), HttpStatus.OK);
	}

	@GetMapping("/bookorder")
	public ResponseEntity<BookOrder> getBookOrderByIdPathParam(@RequestParam("orderid") int id) {
		BookOrder bookOrder = oServ.getByOrderId(id);
		
		if(bookOrder == null) {
			return new ResponseEntity<BookOrder>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookOrder>(bookOrder, HttpStatus.OK);
		
	}
	
	@PostMapping("/submitorder")
	public ResponseEntity<List<BookOrder>> insertOrder(@RequestBody List<Book> book,@RequestParam("amount") String amount1,@RequestParam("userName") String userName){
		
		System.out.println(book);
		System.out.println(amount1);
		System.out.println(userName);
		BookUser user = uServ.getUserByUserName(userName);
		
		double amount=Double.parseDouble(amount1);
		BookOrder order = new BookOrder();
		order.setTotalCost(amount);
		order.setBooks(book);
		order.setBookuser(user);
		order.setDate(LocalDate.now());
		order.setIsapproved(false);
	
		oServ.insertOrder(order);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/ordersbyuser")
	public ResponseEntity<List<BookOrder>> getBookOrderByUser(@RequestParam("userName") String userName){
		BookUser user = uServ.getUserByUserName(userName);
		
		return new ResponseEntity<List<BookOrder>>(oServ.getByBookUser(user), HttpStatus.OK);
	}
	
	@PutMapping("/approve")
	public BookOrder approveBookOrder(@RequestBody BookOrder bookorder) {

		return oServ.approveBookOrder(bookorder);
	}
	

}
