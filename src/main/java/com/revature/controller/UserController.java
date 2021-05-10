package com.revature.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.BookUser;
import com.revature.service.UserService;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*") 
public class UserController {
	
	private UserService uServ;

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public UserController(UserService uServ) {
		super();
		this.uServ = uServ;
	}
	
	@GetMapping("/initial")
	public ResponseEntity<String> insertInitialValues(){
		
		List<BookUser> uList = new ArrayList<BookUser>(Arrays.asList(new BookUser("testUser1", "123123", "Bob", "Jones",
				"bobjones@test.com", "9089992323", "customer"), new BookUser("testUser2", "123123", "Mary", "Jones",
						"maryjones@test.com", "1239992323", "customer"),  new BookUser("testUser3", "456456", "Cookie", "Monster",
								"cookiemonster@test.com", "1239992323", "employee")));
		for(BookUser bookUser: uList) {
			uServ.insertUser(bookUser);
		}
		
		return new ResponseEntity<String>("BookUser Inserted", HttpStatus.CREATED);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<BookUser>> getAllUsers() {
		return new ResponseEntity<List<BookUser>>(uServ.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/bookuser")
	public ResponseEntity<BookUser> getBookUserNamePathParam(@RequestParam("username") String username) {
		BookUser bookuser = uServ.getUserByUserName(username);
		if(bookuser==null) {
			return new ResponseEntity<BookUser>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bookuser, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> insertUser(@RequestBody BookUser bookuser) {
		uServ.insertUser(bookuser);
		return new ResponseEntity<Object>(uServ.getUserByUserName(bookuser.getUserName()),HttpStatus.CREATED);
	}
	
	@GetMapping("/usernamepassword")
		public ResponseEntity<BookUser> getBookUserNamePassWordPathParam(@RequestParam("username") String username, @RequestParam("password") String password) {
			BookUser bookuser = uServ.getUserByUserNameAndPassWord(username,password);
			if(bookuser==null) {
				return new ResponseEntity<BookUser>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<BookUser>(bookuser, HttpStatus.OK);
	}

}
