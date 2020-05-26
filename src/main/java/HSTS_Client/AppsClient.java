package HSTS_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import HSTS_Entities.Question;
import ocsf_Client.AbstractClient;

public class AppsClient extends AbstractClient {

	private static final String SHELL_STRING = "Choose action number: \n" + "   1. To Show all questions - Enter '#1'\n"
			+ "   2. To Edit a question - Enter '#2'\n" + "   3. To Exit the system - Enter '#Exit'\n"
			+ "Enter input: ";

	private static AppsClient client = null;
	
	private static final Logger LOGGER = Logger.getLogger(AppsClient.class.getName());
	
	public AppsClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
	}

	public static AppsClient getClient() {
		if (client == null) {
			client = new AppsClient("localhost", 3000);
		}
		return client;
	}
	
	@Override
	protected void handleMessageFromServer(Object msg) {
		/*String questionID;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		if (msg.getClass() == ArrayList.class) {
			try {
				ArrayList<Question> questions = (ArrayList<Question>) msg;
				showAll(questions);
				System.out.print(SHELL_STRING);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}

		if (msg.getClass() == String.class) {
			System.out.print(msg.toString());
			if (msg.toString().equals("Question not found! please try again: "))
				questionNotFound = true;
		}

		// System.out.println(msg);
		if (msg.getClass() == Question.class) {
			if (beforeOrAfterChange == 0 || AppsCLI.isEditing() == 1) {
				System.out.println("The chosen question is: ");
				ArrayList<Question> questions = new ArrayList<Question>();
				questions.add((Question) msg);
				showAll(questions);

				System.out.print("Choose action: \n" + "  1. Change question content - Enter '#CQ'\n"
						+ "  2. Choose an answer to change - Enter '#CA' \n"
						+ "  3. Change the right answer - Enter '#CRA' \n" + "  4. For Main Menu - '#M'\n"
						+ "Enter input: ");

				beforeOrAfterChange = 1;
				AppsCLI.setStopEditing();
			} else {
				System.out.println("The question after update: ");

				// System.out.println((Question)msg);
				ArrayList<Question> questions1 = new ArrayList<Question>();
				questions1.add((Question) msg);
				showAll(questions1);
				beforeOrAfterChange = 0;
				System.out.print(SHELL_STRING);
			}
		}*/
	}

	public static void showAll(ArrayList<Question> questions) {

		for (int i = 0; i < questions.size(); i++) {
			System.out.println("Course: " + questions.get(i).getCourse());
			System.out.println("Question id: " + questions.get(i).getQuestionID());
			System.out.println(questions.get(i).getQuestionContent());
			for (String answer : questions.get(i).getAnswer())
				System.out.println(answer);
			System.out.println("And the right answer is: " + questions.get(i).getRightAnswer());
			System.out.println();
		}
	}
}
