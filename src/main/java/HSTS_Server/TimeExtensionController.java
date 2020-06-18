package HSTS_Server;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import HSTS_Entities.Exam;
import HSTS_Entities.ExamForExec;
import HSTS_Entities.Message;
import HSTS_Entities.TimeExtension;

public class TimeExtensionController 
{
	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;
	
	public void addTimeExtentionRequest(TimeExtension timeExtention)
	{
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			session.save(timeExtention);
			session.flush();
			
			session.getTransaction().commit(); // Save everything.
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public int getTimeExtension(ExamForExec examForExec)
	{
		int time = 0;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TimeExtension> criteriaQuery = builder.createQuery(TimeExtension.class);
			Root<TimeExtension> rootEntry = criteriaQuery.from(TimeExtension.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("approved"), true),
					builder.equal(rootEntry.get("examCode"), examForExec.getExamCode()),
					builder.equal(rootEntry.get("examID"), examForExec.getExamID()));
			TypedQuery<TimeExtension> query = session.createQuery(criteriaQuery);
			
			try {
				ArrayList<TimeExtension> timeExtensions = (ArrayList<TimeExtension>) query.getResultList();
				for(int i = 0; i < timeExtensions.size(); i++)
					time += timeExtensions.get(i).getRequestedTime();
				
			} catch (NoResultException nre) {
				System.out.println("No time extension!");
			}
			
			
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		
		return time;
	}
	
	public ArrayList<TimeExtension> getTimeExtensions()
	{
		ArrayList<TimeExtension> timeExtensions = new ArrayList<TimeExtension>();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TimeExtension> criteriaQuery = builder.createQuery(TimeExtension.class);
			Root<TimeExtension> rootEntry = criteriaQuery.from(TimeExtension.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("status"), true));
			TypedQuery<TimeExtension> query = session.createQuery(criteriaQuery);
			timeExtensions = (ArrayList<TimeExtension>) query.getResultList();
			
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return timeExtensions;
	}
	
	public void cancelTimeExtensions(ExamForExec examForExec) {
		ArrayList<TimeExtension> timeExtensions = new ArrayList<TimeExtension>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TimeExtension> criteriaQuery = builder.createQuery(TimeExtension.class);
			Root<TimeExtension> rootEntry = criteriaQuery.from(TimeExtension.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("status"), true),
					builder.equal(rootEntry.get("examCode"), examForExec.getExamCode()),
					builder.equal(rootEntry.get("examID"), examForExec.getExamID()));
			TypedQuery<TimeExtension> query = session.createQuery(criteriaQuery);
			timeExtensions = (ArrayList<TimeExtension>) query.getResultList();
			
			for (int i = 0; i < timeExtensions.size(); i++) {
				session.evict(timeExtensions.get(i));
				timeExtensions.get(i).setStatus(false);
				session.update(timeExtensions.get(i));
				session.flush();
			}
			session.getTransaction().commit(); 
			
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return;
	}
	
	public void updateTimeExtensions(ArrayList<TimeExtension> timeExtensionsArr)
	{	
		ArrayList<TimeExtension> timeExtensions = new ArrayList<TimeExtension>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TimeExtension> criteriaQuery = builder.createQuery(TimeExtension.class);
			Root<TimeExtension> rootEntry = criteriaQuery.from(TimeExtension.class);
			criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("status"), true));
			TypedQuery<TimeExtension> query = session.createQuery(criteriaQuery);
			timeExtensions = (ArrayList<TimeExtension>) query.getResultList();
			
			for (int i = 0; i < timeExtensions.size(); i++) {
				session.evict(timeExtensions.get(i));
				timeExtensions.get(i).setStatus(timeExtensionsArr.get(i).isStatus());
				timeExtensions.get(i).setApproved(timeExtensionsArr.get(i).isApproved());
				session.update(timeExtensions.get(i));
				session.flush();
			}
			session.getTransaction().commit(); 
			
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return;
	}
	
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package
		configuration.addAnnotatedClass(TimeExtension.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
