package com.ba.boost.dao;

import java.util.ArrayList;
import java.util.List;

import com.ba.boost.entity.Student;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

public class StudentDao implements IDatabaseCrud<Student> {

	@Override
	public List<Student> retrive() {
		ArrayList<Student> retriveStudent = null;
		try (Session session = databaseConnection();) {

			String hql = "SELECT p FROM Student AS p";

			TypedQuery<Student> typedQuery = session.createQuery(hql, Student.class);
			retriveStudent = (ArrayList<Student>) typedQuery.getResultList();

			for (Student p : retriveStudent) {
				System.out.println(p);
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while RETRIVING all of Students from DB.");
		}
		return retriveStudent;
	}

	@Override
	public Student update(long id, Student entity) {
		Student updateStudent = find(id);

		try (Session session = databaseConnection();) {
			updateStudent.setUsername(entity.getUsername());
			updateStudent.setBookLists(entity.getBookLists());

			session.getTransaction().begin();
			session.merge(updateStudent);
			session.getTransaction().commit();
			
			System.out.println("Student data was UPDATED to DB at this " + session);
		} catch (Exception e) {
			System.out.println("Some problem occured while UPDATİNG Student to DB");
		}
		return updateStudent;

	}

	@Override
	public Student delete(long id) {
		Student deleteStudent = find(id);
		try (Session session = databaseConnection();) {
			if (deleteStudent != null) {
				session.getTransaction().begin();
				session.remove(deleteStudent);
				session.getTransaction().commit();

				System.out.println("Student whose " + id + " was deleted.");

			}
		} catch (Exception e) {
			System.out.println("Some problem occured while DELETING Student from DB");
		}
		return deleteStudent;

	}

	@Override
	public Student find(long id) {
		Student findStudent = null;
		try (Session session = databaseConnection();) {
			findStudent = session.find(Student.class, id);
			if (findStudent != null) {
				System.out.println(findStudent);
			} else {
				System.out.println("STUDENT cannot FOUND.");
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while FINDING STUDENT.");
		}

		return findStudent;
	}

}
