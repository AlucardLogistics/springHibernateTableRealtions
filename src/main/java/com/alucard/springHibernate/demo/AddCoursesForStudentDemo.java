package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Course;
import com.alucard.springHibernate.entity.Instructor;
import com.alucard.springHibernate.entity.InstructorDetail;
import com.alucard.springHibernate.entity.Review;
import com.alucard.springHibernate.entity.Student;

public class AddCoursesForStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		
		try {		
						
			//start transaction
			session.beginTransaction();
			
			//create a course and student
			int theId = 2;
			Student alucard = session.get(Student.class, theId);
			System.out.println("Alucard courses: " + alucard.getCourses());
			
			System.out.println("Create courses...");
			Course tempCourse1 = new Course("Superman Reacts to Batman Returns!");
			Course tempCourse2 = new Course("A Batmobil Story!");
			Course tempCourse3 = new Course("BatFriends Zone!");
			
			System.out.println("Saving courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
			System.out.println("Add the courses to alucard...");
			alucard.addCourse(tempCourse1);
			alucard.addCourse(tempCourse2);
			alucard.addCourse(tempCourse3);
			
			System.out.println("Alucard courses: " + alucard.getCourses());
						
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! Phewww..");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
