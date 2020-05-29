package HSTS_Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import HSTS_Entities.Message;
import HSTS_Entities.Question;
import HSTS_Entities.Exam;
import HSTS_Entities.HstsUser;
import ocsf_Server.AbstractServer;
import ocsf_Server.ConnectionToClient;

public class AppsServer extends AbstractServer {

	private static Session session;
	private Question chosenQuestion;
	private int isFound = 0;
	private int changeType;
	private int answerNum;
	static SessionFactory sessionFactory = getSessionFactory();
	private QuestionController questionController;
	private UserController userController;
	Message serverMsg;

	public AppsServer(int port) {
		super(port);
		questionController = new QuestionController();
		userController = new UserController();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		serverMsg = new Message();
		
		if(((Message)msg).getAction().equals("Create Question"))
		{
			questionController.addQuestion(((Message)msg).getQuestion());
		}
		
		if(((Message)msg).getAction().equals("Get Teachers Subjects and couerses"))
		{
			serverMsg.setAction("Got subjects and courses");
			serverMsg.setSubjects(userController.getSubsAndCourses(((Message)msg).getUser()).getSubjects());
			serverMsg.setCourses(userController.getSubsAndCourses(((Message)msg).getUser()).getCourses());
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		/*System.out.println("Received Message: " + msg.toString());

		if (msg.toString().equals("#ShowAll")) {
			showAll(client);
		}
		
		else {
			if (msg.toString().startsWith("#2")) {
				findQuestion(msg.toString().substring(3, 8), client);
			}

			else if (msg.toString().startsWith("#CQ")) {
				msg = "Enter changes for question: ";
				changeType = 1;
				try {
					client.sendToClient(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (msg.toString().startsWith("#CA")) {
				System.out.print(msg.toString().charAt(msg.toString().length() - 1));

				answerNum = Character.getNumericValue(msg.toString().charAt(msg.toString().length() - 1));
				msg = "Enter the changes for the chosen answer: ";
				changeType = 2;
				try {
					client.sendToClient(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (msg.toString().startsWith("#CRA")) {
				msg = "Enter the new right answer number: ";
				changeType = 3;
				try {
					client.sendToClient(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {
				if (!msg.toString().contains("#M")) {
					editQuestion(msg.toString(), changeType, client);
					try {
						session = sessionFactory.openSession();
						session.beginTransaction();

						List<Question> questions = getAll(Question.class);
						// System.out.println("KAKI");

						for (Question question : questions) { // Change to a better type of search!!!!!!!
							if (question.getQuestionID().equals(chosenQuestion.getQuestionID()))
								chosenQuestion = question;
						}

						msg = chosenQuestion;
						try {
							client.sendToClient(msg);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

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
			}
		}*/
	}

	public void showAll(ConnectionToClient client) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			List<Question> questions = getAll(Question.class);
			try {
				// System.out.println(questions.get(0));
				client.sendToClient(questions);
			} catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
			}

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

	public void findQuestion(String questionID, ConnectionToClient client) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// You can change the question, answers, right answers,
			List<Question> questions = getAll(Question.class);
			Object msg;
			// System.out.println("KAKI");

			for (Question question : questions) { // Change to a better type of search!!!!!!!
				if (question.getQuestionID().equals(questionID))
				{
					chosenQuestion = question;
					isFound = 1;
				}
			}
			
			if(isFound == 1)
				msg = chosenQuestion;
			else {
				msg = "Question not found! please try again: ";
			}
			isFound = 0;
			
			try {
				client.sendToClient(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

	public void editQuestion(String theActualChange, int changeType, ConnectionToClient client) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			switch (changeType) {
			case 1: // Edit question content
				System.out.println(chosenQuestion.getQuestionID());
				System.out.println(theActualChange);
				chosenQuestion.setQuestionContent(theActualChange);
				session.update(chosenQuestion);
				break;
			case 2: // Edit one Answers
				System.out.println(answerNum);
				chosenQuestion.setAnswer(theActualChange, answerNum);
				System.out.println(chosenQuestion.getAnswer().get(answerNum - 1));
				session.update(chosenQuestion);
				break;
			case 3: // Edit the right answer
				chosenQuestion.setRightAnswer(Integer.parseInt(theActualChange));
				session.update(chosenQuestion);
				break;
			}

			session.flush();

			session.getTransaction().commit(); // Save everything.

			// System.out.println(chosenQuestion.toString());

			/*
			 * try { client.sendToClient(chosenQuestion); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

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

	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		// TODO Auto-generated method stub

		System.out.println("Client Disconnected.");
		super.clientDisconnected(client);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		System.out.println("Client connected: " + client.getInetAddress());
	}

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(HstsUser.class);
		configuration.addAnnotatedClass(Exam.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void addUsersToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			HstsUser student1 = new HstsUser("1111", "123456", 1, null, null);
			session.save(student1);
			session.flush();
			HstsUser student2 = new HstsUser("2222", "1234", 1, null, null);
			session.save(student2);
			session.flush();
			
			ArrayList<Integer> subjects = new ArrayList<Integer>();
			subjects.add(10);
			subjects.add(43);
			
			ArrayList<String> courses = new ArrayList<String>();
			courses.add("Hedva");
			courses.add("CS Intro");
			
			HstsUser teacher1 = new HstsUser("3333", "1234A", 2, subjects, courses);
			session.save(teacher1);
			session.flush();
			HstsUser principal = new HstsUser("4444", "123ABC", 3, null, null);
			session.save(principal);
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

	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			AppsServer server = new AppsServer(Integer.parseInt(args[0]));
			server.listen();
		}

		addUsersToDB();

	}

}
