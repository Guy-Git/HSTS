package HSTS_Server;

import java.util.ArrayList;

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
import HSTS_Entities.ExecutedExam;
import HSTS_Entities.Message;
import HSTS_Entities.Question;

public class ExamController {
	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;

	private static int[] subjectCounter = new int[100];

	public void addExam(Exam exam) {

		// Dealing with exam ID:
		int subjectCode = 0;

		String examID = "";

		if (exam.getSubject().equals("Math")) {
			subjectCode = 1;
			subjectCounter[subjectCode]++;
			examID = "01";

			if (exam.getCourse().equals("Calculus")) {
				examID += "22";
			}

			if (exam.getCourse().equals("Algebra 101")) {
				examID += "13";
			}

			if (exam.getCourse().equals("Introduction to Probability")) {
				examID += "10";
			}

		}

		if (exam.getSubject().equals("CS")) {
			subjectCode = 43;
			subjectCounter[subjectCode]++;
			examID = "43";

			if (exam.getCourse().equals("Introduction to CS")) {
				examID += "19";
			}

			if (exam.getCourse().equals("Data structures")) {
				examID += "65";
			}

			if (exam.getCourse().equals("OS")) {
				examID += "03";
			}
		}

		if (exam.getSubject().equals("Biology")) {
			subjectCode = 78;
			subjectCounter[subjectCode]++;
			examID = "78";

			if (exam.getCourse().equals("Anatomy")) {
				examID += "72";
			}

			if (exam.getCourse().equals("Stem Cells")) {
				examID += "42";
			}

			if (exam.getCourse().equals("Biostructure")) {
				examID += "16";
			}
		}

		if (subjectCounter[subjectCode] < 10) {
			examID += "0" + subjectCounter[subjectCode];
		} else {
			examID += subjectCounter[subjectCode];
		}

		exam.setExamID(examID);

		try {
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

	public ArrayList<Exam> getExamsByExecutedExams(ArrayList<ExecutedExam> executedExams) {
		ArrayList<Exam> exams = new ArrayList<Exam>();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Exam> criteriaQuery = builder.createQuery(Exam.class);
			Root<Exam> rootEntry = criteriaQuery.from(Exam.class);

			for (int i = 0; i < executedExams.size(); i++) 
			{
				criteriaQuery.select(rootEntry)
						.where(builder.equal(rootEntry.get("examID"), executedExams.get(i).getExamID()));
				TypedQuery<Exam> query = session.createQuery(criteriaQuery);
				exams.add((Exam) query.getSingleResult());
			}
			// System.out.println(exams.get(0).getQuestions().get(0).getQuestionContent());
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

	public ArrayList<Exam> getExams(Message msg) {
		ArrayList<Exam> exams = new ArrayList<Exam>();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Exam> criteriaQuery = builder.createQuery(Exam.class);
			Root<Exam> rootEntry = criteriaQuery.from(Exam.class);
			criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("course"), msg.getCourse()),
					builder.equal(rootEntry.get("examSubject"), msg.getSubject()));
			TypedQuery<Exam> query = session.createQuery(criteriaQuery);
			exams = (ArrayList<Exam>) query.getResultList();

			// System.out.println(exams.get(0).getQuestions().get(0).getQuestionContent());
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

	public ArrayList<Exam> getExamsById(ArrayList<String> examsIds) {
		ArrayList<Exam> exams = new ArrayList<Exam>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Exam> criteriaQuery = builder.createQuery(Exam.class);
			Root<Exam> rootEntry = criteriaQuery.from(Exam.class);
			for (int i = 0; i < examsIds.size(); i++) {
				criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("examID"), examsIds.get(i)));
				TypedQuery<Exam> query = session.createQuery(criteriaQuery);
				exams.add((Exam) query.getSingleResult());
			}
			
			// System.out.println(exams.get(0).getQuestions().get(0).getQuestionContent());
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
