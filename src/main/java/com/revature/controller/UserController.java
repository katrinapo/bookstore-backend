package com.revature.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/{username}")
	public ResponseEntity<BookUser> getUserByUsername(@PathVariable("username") String name){
		BookUser bUser = uServ.getUserByName(name);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
	
	@GetMapping("/{username}/{password}")
	public ResponseEntity<BookUser> loginUser(@PathVariable("username") String name,@PathVariable("password") String pass){
		BookUser bUser = uServ.getUserByNameAndPassword(name, pass);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
	
	@GetMapping("/{userrole}")
	public ResponseEntity<List<BookUser>> getUserByRole(@PathVariable("userrole") String role){
		List<BookUser> bUser = uServ.getUserByRole(role);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BookUser>>(bUser,HttpStatus.OK);
	}
	@GetMapping("/{email}")
	public ResponseEntity<BookUser> getUserByEmail(@PathVariable("email") String email){
		BookUser bUser = uServ.getUserByEmail(email);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<BookUser> getUser(@PathVariable("id") int id){
		BookUser bUser = uServ.getUserById(id);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
}
