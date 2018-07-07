package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Course;
import com.alucard.springHibernate.entity.Instructor;
import com.alucard.springHibernate.entity.InstructorDetail;
import com.alucard.springHibernate.entity.Review;
import com.alucard.springHibernate.entity.Student;

public class CreateCourseAndStudentsDemo {

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
			System.out.println("Create the course!");
			Course tempCourse = new Course("Batman Returns Course!");
			System.out.println("Save the course: " + tempCourse);
			
			session.save(tempCourse);
			
			System.out.println("Create the students!");			
			Student tempStudent1 = new Student("Dragos", "Stoian", "sadics@course.com");
			Student tempStudent2 = new Student("Alucard", "Smith", "alucard@course.com");
			
			
			System.out.println("add student2 to course");
			tempCourse.addStudent(tempStudent2);
			
			System.out.println("Save the student1: " + tempStudent1);
			System.out.println("Save the student2: " + tempStudent2);			
			
			session.save(tempStudent1);
			session.save(tempStudent2);
			
			System.out.println("add course to student1");
			tempStudent1.addCourse(tempCourse);
			
			System.out.println("Saved Students: " + tempCourse.getStudents());
			
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
