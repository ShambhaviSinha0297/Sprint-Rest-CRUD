package com.springrest.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.dao.BookDao;
import com.springrest.exceptions.InvalidBookIdException;
import com.springrest.model.Book;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;

	public Book addBook(Book book) {
		Book returnBook = null;
		if (book != null && book.getBookName() != "") {
			returnBook = bookDao.addBook(book);
		}
		return returnBook;
	}

	public boolean deleteBook(int id) throws InvalidBookIdException {
		boolean result = false;
		if (bookDao.isBookExist(id)) {
			result = bookDao.deleteBook(id);
		} else {
			throw new InvalidBookIdException("Invalid Book Id");
		}
		return result;
	}

	public Book getBookById(int id) throws InvalidBookIdException {

		Book book = bookDao.getBookById(id);
		if (book == null) {
			throw new InvalidBookIdException("Invalid Book Id");
		}

		return book;
	}

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	public boolean updateBook(Book book) throws InvalidBookIdException {
 		 boolean result = false;
		if (bookDao.isBookExist(book.getBookId())) {
			result = bookDao.updateBook(book);
		} else {
			throw new InvalidBookIdException("Invalid Book Id");
		}
		return result;
	}

}
