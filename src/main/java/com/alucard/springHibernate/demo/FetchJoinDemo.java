package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.alucard.springHibernate.entity.Course;
import com.alucard.springHibernate.entity.Instructor;
import com.alucard.springHibernate.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			//Hibernate query with HQL
			
			//get instructor from data base
			int theId = 1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i " + 
										"JOIN FETCH i.courses " + 
										"where i.id=:theInstructorId", 
										Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get intructor
			Instructor tempInst = query.getSingleResult();
			
			System.out.println("EVL: Instructors name: " + tempInst);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("\nEVL: Closing the session!\n");
			session.close();
			
			System.out.println("EVL: Instructor courses: " + tempInst.getCourses());
			
			System.out.println("EVL: Done! Phewww..");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			factory.close();
		}
	}

}
