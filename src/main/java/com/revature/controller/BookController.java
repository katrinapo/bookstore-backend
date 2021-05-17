package com.revature.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Book;
import com.revature.service.BookService;

@RestController
@RequestMapping(value="/books")
@CrossOrigin(origins="*") 
public class BookController {
	
	private BookService bServ;
	private static Logger log = Logger.getLogger(BookController.class);

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


		List<Book> bList = new ArrayList<Book>(Arrays.asList(new Book("Animal Farm", "George Orwell", "Fiction", 4.00,10,"https://bookimagesbucket.s3.us-east-2.amazonaws.com/animalfarm.jpg"),new Book("To Kill A Mockingbird", "Harper Lee", "Fiction", 6.00,8,"https://bookimagesbucket.s3.us-east-2.amazonaws.com/tokillamockingbird.jpg"), new Book("Black Swan", "Nassim Taleb", "Economy", 6.00,8,null)));

		for (Book book: bList) {
			bServ.insertBook(book);
			log.info("Books inserted initially.");
		}
		
		return new ResponseEntity<String>("Book Inserted",HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Book>> getAllBooks(){
		return new ResponseEntity<List<Book>>(bServ.getAllBooks(), HttpStatus.OK);
	}
	

	@GetMapping("/bookid")
	public ResponseEntity<Book> getBookId(@RequestParam("id") int id){
		Book book = bServ.getBookById(id);
		if(book==null) {
			log.info("No books found");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookId") int id){
		Book book = bServ.getBookById(id);
		if(book==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		log.info("Book returned by id.");
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}


	@GetMapping("/booktitle")
	public ResponseEntity<Book> getBookTitle(@RequestParam("title") String title){
		Book book = bServ.getBookWithTitle(title);
		if(book==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		log.info("Book returned by title.");
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<Book> getBookByTitle(@PathVariable("booktitle") String title){
		Book book = bServ.getBookWithTitle(title);
		if(book==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@GetMapping("/genre")
	public ResponseEntity<List<Book>> getAllBooksByGenre(String genre){
		return new ResponseEntity<List<Book>>(bServ.getBooksByGenre(genre), HttpStatus.OK);
	}
	
	@GetMapping("/author")
	public ResponseEntity<List<Book>> getAllBooksByAuthor(String author){
		return new ResponseEntity<List<Book>>(bServ.getBooksByAuthor(author), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> insertBook(@RequestBody Book book) {
		bServ.insertBook(book);
		return new ResponseEntity<Object>(bServ.getBookById(book.getBookId()), HttpStatus.CREATED);
	}
	
	
	@PutMapping("/updatebook")
	public Book updateBook(@RequestBody Book book) {
		return bServ.updateBook(book);
		
	}
	
	@PutMapping("/addImage")
	public Book uploadImage(@RequestBody Book book, String imageURL) {
		 return bServ.addImage(book);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteBook(@RequestBody Book book) {
		bServ.deleteBook(book);
		return new ResponseEntity<>("resource Deleted", HttpStatus.GONE);
		
	}

}
