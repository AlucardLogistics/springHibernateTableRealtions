package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Course;
import com.alucard.springHibernate.entity.Instructor;
import com.alucard.springHibernate.entity.InstructorDetail;
import com.alucard.springHibernate.entity.Review;
import com.alucard.springHibernate.entity.Student;

public class DeleteCoursesForStudentDemo {

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
			
			//get course
			int courseId = 10;
			int studentId = 1;
			Student alucard = session.get(Student.class, studentId);
			Course tempCourse = session.get(Course.class, courseId);
			System.out.println("Dragos courses: " + alucard.getCourses());
			
			//session.delete(tempCourse);
			//session.delete(alucard);
						
			System.out.println("Course Students: " + tempCourse.getStudents());
			
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
