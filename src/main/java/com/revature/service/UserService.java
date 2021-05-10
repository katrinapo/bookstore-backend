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
	
	public BookUser getUserByUserName(String username) {
		return uRepo.findByUserName(username);
	}
	
	public BookUser getUserByUserNameAndPassWord(String username, String password) {
		return uRepo.findByUserNameAndPassWord(username, password);
	}
	
}
