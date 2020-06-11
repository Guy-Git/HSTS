package HSTS_Server;

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
		
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<HstsUser> criteriaQuery = builder.createQuery(HstsUser.class);
			Root<HstsUser> rootEntry = criteriaQuery.from(HstsUser.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("userId"), user.getUserId()), 
					builder.equal(rootEntry.get("userPassword"), user.getUserPassword()));
			TypedQuery<HstsUser> query = session.createQuery(criteriaQuery);
			foundUser = query.getResultList().get(0);
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
