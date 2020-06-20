package com.haresh.hibernatedemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.haresh.hibernatedemo.config.HibernateUtil;
import com.haresh.hibernatedemo.model.Student;

public class StudentDao {
	
	public void saveStudent(Student student) {
		Transaction transaction = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Start the transaction
			transaction = session.beginTransaction();
			
			// Save the Student Object
			session.save(student);
			
			// Commit the transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public List<Student> getStudents() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.createQuery("from Student", Student.class).list();
		} 
	}
	
	public int updateStudent(Student student) {
		Transaction transaction = null;
		int updatedRecords = 0;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			String hql = "update Student set firstName = :firstName, lastName = :lastName where email = :email";
			Query query = session.createQuery(hql);
			query.setParameter("firstName", student.getFirstName());
			query.setParameter("lastName", student.getLastName());
			query.setParameter("email", student.getemail());
			
			updatedRecords = query.executeUpdate();
			System.out.println("Number of rows updated: " + updatedRecords);
			
			transaction.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return updatedRecords;
	}
	
	public int deleteStudent(int studentId) {
		Transaction transaction = null;
		int deletedRecords = 0;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			Student student = session.get(Student.class, studentId);
			if (student != null) {
				String hql = "delete from Student where id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id", student.getId());
				
				deletedRecords = query.executeUpdate();
				System.out.println("Number of rows deleted: " + deletedRecords);
			}			
			
			transaction.commit();			
		}
		catch (Exception e) {
			// TODO: handle exception
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return deletedRecords;
	}

}
