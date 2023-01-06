package com.ba.boost.service;

import java.time.LocalDate;

import com.ba.boost.dao.BookDao;
import com.ba.boost.dao.StudentDao;
import com.ba.boost.entity.Book;
import com.ba.boost.entity.Student;

public class LibraryService {

	private BookDao bookDao = new BookDao();
	private StudentDao studentDao = new StudentDao();

	public void borrowBook(Book book, Student student) {
		book.getBookDetail().setBookBorrowDate(LocalDate.now());
		book.getBookDetail().setBookReturnDate(LocalDate.now().plusDays(30));
		book.getBookDetail().setBorrowed(true);
		book.setActiveStudent(student);

		student.getBookLists().add(book);

		bookDao.update(book.getId(), book);
		studentDao.update(student.getId(), student);
		
		System.out.println(book.getTitle() + " is borrowed by " + student.getUsername());

	}
	
	public void returnBook(Book book, Student student) {
		book.getBookDetail().setBookBorrowDate(LocalDate.now());
		book.getBookDetail().setBookReturnDate(LocalDate.now().plusDays(30));
		book.getBookDetail().setBorrowed(true);
		book.setActiveStudent(student);

		student.getBookLists().add(book);

		bookDao.update(book.getId(), book);
		studentDao.update(student.getId(), student);

	}

}
