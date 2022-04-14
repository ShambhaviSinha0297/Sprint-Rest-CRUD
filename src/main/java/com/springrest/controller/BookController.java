package com.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.exceptions.InvalidBookIdException;
import com.springrest.model.Book;
import com.springrest.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService service;

	

	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		ResponseEntity<?> entity = null;
		Book createdBook = service.addBook(book);
		if (createdBook == null) {
			entity = new ResponseEntity<String>("Book Not Created", HttpStatus.BAD_REQUEST);
		} else {
			entity = new ResponseEntity<Book>(createdBook, HttpStatus.CREATED);
		}
		return entity;
	}

	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() {
		ResponseEntity<?> entity = null;
		List<Book> bookList = service.getAllBooks();
		// customerList = new ArrayList<>();
		if (bookList == null || bookList.isEmpty()) {
			entity = new ResponseEntity<String>("No Books in the List", HttpStatus.NO_CONTENT);
		} else {
			entity = new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
		}
		return entity;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		ResponseEntity<?> entity = null;
		boolean result;
		try {
			result = service.deleteBook(id);
			entity = new ResponseEntity<String>("Book deleted Successfully", HttpStatus.OK);

		} catch (InvalidBookIdException e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable int id) {
		ResponseEntity<?> entity = null;
	 
		try {
			Book  book = service.getBookById(id);
			entity = new ResponseEntity<Book>(book, HttpStatus.OK);

		} catch (InvalidBookIdException e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBook(@RequestBody Book  book) {
		ResponseEntity<?> entity = null;
		boolean result;
		try {
			result = service.updateBook(book);
			entity = new ResponseEntity<String>("Book updated Successfully", HttpStatus.OK);

		} catch (InvalidBookIdException e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
}
