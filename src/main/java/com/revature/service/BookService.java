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

	public Book getBookById(int id){
		return bRepo.findByBookId(id);
	}
	
	public Book getBookWithTitle(String title) {
		return bRepo.findByTitle(title);
	}
	
	public List<Book> getBooksByGenre(String genre){
		return bRepo.findAllByGenre(genre);
	}
	
	public List<Book> getBooksByAuthor(String author){
		return bRepo.findAllByGenre(author);
	}
	
	public Book getBookById(int id){
		return bRepo.findByBookId(id);
	}
	
	public Book getBookWithTitle(String title) {
		return bRepo.findByTitle(title);
	}
	
	public Book getBookWithAuthor(String author) {
		return bRepo.findByAuthor(author);
	}
	
}
