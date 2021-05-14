package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.model.Book;
import com.revature.repository.BookRepository;
import com.revature.service.BookService;

@RunWith(MockitoJUnitRunner.class)//this will intialize all objects annotate with @mock & @InjectMocks
@SpringBootTest
public class BookServiceTest {
	
	@Mock
	private BookRepository repo;
	
	
	@InjectMocks
	private BookService service;
	private Book book;
	
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllBooksTest() {
		
		List<Book> list = new ArrayList<Book>();
		Book b1 = new Book(1,"Intro to spring","Katrina","any",50,5,"any");
		Book b2 = new Book(2,"Intro to Mockito","Mantesh","any",40,4,"any");
		Book b3 = new Book(3,"Intro to Junit","Chandra","any",30,3,"any");
		
		list.add(b1);
		list.add(b2);
		list.add(b3);
		
		when(service.getAllBooks()).thenReturn(list);
		
		List<Book> bookList = repo.findAll();
		
		assertEquals(3, bookList.size());
		verify(repo, times(1)).findAll();
	}

	@Test
	public void insertBookTest() {
		Book  book = new Book(4,"Intro to spring","Katrina","any",50,5,"any");
		service.insertBook(book);
		verify(repo, times(1)).save(book);
	}
	
	@Test
	public void getBookByIdTest() {
		when(repo.findByBookId(1)).thenReturn(new Book(1,"Intro to spring","Katrina","any",50,5,"any"));
		
		Book book = service.getBookById(1);
		assertEquals(1, book.getBookId());
		assertEquals("Intro to spring", book.getTitle());
		assertEquals("Katrina", book.getAuthor());
		assertEquals("any", book.getGenre());
		//assertEquals(50, book.getCost());
		}
	
    @Test
    public void getBookWithTitleTest() {
    	when(repo.findByTitle("Intro to spring")).thenReturn(new Book(1,"Intro to spring","Katrina","any",50,5,"any"));
    	Book book = service.getBookWithTitle("Intro to spring");
    	assertEquals(1, book.getBookId());
		assertEquals("Intro to spring", book.getTitle());
		assertEquals("Katrina", book.getAuthor());
		assertEquals("any", book.getGenre());
    }
    
   @Test
   public void getBookWithAuthorTest() {
	   when(repo.findByAuthor("Katrina")).thenReturn(new Book(1,"Intro to spring","Katrina","any",50,5,"any"));
   	Book book = service.getBookWithAuthor("Katrina");
   	 assertEquals(1, book.getBookId());
		assertEquals("Intro to spring", book.getTitle());
		assertEquals("Katrina", book.getAuthor());
		assertEquals("any", book.getGenre());
	   
		
   }
	
}