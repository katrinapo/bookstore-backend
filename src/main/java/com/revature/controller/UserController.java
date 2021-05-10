package com.revature.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.BookUser;
import com.revature.model.Status;
import com.revature.repository.UserRepository;
import com.revature.service.UserService;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*") 
public class UserController {
	
	private UserService uServ;
	private UserRepository uRepo;

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
	

	@GetMapping("/username")
	public ResponseEntity<BookUser> getUserByUsername(@RequestParam("username") String name){
		BookUser bUser = uServ.getUserByUserName(name);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
	
	
	@GetMapping("/userrole")
	public ResponseEntity<List<BookUser>> getUserByRole(@RequestParam("userrole") String role){
		List<BookUser> bUser = uServ.getUserByRole(role);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BookUser>>(bUser,HttpStatus.OK);
	}
	@GetMapping("/email")
	public ResponseEntity<BookUser> getUserByEmail(@RequestParam("email") String email){
		BookUser bUser = uServ.getUserByEmail(email);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
	@GetMapping("/id")
	public ResponseEntity<BookUser> getUser(@RequestParam("id") int id){
		BookUser bUser = uServ.getUserById(id);
		if(bUser==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
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
	@PostMapping("/register")
    public Status registerUser(@Validated @RequestBody BookUser newUser) {
        List<BookUser> users = uServ.getAllUsers();
        System.out.println("New user: " + newUser.toString());
        for (BookUser user : users) {
            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        uServ.insertUser(newUser);
        return Status.SUCCESS;
    }
	
	@PostMapping("/login")
    public Status loginUser(@Validated @RequestBody BookUser user,HttpServletRequest request) {
        List<BookUser> users = uServ.getAllUsers();
        for (BookUser other : users) {
            if (other.getUserName().equals(user.getUserName())&& other.getPassWord().equals(user.getPassWord())) {
                
                return Status.SUCCESS;
               
            }
            request.getSession().setAttribute("user",user);
        }
        return Status.FAILURE;
    }
    @PostMapping("/logout")
    public Status logUserOut(HttpServletRequest request) {
    	request.getSession().invalidate();
        return Status.SUCCESS;
    }
}
