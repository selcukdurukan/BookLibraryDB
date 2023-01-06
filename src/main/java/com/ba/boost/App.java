package com.ba.boost;

import com.ba.boost.dao.BookDao;
import com.ba.boost.dao.StudentDao;
import com.ba.boost.entity.Book;
import com.ba.boost.entity.BookDetail;
import com.ba.boost.entity.Student;
import com.ba.boost.service.LibraryService;

public class App {

	public static void main(String[] args) {

       Student s1 = new Student();
       s1.setUsername("hamathamat");
       StudentDao studentDao = new StudentDao();
       studentDao.create(s1);
       
       Book book1 = new Book();
       book1.setTitle("Uluslarin Dususu");
       BookDetail bookDetail1 = new BookDetail();
       book1.setBookDetail(bookDetail1);
       BookDao bookDao = new BookDao();
       bookDao.create(book1);
       
       
       LibraryService ls = new LibraryService();
       ls.borrowBook(book1, s1);

	}

}
