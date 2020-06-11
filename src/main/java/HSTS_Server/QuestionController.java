package HSTS_Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;
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
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;
import ocsf_Server.ConnectionToClient;

public class QuestionController 
{
	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;

	private static int[] subjectCounter = new int[100];
	
	public ArrayList<Question> getQuestions(Message msg)
	{
		ArrayList<Question> questions = new ArrayList<Question>();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> criteriaQuery = builder.createQuery(Question.class);
			Root<Question> rootEntry = criteriaQuery.from(Question.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("course"), msg.getCourse()), 
					builder.equal(rootEntry.get("subject"), msg.getSubject()));
			TypedQuery<Question> query = session.createQuery(criteriaQuery);
			questions = (ArrayList<Question>) query.getResultList();

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return questions;
	}
	
	public void addQuestion(Question question)
	{
		String questionID = "";
		
		int subjectCode = 0;
		if(question.getSubject().equals("Math"))
		{
			subjectCode = 1;
			subjectCounter[subjectCode]++;
			questionID = "01";
		}
		
		if(question.getSubject().equals("CS"))
		{
			subjectCode = 43;
			subjectCounter[subjectCode]++;
			questionID = "43";
		}
		
		if(question.getSubject().equals("Biology"))
		{
			subjectCode = 78;
			subjectCounter[subjectCode]++;
			questionID = "78";
		}
		
		if (subjectCounter[subjectCode] < 10) {
			questionID = questionID + "00" + subjectCounter[subjectCode];
		} else if (subjectCounter[subjectCode] < 100) {
			questionID = questionID + "0" + subjectCounter[subjectCode];
		} else {
			questionID = questionID + subjectCounter[subjectCode];
		}
		
		question.setQuestionID(questionID);
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(question);
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
