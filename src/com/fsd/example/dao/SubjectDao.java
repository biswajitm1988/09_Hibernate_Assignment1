package com.fsd.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fsd.example.entity.Book;
import com.fsd.example.entity.Subject;
import com.fsd.example.helper.SessionFactoryHelper;

public class SubjectDao {

	public static boolean addSubject(Subject newSubject) throws Exception {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(newSubject);
		tx.commit();
		return true;
	}

	public static int getAllSubjectCount() throws Exception {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		session.beginTransaction();
		Long subjectCount = 0l;
		subjectCount = (Long) session.createQuery("select count(s) from Subject s").uniqueResult();
		session.close();
		return subjectCount.intValue();
	}

	public static List<Book> searchForSubjects(String subtitle) throws Exception {

		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		session.beginTransaction();
		List<Book> bookList = session
				.createQuery("select s.references from Subject s where lower(subtitle) like :subtitle")
				.setParameter("subtitle", "%" + subtitle + "%").getResultList();

		session.close();
		return bookList;
	}

	public static int deleteSubject(String subtitle) throws Exception {
		Session session = SessionFactoryHelper.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int results = session.createQuery("delete Subject s where lower(subtitle) like :subtitle")
				.setParameter("subtitle", "%" + subtitle + "%").executeUpdate();
		tx.commit();
		session.close();
		return results;
	}
}
