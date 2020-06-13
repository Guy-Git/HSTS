package HSTS_Client;

import java.io.IOException;

import HSTS_Entities.Message;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppsGUI extends Application {
	private AppsClient client;
	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		client = AppsClient.getClient();
		client.openConnection();
		
		Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/Login.fxml"));
		stage.setTitle("High School Test System");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void stop() {
		Message msg = new Message();
		msg.setAction("client disconnect");
		msg.setUser(AppsClient.getUser());
		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(AppsGUI.class.getResource(fxml + ".fxml‬"));
		return fxmlLoader.load();
	}

	public void displayMessage(Object message) {
	}

	public void closeConnection() {
		System.out.println("Connection closed.");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException {
		launch();
	}
}
