package com.ba.boost.dao;

import java.util.ArrayList;
import java.util.List;

import com.ba.boost.entity.BookDetail;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

public class BookDetailDao implements IDatabaseCrud<BookDetail> {

	@Override
	public List<BookDetail> retrive() {
		ArrayList<BookDetail> retriveBookDetail = null;
		try (Session session = databaseConnection();) {

			String hql = "SELECT p FROM BookDetail AS p";

			TypedQuery<BookDetail> typedQuery = session.createQuery(hql, BookDetail.class);
			retriveBookDetail = (ArrayList<BookDetail>) typedQuery.getResultList();

			for (BookDetail p : retriveBookDetail) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while RETRIVING all of BOOKDETAIL from DB.");
		}
		return retriveBookDetail;
	}

	@Override
	public BookDetail update(long id, BookDetail entity) {
		BookDetail updateBookDetail = find(id);

		try (Session session = databaseConnection();) {
			updateBookDetail.setBorrowed(entity.isBorrowed());
			updateBookDetail.setBookBorrowDate(entity.getBookBorrowDate());
			updateBookDetail.setBookReturnDate(entity.getBookReturnDate());
			updateBookDetail.setBook(entity.getBook());

			session.getTransaction().begin();
			session.merge(updateBookDetail);
			session.getTransaction().commit();
			
			System.out.println("BOOKDETAIL data was UPDATED to DB at this " + session);
		} catch (Exception e) {
			System.out.println("Some problem occured while UPDATİNG BOOKDETAIL to DB");
		}
		return updateBookDetail;

	}

	@Override
	public BookDetail delete(long id) {
		BookDetail deleteBookDetail = find(id);
		try (Session session = databaseConnection();) {
			if (deleteBookDetail != null) {
				session.getTransaction().begin();
				session.remove(deleteBookDetail);
				session.getTransaction().commit();

				System.out.println("BOOKDETAIL whose " + id + " was deleted.");

			}
		} catch (Exception e) {
			System.out.println("Some problem occured while DELETING BOOKDETAIL from DB");
		}
		return deleteBookDetail;

	}

	@Override
	public BookDetail find(long id) {
		BookDetail findBookDetail = null;
		try (Session session = databaseConnection();) {
			findBookDetail = session.find(BookDetail.class, id);
			if (findBookDetail != null) {
				System.out.println(findBookDetail);
			} else {
				System.out.println("BOOKDETAIL cannot FOUND.");
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while FINDING BOOKDETAIL.");
		}

		return findBookDetail;
	}

}
