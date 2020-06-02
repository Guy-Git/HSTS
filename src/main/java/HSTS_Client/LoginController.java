package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.mysql.cj.xdevapi.Client;

import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private TextField username_text;

	@FXML
	private Button login_btn;

	@FXML
	private PasswordField password_text;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	
	}
	
	@FXML
	void onClick(ActionEvent event) {
		HstsUser user = new HstsUser(username_text.getText(), password_text.getText(), 0, null, null);
		Message msg = new Message();
		msg.setAction("Login");
		msg.setUser(user);
		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Subscribe
	public void onMessageEvent(Message recieved) 
	{
		Platform.runLater(() -> {
			if (recieved.getAction().equals("Identification failed")) 
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("ID or password are incorrect! \nTry again!");
				alert.setTitle("");
				// alert.setContentText("The fields marked red must be filled");
				alert.show();
			} 
			
			else 
			{
				Stage stage = (Stage) login_btn.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/CreateExam.fxml"));
					stage.setTitle("High School Test System");
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					EventBus.getDefault().post(((Message)recieved).getUser());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
