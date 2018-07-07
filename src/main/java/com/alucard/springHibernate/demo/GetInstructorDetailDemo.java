package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Instructor;
import com.alucard.springHibernate.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		
		try {
			
			//start  a transaction
			session.beginTransaction();
			
			int theId = 1;
			
			InstructorDetail tempInstDetail = session.get(InstructorDetail.class, theId);
			
			System.out.println("tempInstDetail: " + tempInstDetail);
			
			System.out.println("Associated instructor: " + tempInstDetail.getInstructor());
			
						
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! Phewww..");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//handle leaks
			session.close();
			
			factory.close();
		}
	}

}
