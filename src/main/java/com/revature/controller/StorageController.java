package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.service.BookService;
import com.revature.service.StorageService;


@RestController
@RequestMapping(value="/books")
@CrossOrigin(origins="*") 
public class StorageController {

	@Autowired
	private StorageService service;
	
	@Autowired
	private BookService bServ;
	
	public StorageController(BookService bServ) {
		super();
		this.bServ = bServ;
	}

	@PutMapping("/uploadimage")
	public ResponseEntity<String> uploadFile(@RequestParam(value="file") MultipartFile file, String title) {
		return new ResponseEntity<>(service.uploadFile(file, title), HttpStatus.OK);
	}
	
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
}

//https://www.youtube.com/watch?v=fUNyaKDgJd4&list=PLZTETldyguF0ogvkEzN-p6b73dXgCBhn9&index=16