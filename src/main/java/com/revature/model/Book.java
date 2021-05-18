package com.revature.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name="seq", initialValue=1000, allocationSize=100)
@Entity
@Table(name="book")
public class Book {
	
	@Id
	@Column(name="bookId")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int bookId;
	
	@Column(name="title", nullable=false, unique=true)
	private String title;
	
	@Column(name="author", nullable=false )
	private String author;
	
	@Column(name="genre", nullable=false)
	private String genre;
	
	@Column(name="cost", nullable=false)
	private double cost;
	
	@Column(name="quantity")
	private int quantity;
	
	
	@Column(name="image")
	private String image;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int bookId,String title, String author, String genre, double cost, int quantity, String image) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.cost = cost;
		this.quantity = quantity;
		this.image = image;
	}
	
	public Book(String title, String author, String genre, double cost, int quantity, String image) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.cost = cost;
		this.quantity = quantity;
		this.image = image;
	}
	/*
	 * public Book(int bookId,String title, String author, String genre, double
	 * cost, int quantity, String image) { super(); this.bookId=bookId; this.title =
	 * title; this.author = author; this.genre = genre; this.cost = cost;
	 * this.quantity = quantity; this.image = image; }
	 */


	public int getBookId() {
		return bookId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", genre=" + genre + ", cost="
				+ cost + ", quantity=" + quantity + ", image=" + image + "]";
	}

	public void setBookId(int bookId) {
		// TODO Auto-generated method stub
		this.bookId = bookId;
	}


}
