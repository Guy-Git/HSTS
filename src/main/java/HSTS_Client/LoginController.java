package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
import javafx.scene.layout.Background;
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
		boolean badInput = false;

		if (username_text.getText().isEmpty()) {
			username_text.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10");
			badInput = true;
		} else {
			username_text.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (password_text.getText().isEmpty()) {
			password_text.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10");
			badInput = true;
		} else {
			password_text.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (badInput == false) {
			HstsUser user = new HstsUser(username_text.getText(), password_text.getText(), 0, null, null, "", false);
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
		
		else 
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red must be filled");
			alert.setTitle("");
			alert.show();
		}
	}

	@Subscribe
	public void onMessageEvent(Message recieved) {
		Platform.runLater(() -> {
			if (recieved.getAction().equals("Identification failed")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("ID or password are incorrect! \nTry again!");
				alert.setTitle("");
				// alert.setContentText("The fields marked red must be filled");
				alert.show();
			}

			else {
				if (recieved.getUser().getConnectionStatus() == true) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("User already connected!");
					alert.setTitle("");
					alert.show();
				}

				else {
					if (recieved.getUser().getUserType() == 1) {
						Stage stage = (Stage) login_btn.getScene().getWindow();
						try {
							Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentMainPage.fxml"));
							stage.setTitle("High School Test System");
							Scene scene = new Scene(root);
							stage.setScene(scene);
							stage.show();
							EventBus.getDefault().post(((Message) recieved).getUser());
							EventBus.getDefault().unregister(this);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if (recieved.getUser().getUserType() == 2) {
						Stage stage = (Stage) login_btn.getScene().getWindow();
						try {
							Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherMainPage.fxml"));
							stage.setTitle("High School Test System");
							Scene scene = new Scene(root);
							stage.setScene(scene);
							stage.show();
							EventBus.getDefault().post(((Message) recieved).getUser());
							EventBus.getDefault().unregister(this);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if (recieved.getUser().getUserType() == 3) {
						Stage stage = (Stage) login_btn.getScene().getWindow();
						try {
							Parent root = FXMLLoader
									.load(getClass().getResource("/HSTS_Client/PrincipalMainPage.fxml"));
							stage.setTitle("High School Test System");
							Scene scene = new Scene(root);
							stage.setScene(scene);
							stage.show();
							EventBus.getDefault().post(((Message) recieved).getUser());
							EventBus.getDefault().unregister(this);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Message msg = new Message();
					msg.setAction("user connected");
					msg.setUser(((Message) recieved).getUser());
					try {
						AppsClient.getClient().sendToServer(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
	}
}
