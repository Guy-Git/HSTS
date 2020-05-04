package HSTS_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AppsCLI {
	private AppsClient client;
	private boolean isRunning;
	private static final String SHELL_STRING = "Choose action number >\n 1.Show all questions\n 2.Edit question\n 3.Exit\n ";
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
				while (client.isConnected()) {
					System.out.print(SHELL_STRING);

					try {
						message = reader.readLine();
						if (message.isBlank())
							continue;

						/*if(message.equals("1"))
							message = "#ShowAll";
						
						else if(message.equals("2"))
							message = reader.readLine();
						*/
						if (message.equalsIgnoreCase("Exit")) {
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
