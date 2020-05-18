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

import HSTS_Entities.Question;
import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.server.AbstractServer;
import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.server.ConnectionToClient;

public class AppsServer extends AbstractServer {

	// out = new PrintWriter(socket.getOutputStream());
	// in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	private static Session session;
	private Question chosenQuestion;
	private int isFound = 0;
	private int changeType;
	private int answerNum;
	static SessionFactory sessionFactory = getSessionFactory();

	public AppsServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		System.out.println("Received Message: " + msg.toString());

		if (msg.toString().equals("#ShowAll")) {
			showAll(client);
		}

		// msg = #Edit 56001
		// msg = #ChangeQuestion
		// msg = "What's your Last name?"
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
				if (!msg.toString().equals("#M")) {
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
		}
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
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void addQuestionsToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			ArrayList<Question> allQuestion = new ArrayList<Question>();

			// Question 1:
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("1. My name is yacov");
			answers.add("2. My name is Arneiu");
			answers.add("3. My name is shmulek");
			answers.add("4. My name is opal");

			allQuestion.add(new Question("What's your name?", answers, 2, "Assi&Guri", 56));
			session.save(allQuestion.get(0));

			// Question 2:
			ArrayList<String> answers1 = new ArrayList<String>();
			answers1.add("1. 4");
			answers1.add("2. 6");
			answers1.add("3. 7");
			answers1.add("4. 2222");

			allQuestion.add(new Question("2 + 2 =", answers1, 1, "Math", 2));
			session.save(allQuestion.get(1));

			// Question 3:
			ArrayList<String> answers2 = new ArrayList<String>();
			answers2.add("1. 5");
			answers2.add("2. 78");
			answers2.add("3. 4");
			answers2.add("4. 2222");

			allQuestion.add(new Question("2 x 2 =", answers2, 3, "Math", 2));
			session.save(allQuestion.get(2));

			// Question 4:
			ArrayList<String> answers3 = new ArrayList<String>();
			answers3.add("1. Java");
			answers3.add("2. C (YAK)");
			answers3.add("3. C++");
			answers3.add("4. Python");

			allQuestion.add(new Question("What is the current best programming language?", answers3, 1, "CS", 10));
			session.save(allQuestion.get(3));

			// Question 5:
			ArrayList<String> answers4 = new ArrayList<String>();
			answers4.add("1. A cat scratched her (She's a cat lady)");
			answers4.add("2. She hitpatzla to 3");
			answers4.add("3. She was hit by a bus");
			answers4.add("4. She hitpatzla to 2");

			allQuestion.add(new Question("What happend to the zkena?", answers4, 4, "Assi&Guri", 56));
			session.save(allQuestion.get(4));

			// Question 6:
			ArrayList<String> answers5 = new ArrayList<String>();
			answers5.add("1. Jerusalem");
			answers5.add("2. Haifa");
			answers5.add("3. Tel-Aviv");
			answers5.add("4. Rishon");

			allQuestion.add(new Question("What's the capital of Israel?", answers5, 1, "Geography", 76));
			session.save(allQuestion.get(5));

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

		addQuestionsToDB();

	}

}
