package com.fsd.example.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fsd.example.entity.Book;
import com.fsd.example.helper.SessionFactoryHelper;

public class BookDao {

	public static boolean addBook(Book newBook) throws Exception {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(newBook);
		tx.commit();
		session.close();
		return true;
	}

	public static List<Book> getAllBooks() throws Exception {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		session.beginTransaction();
		List<Book> bookList = session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
		session.close();
		return bookList;
	}

	public static List<Book> searchForBooks(String bookTitle) throws Exception {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		session.beginTransaction();
		List<Book> bookList = session.createQuery("select b from Book b where lower(title) like :bookTitle", Book.class)
				.setParameter("bookTitle", "%" + bookTitle + "%").getResultList();

		session.close();
		return bookList;
	}

	public static int deleteBook(String bookTitle) throws Exception {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		int results = session.createQuery("delete from Book where lower(title) like :bookTitle")
				.setParameter("bookTitle", "%" + bookTitle + "%").executeUpdate();
		tx.commit();
		session.close();
		return results;
	}
}
