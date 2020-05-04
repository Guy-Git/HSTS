package HSTS_Server;

import java.io.IOException;
import java.net.ServerSocket;
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

import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.server.AbstractServer;
import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.server.ConnectionToClient;
import net.bytebuddy.asm.Advice.This;

public class AppsServer extends AbstractServer {

	// out = new PrintWriter(socket.getOutputStream());
	// in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	private static Session session;
	private Question chosenQuestion;
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
		else if (msg.toString().startsWith("#Edit")) {
			findQuestion(msg.toString().substring(6, 11), client);
		}

		else if (msg.toString().startsWith("#ChangeQuestion")) {
			msg = "Enter changes for question";
			changeType = 1;
		}

		else if (msg.toString().startsWith("#ChangeAnswer")) {
			msg = "Enter the changes for answer: ";
			answerNum = Character.getNumericValue(msg.toString().charAt(msg.toString().length() - 1));
			changeType = 2;
		}

		else if (msg.toString().startsWith("#ChangeRightAnswer")) {
			msg = "Enter changes for question";
			changeType = 3;
		}

		else {
			editQuestion(chosenQuestion, msg.toString(), changeType, client);
		}

	}

	public void showAll(ConnectionToClient client) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			List<Question> questions = getAll(Question.class);
			try {
				System.out.println(questions.get(0));
				client.sendToClient(questions.get(0));
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
			System.out.println("KAKI");
			
			for (Question question : questions) { // Change to a better type of search!!!!!!!
				if (question.getQuestionID() == questionID)
					chosenQuestion = question;
			}

			msg = "Choose action: \n 1. Change Question \n 2. Choose an answer to change \n 3. Change the right answer";

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

	public void editQuestion(Question question, String theActualChange, int changeType, ConnectionToClient client) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			switch (changeType) {
			case 1: // Edit question content
				question.setQuestionContent(theActualChange);
				session.save(question);
				break;
			case 2: // Edit one Answers
				question.setAnswer(theActualChange, answerNum);
				session.save(question);
				break;
			case 3:
				question.setRightAnswer(Integer.parseInt(theActualChange));
				session.save(question);
				break;
			}
			session.flush();

			System.out.println("Question after updates: ");
			System.out.println("Course: " + question.getCourse());
			System.out.println("Question id: " + question.getQuestionID());
			System.out.println(question.getQuestionContent());
			for (String answer : question.getAnswer())
				System.out.println(answer);

			System.out.println("And the right answer is: " + question.getRightAnswer());

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

	public static void addQuestionToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			ArrayList<Question> allQuestion = new ArrayList<Question>();
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("1. My name is yacov");
			answers.add("2. My name is Arneiu");
			answers.add("3. My name is shmulek");
			answers.add("4. My name is opal");

			allQuestion.add(new Question("What's your name?", answers, 2, "Assi&Guri", 56));
			session.save(allQuestion.get(0));
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

		addQuestionToDB();

	}

}
