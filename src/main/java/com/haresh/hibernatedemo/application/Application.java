/**
 * 
 */
package com.haresh.hibernatedemo.application;

import java.util.List;

import com.haresh.hibernatedemo.dao.StudentDao;
import com.haresh.hibernatedemo.model.Student;

/**
 * @author Haresh Shaha
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentDao dao = new StudentDao();
		
		Student student1 = new Student("Haresh", "Shaha", "haresh.shaha7@gmail.com");
		Student student2 = new Student("Prachi", "Shaha", "prachi.shaha7@gmail.com");
		Student student3 = new Student("Akash", "Doshi", "akash.doshi5@gmail.com");
		Student student4 = new Student("Prashant", "Sadavarte", "prashant.sadavarte@gmail.com");
		Student student5 = new Student("Sanket", "gargate", "sanket.gargate@gmail.com");

		dao.saveStudent(student1);
		dao.saveStudent(student2);
		dao.saveStudent(student3);
		dao.saveStudent(student4);
		dao.saveStudent(student5);
		
		System.out.println("------------------------------------------------------");
		
		List<Student> students = dao.getStudents();
		for (Student student : students) {
			System.out.println(student.getFirstName() + " " + student.getLastName() + " -> " + student.getemail());			
		}

		System.out.println("------------------------------------------------------");
		
		Student modifiedStudent = new Student("Sanket", "Gargate", "sanket.gargate@gmail.com");
		dao.updateStudent(modifiedStudent);
		students = dao.getStudents();
		for (Student student : students) {
			System.out.println(student.getFirstName() + " " + student.getLastName() + " -> " + student.getemail());			
		}

		System.out.println("------------------------------------------------------");
		
		dao.deleteStudent(3);
		students = dao.getStudents();
		for (Student student : students) {
			System.out.println(student.getFirstName() + " " + student.getLastName() + " -> " + student.getemail());			
		}
	}

}
