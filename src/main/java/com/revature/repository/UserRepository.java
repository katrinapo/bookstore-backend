package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.BookUser;

public interface UserRepository extends JpaRepository<BookUser, Integer>{
	
	public List<BookUser> findAll();
	public BookUser findByUserId(int id);
	public List<BookUser> findByUserRole(String userRole);
	public BookUser findByEmail(String email);
	public BookUser findByUserName(String userName);
	public BookUser findByUserNameAndPassWord(String userName, String password);
	
	public BookUser findByResetPasswordToken(String token);


}
