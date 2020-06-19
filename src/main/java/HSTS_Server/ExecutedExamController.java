package HSTS_Server;

import java.util.ArrayList;

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
import HSTS_Entities.Message;
import HSTS_Entities.Question;
import HSTS_Entities.StudentsExecutedExam;

public class ExecutedExamController {

	static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;

	public void addStudentExectutedExam(StudentsExecutedExam studentExecutedExam) {

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(studentExecutedExam);

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
		
		if(studentExecutedExam.isManual())
			addCheckedExam(studentExecutedExam);
	}
	
	
	public void addExectutedExam(ExecutedExam executedExam) {

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("kaki");
			session.save(executedExam);

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
	
	/*public void addFinishedManualExam(StudentsExecutedExam studentsExecutedExam) {
		
		ExecutedExam executedExam = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ExecutedExam> criteriaQuery = builder.createQuery(ExecutedExam.class);
			Root<ExecutedExam> rootEntry = criteriaQuery.from(ExecutedExam.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("examID"), studentsExecutedExam.getExecutedExam().getExamID()),
					builder.equal(rootEntry.get("examCode"), studentsExecutedExam.getExecutedExam().getExamCode()));
			TypedQuery<ExecutedExam> query = session.createQuery(criteriaQuery);
			
			try {
				executedExam = (ExecutedExam) query.getSingleResult();
			} catch (NoResultException nre) {
				System.out.println("Executed Exam not found!");
			}
			session.close();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			//System.out.println(executedExam);
			session.evict(executedExam);
			executedExam.getStudentsExecutedExams().add(studentsExecutedExam);
			executedExam.setNumOfStudents(executedExam.getNumOfStudents() + 1);
			if(studentsExecutedExam.isForcedFinish())
			{
				executedExam.setNumForced(executedExam.getNumForced() + 1);
			}
			else {
				executedExam.setNumUnforced(executedExam.getNumUnforced() + 1);
			}
			
			session.update(executedExam);
			session.flush();
			//session.getTransaction().commit(); 

		}catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return;


	}*/
	
	public ArrayList<ExecutedExam> getExamsByTeacher(HstsUser user)
	{
		ArrayList<ExecutedExam> exams = new ArrayList<ExecutedExam>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			System.out.println(user.getUserId());
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ExecutedExam> criteriaQuery = builder.createQuery(ExecutedExam.class);
			Root<ExecutedExam> rootEntry = criteriaQuery.from(ExecutedExam.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("assignedBy"), user.getUserId()),
					builder.equal(rootEntry.get("isChecked"), false));					
					TypedQuery<ExecutedExam> query = session.createQuery(criteriaQuery);
			try {
				exams = (ArrayList<ExecutedExam>) query.getResultList();
			} catch (NoResultException nre) {
				System.out.println("Exam not found!");
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
		
		return exams;
	}
	
	public void checkExam(StudentsExecutedExam studentsExecutedExam) {
		// TODO Auto-generated method stub

		Exam exam = null;
		ArrayList<Integer> answers = new ArrayList<Integer>();
		int grade = 0;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			//System.out.println(studentsExecutedExam.getExamID());
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Exam> criteriaQuery = builder.createQuery(Exam.class);
			Root<Exam> rootEntry = criteriaQuery.from(Exam.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("examID"), studentsExecutedExam.getExecutedExam().getExamID()));					
					TypedQuery<Exam> query = session.createQuery(criteriaQuery);
			try {
				exam = (Exam) query.getSingleResult();
			} catch (NoResultException nre) {
				System.out.println("Exam not found!");
			}

			for (int i = 0; i < exam.getQuestions().size(); i++) {
				if (studentsExecutedExam.getAnswersForExam().get(i) == exam.getQuestions().get(i)
						.getRightAnswer()) {
					grade += exam.getQuestionGrade().get(i);

				} 
			}
			studentsExecutedExam.setGrade(grade);
			addCheckedExam(studentsExecutedExam);
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
	
public void updateExecutedExam(ExecutedExam updatedExecutedExam) 
{
		System.out.println(updatedExecutedExam.getStudentsExecutedExams().get(0).getExamGrade());
		
		ExecutedExam executedExam = null;
		ArrayList<StudentsExecutedExam> studentsExecutedExams = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ExecutedExam> criteriaQuery = builder.createQuery(ExecutedExam.class);
			Root<ExecutedExam> rootEntry = criteriaQuery.from(ExecutedExam.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("examCode"), updatedExecutedExam.getExamCode()),
					builder.equal(rootEntry.get("examID"), updatedExecutedExam.getExamID()));
			TypedQuery<ExecutedExam> query = session.createQuery(criteriaQuery);
			try {
				executedExam = (ExecutedExam) query.getSingleResult();
			} catch (NoResultException nre) {
				System.out.println("Exam code not found!");
			}	
			
			session.evict(executedExam);
			session.update(updatedExecutedExam);
			session.flush();
			
			session.getTransaction().commit();
			session.close();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			CriteriaBuilder builder1 = session.getCriteriaBuilder();
			CriteriaQuery<StudentsExecutedExam> criteriaQuery1 = builder1.createQuery(StudentsExecutedExam.class);
			Root<StudentsExecutedExam> rootEntry1 = criteriaQuery1.from(StudentsExecutedExam.class);
			criteriaQuery1.select(rootEntry1).where(
					builder1.equal(rootEntry1.get("executedExam"), updatedExecutedExam.getId()));
			TypedQuery<StudentsExecutedExam> query1 = session.createQuery(criteriaQuery1);
			try {
				studentsExecutedExams = (ArrayList<StudentsExecutedExam>) query1.getResultList();
			} catch (NoResultException nre) {
				System.out.println("Exam code not found!");
			}
			
			for(int i = 0; i < updatedExecutedExam.getNumOfStudents(); i++)
			{
				session.evict(studentsExecutedExams.get(i));
				studentsExecutedExams.set(i, updatedExecutedExam.getStudentsExecutedExams().get(i));
				session.update(studentsExecutedExams.get(i));
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
	}
	
	public ExecutedExam getExecutedExam(Message msg) {
		// TODO Auto-generated method stub
		ExecutedExam executedExam = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ExecutedExam> criteriaQuery = builder.createQuery(ExecutedExam.class);
			Root<ExecutedExam> rootEntry = criteriaQuery.from(ExecutedExam.class);
			criteriaQuery.select(rootEntry)
					.where(builder.equal(rootEntry.get("examCode"), msg.getExamForExec().getExamCode()));
			TypedQuery<ExecutedExam> query = session.createQuery(criteriaQuery);
			try {
				executedExam = (ExecutedExam) query.getSingleResult();
			} catch (NoResultException nre) {
				System.out.println("Exam code not found!");
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

		return executedExam;
	}

	private void addCheckedExam(StudentsExecutedExam studentsExecutedExam) {
		// TODO Auto-generated method stub

		ExecutedExam executedExam = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ExecutedExam> criteriaQuery = builder.createQuery(ExecutedExam.class);
			Root<ExecutedExam> rootEntry = criteriaQuery.from(ExecutedExam.class);
			criteriaQuery.select(rootEntry).where(
					builder.equal(rootEntry.get("examID"), studentsExecutedExam.getExecutedExam().getExamID()),
					builder.equal(rootEntry.get("examCode"), studentsExecutedExam.getExecutedExam().getExamCode()));
			TypedQuery<ExecutedExam> query = session.createQuery(criteriaQuery);
			
			try {
				executedExam = (ExecutedExam) query.getSingleResult();
			} catch (NoResultException nre) {
				System.out.println("Executed Exam not found!");
			}
			session.close();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			//System.out.println(executedExam);
			session.evict(executedExam);
			executedExam.setChecked(false);
			executedExam.getStudentsExecutedExams().add(studentsExecutedExam);
			executedExam.setNumOfStudents(executedExam.getNumOfStudents() + 1);
			if(studentsExecutedExam.isForcedFinish())
			{
				executedExam.setNumForced(executedExam.getNumForced() + 1);
			}
			else {
				executedExam.setNumUnforced(executedExam.getNumUnforced() + 1);
			}
			
			session.update(executedExam);
			session.flush();
			//session.getTransaction().commit(); 

		}catch (Exception exception) {
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
