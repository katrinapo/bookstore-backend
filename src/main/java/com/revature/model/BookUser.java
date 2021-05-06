package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bookuser")
public class BookUser {

	@Id
	@Column(name="userId")
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

	public BookUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookUser(int userId, String username, String password, String firstName, String lastName, String email,
			String phoneNumber, String userRole) {
		super();
		this.userId = userId;
		this.userName = username;
		this.passWord = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userRole = userRole;
	}

	public BookUser(String username, String password, String firstName, String lastName, String email,
			String phoneNumber, String userRole) {
		super();
		this.userName = username;
		this.passWord = password;
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

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return passWord;
	}

	public void setPassword(String password) {
		this.passWord = password;
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

	@Override
	public String toString() {
		return "BookUser [userId=" + userId + ", username=" + userName + ", password=" + passWord + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", userRole=" + userRole + "]";
	}
	
	
}
