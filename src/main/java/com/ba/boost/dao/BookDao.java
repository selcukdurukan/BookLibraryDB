package com.ba.boost.dao;

import java.util.ArrayList;
import java.util.List;

import com.ba.boost.entity.Book;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

public class BookDao implements IDatabaseCrud<Book> {

	@Override
	public List<Book> retrive() {
		ArrayList<Book> retriveBooks = null;
		try (Session session = databaseConnection();) {

			String hql = "SELECT p FROM Book AS p";

			TypedQuery<Book> typedQuery = session.createQuery(hql, Book.class);
			retriveBooks = (ArrayList<Book>) typedQuery.getResultList();

			for (Book p : retriveBooks) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while RETRIVING all of BOOKS from DB.");
		}
		return retriveBooks;
	}

	@Override
	public Book update(long id, Book entity) {
		Book updateBook = find(id);

		try (Session session = databaseConnection();) {
			updateBook.setTitle(entity.getTitle());
			updateBook.setBookDetail(entity.getBookDetail());
			updateBook.setActiveStudent(entity.getActiveStudent());

			session.getTransaction().begin();
			session.merge(updateBook);
			session.getTransaction().commit();
			
			System.out.println("BOOK data was UPDATED to DB at this " + session);
		} catch (Exception e) {
			System.out.println("Some problem occured while UPDATİNG BOOK to DB");
		}
		return updateBook;

	}

	@Override
	public Book delete(long id) {
		Book deleteBook = find(id);
		try (Session session = databaseConnection();) {
			if (deleteBook != null) {
				session.getTransaction().begin();
				session.remove(deleteBook);
				session.getTransaction().commit();

				System.out.println("BOOK whose " + id + " was deleted.");

			}
		} catch (Exception e) {
			System.out.println("Some problem occured while DELETING BOOK from DB");
		}
		return deleteBook;

	}

	@Override
	public Book find(long id) {
		Book findBook = null;
		try (Session session = databaseConnection();) {
			findBook = session.find(Book.class, id);
			if (findBook != null) {
				System.out.println(findBook);
			} else {
				System.out.println("BOOK cannot FOUND.");
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while FINDING BOOK.");
		}

		return findBook;
	}

}
