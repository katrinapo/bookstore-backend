package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Book;
import com.revature.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bRepo;

	public BookService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public BookService(BookRepository bRepo) {
		super();
		this.bRepo = bRepo;
	}
	
	public List<Book> getAllBooks(){
		return bRepo.findAll();
	}
	
	public void insertBook(Book book) {
		bRepo.save(book);
	}
	
}
