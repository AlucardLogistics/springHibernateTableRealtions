package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Course;
import com.alucard.springHibernate.entity.Instructor;
import com.alucard.springHibernate.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		
		try {		
						
			//start transaction
			session.beginTransaction();
			
			//get instructor from data base
			int theId = 1;
			Instructor tempInst = session.get(Instructor.class, theId);
			
			System.out.println("EVL: Instructors name: " + tempInst);
			
			System.out.println("EVL: Instructors courses: " + tempInst.getCourses());
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("EVL: Done! Phewww..");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
