package HSTS_Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import HSTS_Entities.Message;
import HSTS_Entities.Question;
import HSTS_Entities.Exam;
import HSTS_Entities.ExamForExec;
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
	private ExamController examController;
	private ExamExecController examExecController;
	private TimeExtensionController timeExtensionController;
	private ExecutedExamController executedExamController;
	
	Message serverMsg;

	public AppsServer(int port) {
		super(port);
		questionController = new QuestionController();
		userController = new UserController();
		examController = new ExamController();
		examExecController = new ExamExecController();
		timeExtensionController = new TimeExtensionController();
		executedExamController = new ExecutedExamController();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		serverMsg = new Message();
		
		if(((Message)msg).getAction().equals("user log out"))  { // user log out without closing the client
			userController.clientDisconnect(((Message)msg).getUser());
		}
		
		if(((Message)msg).getAction().equals("user connected"))  {
			userController.connectUser(((Message)msg).getUser());
		}
		
		if(((Message)msg).getAction().equals("client disconnect"))   // user log out with closing the client
		{
			userController.clientDisconnect(((Message)msg).getUser());
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(((Message)msg).getAction().equals("Update time extension requests"))  
		{
			timeExtensionController.updateTimeExtensions(((Message)msg).getTimeExtensionArr());
		}
		
		if(((Message)msg).getAction().equals("show time extensions")) // principal 
		{
			serverMsg.setTimeExtensionArr(timeExtensionController.getTimeExtensions());
			serverMsg.setAction("got time extensions");
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(((Message)msg).getAction().equals("Pull Exams and Questions")) 
		{
			serverMsg.setExams(examController.getExams((Message)msg));
			serverMsg.setQuestions(questionController.getQuestions((Message)msg));
			serverMsg.setAction("Show Exams and Questions");
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(((Message)msg).getAction().equals("Request time extension")) // teacher
		{
			timeExtensionController.addTimeExtentionRequest(((Message)msg).getTimeExtension());
		}
		
		if(((Message)msg).getAction().equals("Add Exam"))
		{
			examController.addExam(((Message)msg).getExam());
		}
		
		if(((Message)msg).getAction().equals("Submit Student Exam"))
		{
			executedExamController.checkExam(((Message)msg).getStudentsExecutedExam());
		}
	
		if(((Message)msg).getAction().equals("Enter code") || ((Message)msg).getAction().equals("Pull exam by examCode"))
		{
			serverMsg.setExam(examExecController.getExamForExec(((Message)msg)));
			if(serverMsg.getExam() == null)
			{
				serverMsg.setAction("Exam code invalid");
			}
			else {
				serverMsg.setAction("Exam for exec");
			}
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if(((Message)msg).getAction().equals("Add exam for execution"))
		{
			examExecController.addExamForExec(((Message)(msg)).getExamForExec());
			executedExamController.addExectutedExam(((Message)msg).getExecutedExam());
		}
		
		if(((Message)msg).getAction().equals("Pull Exams"))
		{
			serverMsg.setExams(examController.getExams((Message)msg));
			serverMsg.setAction("Got Exams");

			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		if(((Message)msg).getAction().equals("Show Questions"))
		{
			serverMsg.setQuestions(questionController.getQuestions((Message)msg));
			serverMsg.setAction("Show Questions");
			
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		if(((Message)msg).getAction().equals("Create Question"))
		{
			questionController.addQuestion(((Message)msg).getQuestion());
		}
		
		if(((Message)msg).getAction().equals("Login"))
		{	
			HstsUser identifiedUser = userController.identification(((Message)msg).getUser());
			if(identifiedUser == null)
				serverMsg.setAction("Identification failed");
			else 
			{
				serverMsg.setAction("Identification succeed");
				serverMsg.setUser(identifiedUser);
			}
			
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*public void showAll(ConnectionToClient client) {
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

/*	public void findQuestion(String questionID, ConnectionToClient client) {
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

			
			  try { client.sendToClient(chosenQuestion); } catch (IOException e) { // TODO
			  Auto-generated catch block e.printStackTrace(); }
			 

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
	}*/

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
			
			String passwordInput = "123456";
		    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
		    byte[] digest = digestSHA3.digest(passwordInput.getBytes());
		    			    
			HstsUser student1 = new HstsUser("1111", Hex.encodeHexString(digest), 1, null, null, "Opal",false);

			session.save(student1);
			session.flush();
			
			passwordInput = "1234";
		    SHA3.DigestSHA3 digestSHA3_1 = new SHA3.Digest256();
		    byte[] digest1 = digestSHA3_1.digest(passwordInput.getBytes());
		    
			HstsUser student2 = new HstsUser("2222", Hex.encodeHexString(digest1), 1, null, null, "Guy",false);
			session.save(student2);
			session.flush();
			
			ArrayList<String> subjects = new ArrayList<String>();
			subjects.add("Math");
			subjects.add("CS");
			
			ArrayList<String> courses = new ArrayList<String>();
			courses.add("Calculus");
			courses.add("Introduction to CS");
			courses.add("OS");
			
			passwordInput = "1234A";
		    SHA3.DigestSHA3 digestSHA3_2 = new SHA3.Digest256();
		    byte[] digest2 = digestSHA3_2.digest(passwordInput.getBytes());
		    
			HstsUser teacher1 = new HstsUser("3333", Hex.encodeHexString(digest2), 2, subjects, courses, "Trachel",false);

			session.save(teacher1);
			session.flush();
			
			passwordInput = "1234A";
		    SHA3.DigestSHA3 digestSHA3_3 = new SHA3.Digest256();
		    byte[] digest3 = digestSHA3_3.digest(passwordInput.getBytes());
		    
			subjects.add("Biology");
			courses.add("Algebra 101");
			courses.add("Introduction to Probability");
			courses.add("Data structures");
			courses.add("OS");
			courses.add("Anatomy");
			courses.add("Stem Cells");
			courses.add("Biostructure");
			
			HstsUser principal = new HstsUser("4444", Hex.encodeHexString(digest3), 3, subjects, courses, "Chen",false);

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
