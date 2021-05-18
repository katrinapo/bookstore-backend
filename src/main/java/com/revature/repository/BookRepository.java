package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	public List<Book> findAll();
	public Book findByBookId(int id);
	public Book findByTitle(String title);
	public Book findByAuthor(String author);
	//public Book findByGenre(String genre);
	public Book findByTitleAndAuthor(String title, String author);
	
	public List<Book> findAllByGenre(String genre);
	public List<Book> findAllByAuthor(String author);
	
	
	
}
