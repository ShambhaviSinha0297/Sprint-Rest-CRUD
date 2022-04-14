package com.springrest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springrest.model.Book;

@Repository
@Transactional
public class BookDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Book addBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.save(book);
		return book;
	}

	public boolean deleteBook(int id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.load(Book.class, id);
		session.delete(book);
		return true;
	}

	public Book getBookById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, id);
		return book;
	}

	public List<Book> getAllBooks() {
		Session session = sessionFactory.getCurrentSession();
		List<Book> bookList = session.createQuery("from  Book").list();
		return bookList;
	}

	public boolean updateBook( Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.update(book);
		return true;
	}

	public boolean isBookExist(int id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, id);
		boolean result = true;
		if (book == null) {
			result = false;
		}
		return result;

	}

}
