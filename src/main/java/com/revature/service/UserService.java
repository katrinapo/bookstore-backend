package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.BookUser;
import com.revature.repository.UserRepository;

@Service
public class UserService {

	private UserRepository uRepo;

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserService(UserRepository uRepo) {
		super();
		this.uRepo = uRepo;
	}
	
	public List<BookUser> getAllUsers(){
		return uRepo.findAll();
	}
	
	public void insertUser(BookUser bookUser) {
		uRepo.save(bookUser);
	}
	public BookUser getUserByName(String name){
		return uRepo.findByUserName(name);
	}
	public BookUser getUserByNameAndPassword(String name,String pass) {
		return uRepo.findByUserNameAndPassWord(name, pass);
	}
	public BookUser getUserByEmail(String email) {
		return uRepo.findByEmail(email);
	}
	
	public List<BookUser> getUserByRole(String role) {
		return uRepo.findByUserRole(role);
	}
	public BookUser getUserById(int id) {
		return uRepo.findByUserId(id);
	}
	
	public BookUser getUserByUserName(String username) {
		return uRepo.findByUserName(username);
	}
	
	public BookUser getUserByUserNameAndPassWord(String username, String password) {
		return uRepo.findByUserNameAndPassWord(username, password);
	}
	

	public void  updateResetPasswordToken(String token, String email) throws UserNotFoundException {
		BookUser user = uRepo.findByEmail(email);
		
		if(user != null) {
			user.setResetPasswordToken(token);
			uRepo.save(user);
		}
		else {
			throw new UserNotFoundException("Could not fond any customer with email " + email);
		}
		
	}
	
	public BookUser getByResetPasswordToken(String resetPosswordToken) {
		return uRepo.findByResetPasswordToken(resetPosswordToken);
		
	}

	public void updatePassword(BookUser user, String newPassword) {
		user.setPassWord(newPassword);
		user.setResetPasswordToken(null);
		uRepo.save(user);
	}

}
