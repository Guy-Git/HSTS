package HSTS_Server;

import java.util.ArrayList;

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
import HSTS_Entities.Message;
import HSTS_Entities.Question;

public class ExamController 
{
	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;

	public void addExam(Exam exam)
	{
		try 
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(exam);
			
			session.flush();
			session.getTransaction().commit(); // Save everything.
		}
		
		catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public ArrayList<Exam> getExams(Message msg)
	{
		ArrayList<Exam> exams = new ArrayList<Exam>();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Exam> criteriaQuery = builder.createQuery(Exam.class);
			Root<Exam> rootEntry = criteriaQuery.from(Exam.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("course"), msg.getCourse()), 
					builder.equal(rootEntry.get("subject"), msg.getSubject()));
			TypedQuery<Exam> query = session.createQuery(criteriaQuery);
			exams = (ArrayList<Exam>) query.getResultList();

			System.out.println(exams.get(0).getQuestions().get(0).getQuestionContent());
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return exams;
	}
	
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(Exam.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
