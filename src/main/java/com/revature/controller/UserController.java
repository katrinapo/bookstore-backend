package com.revature.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.BookUser;
import com.revature.model.Status;
import com.revature.service.MailService;
import com.revature.service.UserService;

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*") 
public class UserController {
	
	private UserService uServ;
	private static Logger log = Logger.getLogger(UserController.class);

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
			log.info("No User found by username.");
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
	
	
	@GetMapping("/userrole/{userrole}")
	public ResponseEntity<List<BookUser>> getUserByRole(@PathVariable String userrole){
		List<BookUser> bUser = uServ.getUserByRole(userrole);
		if(bUser==null) {
			log.info("No User found by userrole.");
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BookUser>>(bUser,HttpStatus.OK);
	}
	@GetMapping("/email")
	public ResponseEntity<BookUser> getUserByEmail(@RequestParam("email") String email){
		BookUser bUser = uServ.getUserByEmail(email);
		if(bUser==null) {
			log.info("No User found by email.");
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<BookUser> getUser(@PathVariable int id){
		BookUser bUser = uServ.getUserById(id);
		if(bUser==null) {
			log.info("No User found by id.");
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookUser>(bUser,HttpStatus.OK);
	}

	@GetMapping("/bookuser/{username}")
	public ResponseEntity<BookUser> getBookUserNamePathParam(@PathVariable String username) {
		BookUser bookuser = uServ.getUserByUserName(username);
		if(bookuser==null) {
			log.info("No books by username found");
			return new ResponseEntity<BookUser>(HttpStatus.NOT_FOUND);
		}
		log.info("All books by username returned.");
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
    public Status registerUser(@RequestBody BookUser user)throws Exception {
        List<BookUser> users = uServ.getAllUsers();
        
        System.out.println("New user: " + user.toString());
        for (BookUser user1 : users) {
            System.out.println("Registered user: " + user.toString());
            if (user1.getUserName().equals(user.getUserName())) {
                System.out.println("Username Already exists!");
                throw new Exception("Username already Exists");
            }
            if (user1.getEmail().equals(user.getEmail())) {
                System.out.println("User with that email Already exists!");
                log.info("New registration unsuccessful because user already exists.");
                throw new Exception("User with that email already Exists");
            }
        }
        uServ.insertUser(user);
        log.info("New registration successful.");
        return Status.SUCCESS;
    }
	
	@PostMapping("/login")
    public BookUser loginUser(@RequestBody BookUser user) throws Exception {
		String tempUsername = user.getUserName();
		String tempPassword = user.getPassWord();
		BookUser userObj = null;
          if(tempUsername!=null && tempPassword!=null) {
        	  log.info("Login successful.");
                userObj = uServ.getUserByUserNameAndPassWord(tempUsername, tempPassword);
            
            }
          
          if (userObj == null) {
        	  throw new Exception("Bad credentials");
          }
          return userObj;
           
        }

    @PostMapping("/forgotpassword1/{name}")
    public String processForgotPasswordForm(@PathVariable String name, HttpServletRequest request) throws Exception {
    	System.out.println(name);
    	BookUser user = uServ.getUserByUserName(name);
    	String email2 = user.getEmail();
    	
    	String token = RandomString.make(30);
  	   	System.out.println("method is");
    	
  	   	try
  	   	{
  	   		uServ.updateResetPasswordToken(token, email2);

  	   	String resetPasswordLink = "http://bookstorefrontend.s3-website.us-east-2.amazonaws.com/createpassword?token=" + token;
    	MailService.sendMail(email2,resetPasswordLink);
    	log.info("Email to reset the password sent to user.");
  	   	}
  	   	catch(Exception ex){
  	   		ex.printStackTrace();
  	   	}

		return null;
    	
    }
    
    @PostMapping("/resetpassword")
    public Status processResetPassword(HttpServletRequest request) {
    	String token = request.getParameter("token");
    	String password = request.getParameter("password");
    	
    	BookUser user = uServ.getByResetPasswordToken(token);
    	
    	if(user != null) {
    		uServ.updatePassword(user,password);
			log.info("Password reset successful.");
    		return Status.SUCCESS;
    		
    	}
    	
    	return Status.FAILURE;
    }
}
