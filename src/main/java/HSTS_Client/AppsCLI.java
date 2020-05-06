package HSTS_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AppsCLI {
	private AppsClient client;
	private boolean isRunning;
	private static int stopEditing = 0;

	private static final String SHELL_STRING = "Choose action number: \n" + "   1. To Show all questions - Enter '#1'\n"
			+ "   2. To Edit a question - Enter '#2'\n" + "   3. To Exit the system - Enter '#Exit'\n ";

	private Thread loopThread;

	public AppsCLI(AppsClient client) {
		this.client = client;
		this.isRunning = false;
	}

	public void loop() throws IOException {
		loopThread = new Thread(new Runnable() {

			@Override
			public void run() {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String message;
				System.out.println("Hello!\nWelcome to HSTS");
				System.out.print(SHELL_STRING);
				while (client.isConnected()) {

					try {
						message = reader.readLine();
						if (message.isBlank())
							continue;
						
						if (message.toString().equals("#M")) 
						{
							stopEditing = 1;
							System.out.println(SHELL_STRING);
							continue;
						}
						
						if (message.equals("#1")) {
							message = "#ShowAll";
						}

						else {
							if (message.startsWith("#2")) {
								System.out.println("Chosen question ID: ");
								message = "#2 " + reader.readLine();
							}

							else {
								if (message.startsWith("#CA")) {
									System.out.println("Choose the answer number to change: ");
									message = "#CA " + reader.readLine();
								}
							}
							System.out.println();
						}
						
						if (message.toString().contains("#M")) {
							stopEditing = 1;
							System.out.println(SHELL_STRING);
							continue;
						}
						
						if (message.equalsIgnoreCase("#Exit")) {
							System.out.println("Closing connection.");
							client.closeConnection();
						} else {
							client.sendToServer(message);
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		loopThread.start();
		this.isRunning = true;

	}

	public void displayMessage(Object message) {
		if (isRunning) {
			System.out.print("(Interrupted)\n");
		}
		System.out.println(message.toString());
		if (isRunning)
			System.out.print(SHELL_STRING);
	}

	public void closeConnection() {
		System.out.println("Connection closed.");
		System.exit(0);
	}

	public static int isEditing()
	{
		return stopEditing;
	}
	
	public static void setStopEditing()
	{
		stopEditing = 0;
	}
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Required arguments: <host> <port>");
		} else {
			String host = args[0];
			int port = Integer.parseInt(args[1]);

			AppsClient chatClient = new AppsClient(host, port);
			chatClient.openConnection();
		}
	}
}
