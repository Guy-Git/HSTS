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

public class AppsServer extends AbstractServer {

	//out = new PrintWriter(socket.getOutputStream());
    //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    private static Session session;
    
	public AppsServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		if (msg.toString() == "Show All") 
			showAll();
	
	//	msg = "#Edit 56001 ChangeQuestion"
		if(msg.toString().startsWith("#Edit"))
		{
			if(msg.toString().contains("ChangeQuestion"))
				editQuestion(msg.toString().substring(6, 11), 1);
			
			if(msg.toString().contains("ChangeAnswer"))
				editQuestion(msg.toString().substring(6, 11), 2);
			
			if(msg.toString().contains("ChangeRightAnswer"))
				editQuestion(msg.toString().substring(6, 11), 3);
			
		}
			
		System.out.println("Received Message: " + msg.toString());
		sendToAllClients(msg);
	}

	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}

	public static void showAll() {
		List<Question> questions = getAll(Question.class);
		
		System.out.println("Course: " + questions.get(0).getCourse());
		System.out.println("Question id: " + questions.get(0).getQuestionID());
		System.out.println(questions.get(0).getQuestionContent());
		for(String answer : questions.get(0).getAnswer())
			System.out.println(answer);
		
		System.out.println("And the right answer is: " + questions.get(0).getRightAnswer());
	}
	
	public void editQuestion(String questionID, int change)
	{
		//You can change the question, answers, right answers, 
		List<Question> questions = getAll(Question.class);
		Question chosenQuestion;
		
		for(Question question: questions)
		{
			if(question.getQuestionID() == questionID)
				chosenQuestion = question;
		}
		
		switch(change)
		{
			case 1: //Edit question content
				//send("Enter changes for question");
		}
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

	public static void main(String[] args) throws IOException {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();

			if (args.length != 1) {
				System.out.println("Required argument: <port>");
			} else {
				AppsServer server = new AppsServer(Integer.parseInt(args[0]));
				server.listen();
			}

			ArrayList<Question> allQuestion = new ArrayList<Question>();
			ArrayList<String> answers = new ArrayList<String>();
			answers.add("1. My name is yacov");
			answers.add("2. My name is Arneiu");
			answers.add("3. My name is shmulek");
			answers.add("4. My name is opal");

			allQuestion.add(new Question("What's your name?", answers, 2, "Assi&Guri", 56));
			session.save(allQuestion.get(0));
			session.flush();

			showAll();

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
