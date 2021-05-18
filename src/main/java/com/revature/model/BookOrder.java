package com.revature.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="bookorder")
public class BookOrder {

	@Id
	@Column(name="orderid", unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	
	@Column(name="totalcost")
	private double totalCost;
	
	@Column(name="isapproved")
	private boolean isapproved;
	
	@Column(name="date")
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private BookUser bookuser;

	@ManyToMany(cascade=CascadeType.MERGE)
	private List<Book> books;

	public BookOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public BookOrder(double totalCost, boolean isapproved, LocalDate date, BookUser bookuser,
			List<Book> books) {
		super();
		this.totalCost = totalCost;
		this.isapproved = isapproved;
		this.date = date;
		this.bookuser = bookuser;
		this.books = books;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public boolean isIsapproved() {
		return isapproved;
	}

	public void setIsapproved(boolean isapproved) {
		this.isapproved = isapproved;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BookUser getBookuser() {
		return bookuser;
	}

	public void setBookuser(BookUser bookuser) {
		this.bookuser = bookuser;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "BookOrder [orderId=" + orderId + ", totalCost=" + totalCost + ", isapproved=" + isapproved + ", date="
				+ date + ", bookuser=" + bookuser + ", books=" + books + "]";
	}
	
	


}