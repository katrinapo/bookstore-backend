package com.revature.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Book;
import com.revature.service.BookService;

@RestController
@RequestMapping(value="/books")
@CrossOrigin(origins="*") 
public class BookController {
	
	private BookService bServ;

	public BookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public BookController(BookService bServ) {
		super();
		this.bServ = bServ;
	}
	
	@GetMapping("/initial")
	public ResponseEntity<String> insertInitialValues() {
		List<Book> bList = new ArrayList<Book>(Arrays.asList(new Book("Intro to Java", "Jacob", "Computers", 80.00,10,null),new Book("Intro to Angular", "Jacob", "Computers", 90.00,8,null), new Book("Intro to JavaScript", "Jacob", "Computers", 40.00,8,null)));
	
		for (Book book: bList) {
			bServ.insertBook(book);
		}
		
		return new ResponseEntity<String>("BookUser Inserted",HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Book>> getAllBooks(){
		return new ResponseEntity<List<Book>>(bServ.getAllBooks(), HttpStatus.OK);
	}
	

	
	
	@GetMapping("/{genre}")
	public ResponseEntity<Book>findBookByGenre(@PathVariable("genre") String genre){
		
		Book book = bServ.findBookByGenre(genre);
		if(book==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@GetMapping("/{title}/{author}")
	public ResponseEntity<Book>findBookByTitleAndAuthor(@PathVariable("title") String title, @PathVariable("author") String author){
		
		Book book = bServ.findBookByTitleAndAuthor(title,author);
		if(book==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
