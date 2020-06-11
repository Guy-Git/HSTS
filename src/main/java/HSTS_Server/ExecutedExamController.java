package HSTS_Server;

import javax.persistence.NoResultException;
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
import HSTS_Entities.ExecutedExam;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Question;
import HSTS_Entities.StudentsExecutedExam;

public class ExecutedExamController {

	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;

	public void addExam(StudentsExecutedExam StudentExam) {
		
		ExecutedExam executedExam = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ExecutedExam> criteriaQuery = builder.createQuery(ExecutedExam.class);
			Root<ExecutedExam> rootEntry = criteriaQuery.from(ExecutedExam.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("examID"), StudentExam.getExamID()), 
					builder.equal(rootEntry.get("examCode"), StudentExam.getExamCode()));
			TypedQuery<ExecutedExam> query = session.createQuery(criteriaQuery);
			try {
				executedExam = (ExecutedExam) query.getSingleResult();
			} catch (NoResultException nre) {
				System.out.println("Executed Exam not found!");
			}
			
			if(executedExam == null)
			{
				executedExam = new ExecutedExam();
				executedExam.setExamCode(StudentExam.getExamCode());
				executedExam.setExamID(StudentExam.getExamID());
			}
	}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package
		configuration.addAnnotatedClass(ExamForExec.class);
		configuration.addAnnotatedClass(Exam.class);
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(ExecutedExam.class);
		configuration.addAnnotatedClass(StudentsExecutedExam.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
