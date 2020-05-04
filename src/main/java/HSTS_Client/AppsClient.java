package HSTS_Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import HSTS_Server.Question;
import antlr.collections.List;
import il.ac.haifa.cs.sweng.OCSFSimpleChat.ocsf.client.AbstractClient;

public class AppsClient extends AbstractClient {
	private static final Logger LOGGER =
			Logger.getLogger(AppsClient.class.getName());
	
	private AppsCLI chatClientCLI;	
	public AppsClient(String host, int port) {
		super(host, port);
		this.chatClientCLI = new AppsCLI(this);
	}
	
	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
		
		try {
			chatClientCLI.loop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleMessageFromServer(Object msg) 
	{
		
		//Question question = (Question)msg;
		//showAll(question);
		
		System.out.println(msg.toString());
	}
	
	public static void showAll(Question question) {
		
		System.out.println("Course: " + question.getCourse());
		System.out.println("Question id: " + question.getQuestionID());
		System.out.println(question.getQuestionContent());
		for (String answer : question.getAnswer())
			System.out.println(answer);

		System.out.println("And the right answer is: " + question.getRightAnswer());
	}
	
	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		super.connectionClosed();
		chatClientCLI.closeConnection();
	}
}
