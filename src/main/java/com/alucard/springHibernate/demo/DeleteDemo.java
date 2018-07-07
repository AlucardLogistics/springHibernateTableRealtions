package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Instructor;
import com.alucard.springHibernate.entity.InstructorDetail;

public class DeleteDemo {

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
			
			//start  a transactions
			session.beginTransaction();
			
			//get the instructor by their primary key
			int id = 2;
			Instructor inst = session.get(Instructor.class, id);
			System.out.println("foind the instructor: " + inst);
			
			//delete the instructor
			if(inst != null) {
				System.out.println("Deleting: " + inst);
				//ALSO delete the associated "details" object (Cascade.ALL)
				session.delete(inst);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! Phewww..");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
