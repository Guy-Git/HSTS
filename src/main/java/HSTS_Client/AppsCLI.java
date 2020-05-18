package HSTS_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppsCLI {
	private AppsClient client;
	private boolean isRunning;
	private static int stopEditing = 1;
	private int badInput = 0;
	private boolean startEdit = false;
	private boolean isCRA = false;

	private static final String SHELL_STRING = "Choose action number: \n" + "   1. To Show all questions - Enter '#1'\n"
			+ "   2. To Edit a question - Enter '#2'\n" + "   3. To Exit the system - Enter '#Exit'\n" + "Enter input: ";

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
				String message = "";
				String questionID = "";
				String answerNumber = "";
				System.out.println("Hello!\nWelcome to HSTS");
				System.out.print(SHELL_STRING);
				while (client.isConnected()) {

					try {
						message = reader.readLine();
						
						if (message.isBlank())
							continue;

						if (message.toString().equals("#M")) {
							stopEditing = 1;
							badInput = 0;
							startEdit = false;
							isCRA = false;
							AppsClient.questionNotFound = false;
							System.out.println();
							System.out.print(SHELL_STRING);
							AppsClient.setBeforeOrAfterChange();
							continue;
						}

						if (message.equals("#1")) {
							message = "#ShowAll";
						}
						
						else {
							if (message.equals("#2") || AppsClient.questionNotFound == true) {
								startEdit = true;
								if(AppsClient.questionNotFound == false)
								{
									System.out.println();
									System.out.print("Choose question ID: ");
									questionID = reader.readLine();
								}
								else
									questionID = message;
								
								while((!questionID.matches("[0-9]+") || questionID.length() != 5))
								{
									if(questionID.equals("#M"))
										break;
									
									System.out.println();
									System.out.print("Invalid input! try again: ");
									questionID = reader.readLine();
								}
								stopEditing = 0;
								if(questionID.contains("#M"))
									message = "#M";
								else
									message = "#2 " + questionID;
								AppsClient.questionNotFound = false;
							}

							else {
								if (message.equals("#CA")) {
									startEdit = false;
									System.out.println();
									System.out.print("Choose the answer number to change: ");
									answerNumber = reader.readLine();
									
									while((!answerNumber.matches("[1-4]+") || answerNumber.length() != 1))
									{
										if(answerNumber.equals("#M"))
											break;
										
										System.out.println();
										System.out.print("Invalid input! try again: ");
										answerNumber = reader.readLine();
									}
									stopEditing = 0;
									if(answerNumber.contains("#M"))
										message = "#M";
									else
										message = "#CA " + answerNumber;
								}

								else
								{
									if(message.equals("#CQ")) {
										stopEditing = 0;
										startEdit = false;						
									}
									else
									{
										if(message.equals("#CRA")) {
											stopEditing = 0;
											startEdit = false;
											isCRA = true;
										}
										else
										{
											if(startEdit == true)
											{
												System.out.println();
												System.out.print("Invalid action! try again: ");
												//System.out.println();
												continue;
											}
											
											if(isCRA)
											{
												if(!message.matches("[1-4]+") || message.length() != 1)
												{
													System.out.println();
													System.out.print("Invalid input! try again: ");
													continue;
												}
												else
													isCRA = false;
											}
											if(stopEditing == 1)
												badInput = 1;
										}
									}
								}
									
							}
							System.out.println();
						}

						if (message.toString().equals("#M")) {
							stopEditing = 1;
							System.out.println();
							System.out.print(SHELL_STRING);
							badInput = 0;
							startEdit = false;
							isCRA = false;
							AppsClient.setBeforeOrAfterChange();
							AppsClient.questionNotFound = false;
							continue;
						}

						if (message.equalsIgnoreCase("#Exit")) {
							System.out.println("Closing connection.");
							client.closeConnection();
						} else {
							if (badInput == 0 || stopEditing == 0) {
								client.sendToServer(message);
							}
							
							else 
							{	
								System.out.print("Invalid action! try again: ");
								System.out.println("jgmjr");
								System.out.print(SHELL_STRING);
								badInput = 0;
							}
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

	public static int isEditing() {
		return stopEditing;
	}

	public static void setStopEditing() {
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
