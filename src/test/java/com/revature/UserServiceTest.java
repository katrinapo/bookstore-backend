package com.revature;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.model.Book;
import com.revature.model.BookUser;
import com.revature.repository.UserRepository;
import com.revature.service.UserService;

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
@RunWith(MockitoJUnitRunner.class)//this will intialize all objects annotate with @mock & @InjectMocks
@SpringBootTest
public class UserServiceTest {
	
	@Mock
	private UserRepository repo;
	
	@InjectMocks
	private UserService service;
	private BookUser buser;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	 @Test
	 public void getAllUsersTest() {
		 List<BookUser> list = new ArrayList<BookUser>(); 
		 BookUser bu1 = new BookUser(1,"testUser1", "123123", "Bob", "Jones","bobjones@test.com", "9089992323", "customer");
		 BookUser bu2 = new BookUser(2,"testUser2", "123123", "Mary", "Jones","maryjones@test.com", "1239992323", "customer");
		 BookUser bu3 = new BookUser(3,"testUser3", "456456", "Cookie", "Monster","cookiemonster@test.com", "1239992323", "employee");
		 
		 	list.add(bu1);
			list.add(bu2);
			list.add(bu3);
			
			when(service.getAllUsers()).thenReturn(list);
			List<BookUser> userList = repo.findAll();
			
			assertEquals(3,userList.size());
			verify(repo).findAll();
	 }
	 
	 @Test
		public void insertUserTest() {
			BookUser  buser = new BookUser(4,"testUser1", "123123", "Bob", "Jones","bobjones@test.com", "9089992323", "customer");
			service.insertUser(buser);
			verify(repo, times(1)).save(buser);
		}
	
	 @Test
	 public void getUserByNameTest() {
		 when(repo.findByUserName("testUser1")).thenReturn(new BookUser(1,"testUser1", "123123", "Bob", "Jones","bobjones@test.com", "9089992323", "customer"));
			
			BookUser buser = service.getUserByName("testUser1");
			assertEquals(1, buser.getUserId());
			assertEquals("testUser1", buser.getUserName());
			assertEquals("123123", buser.getPassWord());
			assertEquals("Bob", buser.getFirstName());
	 }
	 
	 @Test
	 public void getUserByEmailTest() {
		 when(repo.findByEmail("bobjones@test.com")).thenReturn(new BookUser(1,"testUser1", "123123", "Bob", "Jones","bobjones@test.com", "9089992323", "customer"));
			
			BookUser buser = service.getUserByEmail("bobjones@test.com");
			assertEquals(1, buser.getUserId());
			assertEquals("testUser1", buser.getUserName());
			assertEquals("123123", buser.getPassWord());
			assertEquals("Bob", buser.getFirstName());
	 }
	 
	 @Test
	 public void getUserByIdTest() {
		 when(repo.findByUserId(1)).thenReturn(new BookUser(1,"testUser1", "123123", "Bob", "Jones","bobjones@test.com", "9089992323", "customer"));
			
			BookUser buser = service.getUserById(1);
			assertEquals(1, buser.getUserId());
			assertEquals("testUser1", buser.getUserName());
			assertEquals("123123", buser.getPassWord());
			assertEquals("Bob", buser.getFirstName());
	 }
	@Test
	public void getUserByNameAndPasswordTest() {
		when(repo.findByUserNameAndPassWord("testUser1","123123" )).thenReturn(new BookUser(1,"testUser1", "123123", "Bob", "Jones","bobjones@test.com", "9089992323", "customer"));
		
		BookUser buser = service.getUserByUserNameAndPassWord("testUser1", "123123");
		assertEquals(1, buser.getUserId());
		assertEquals("testUser1", buser.getUserName());
		assertEquals("123123", buser.getPassWord());
		assertEquals("Bob", buser.getFirstName());
	}

	@Test
	public void getUserByRoleTest() {
		
		BookUser bookuser = new BookUser();
		bookuser.setUserId(1);
		bookuser.setUserName("testUser1");
		bookuser.setPassWord("123123");
		bookuser.setFirstName("Bob");
		bookuser.setLastName("Jones");
		bookuser.setEmail("bobjones@test.com");
		bookuser.setPhoneNumber("9089992323");
		bookuser.setUserRole("customer");
		
		List<BookUser> buserList = new ArrayList<>();
		buserList.add(bookuser);
		
		when(repo.findByUserRole("customer")).thenReturn(buserList);
		List<BookUser> roles = service.getUserByRole("customer");
		
		assertEquals(1, roles.size());
		verify(repo, times(1)).findByUserRole("customer");
		/*
		 * Book book = new Book();
		book.setBookId(1);
		book.setTitle("Intro to spring");
		book.setAuthor("Katrina");
		book.setGenre("any");
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		
		when(repo.findAllByGenre("any")).thenReturn(bookList);
		List<Book> books = service.getBooksByGenre("any");
		
		assertEquals(1, books.size());
		verify(repo, times(1)).findAllByGenre("any");
		 */
	}
}
