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
import HSTS_Entities.ExamForExec;
import HSTS_Entities.Message;
import HSTS_Entities.Question;

public class ExamExecController {

	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;

	public Exam getExamForExec(Message msg) {
		ExamForExec examForExec = new ExamForExec();
		Exam exam = new Exam();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ExamForExec> criteriaQuery = builder.createQuery(ExamForExec.class);
			Root<ExamForExec> rootEntry = criteriaQuery.from(ExamForExec.class);
			criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("examCode"), msg.getExecCode()));
			TypedQuery<ExamForExec> query = session.createQuery(criteriaQuery);
			examForExec = (ExamForExec) query.getResultList().get(0);
			if (examForExec == null) {
				exam = null;
			} 
			else {
				CriteriaQuery<Exam> criteriaQuery1 = builder.createQuery(Exam.class);
				Root<Exam> rootEntry1 = criteriaQuery1.from(Exam.class);
				criteriaQuery1.select(rootEntry1)
						.where(builder.equal(rootEntry1.get("examID"), examForExec.getExamID()));
				TypedQuery<Exam> query1 = session.createQuery(criteriaQuery1);
				exam = (Exam) query1.getResultList().get(0);
				exam.setManual(examForExec.isManual());
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
		return exam;
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
