package HSTS_Server;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.server.AbstractServer;
import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.server.ConnectionToClient;

public class AppsServer extends AbstractServer {

	private static Session session;

	public AppsServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client)
	{
		if(msg.toString() == "Show All")
		{
			
		}
		System.out.println("Received Message: " + msg.toString());
		sendToAllClients(msg);
	}
	
	protected void showAll()
	{
		
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
			answers.add("My name is yacov");
			answers.add("My name is Arneiu");
			answers.add("My name is shmulek");
			answers.add("My name is opal");
			
			allQuestion.add(new Question("What's your name?", answers, 2, "Assi&Guri", 12));
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
}
