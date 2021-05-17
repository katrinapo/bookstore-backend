package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bookuser")
public class BookUser {

	@Id
	@Column(name="userid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="username", unique=true, nullable=false)
	private String userName;
	
	@Column(name="password", nullable=false)
	private String passWord;
	
	@Column(name="firstname", nullable=false)
	private String firstName;
	
	@Column(name="lastname", nullable=false)
	private String lastName;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Column(name="phonenumber")
	private String phoneNumber;
	
	@Column(name="userrole")
	private String userRole;
	
	@Column(name="reset_password_token")
	private String resetPasswordToken;
	
	
	@OneToMany(mappedBy="bookuser")
	private List<BookOrder> bookorders = new ArrayList<>();

	public BookUser() {
		super();
		
	}


	public BookUser(int userId, String userName, String passWord, String firstName, String lastName, String email,
			String phoneNumber, String userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userRole = userRole;
	}
	
	public BookUser(String userName, String passWord, String firstName, String lastName, String email,
			String phoneNumber, String userRole) {
		super();
				this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userRole = userRole;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}


	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}


	@Override
	public String toString() {
		return "BookUser [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", userRole=" + userRole + "]";
	}

}