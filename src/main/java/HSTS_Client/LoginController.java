package HSTS_Client;

import java.io.IOException;

import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField username_text;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_text;

    @FXML
	void onClick(ActionEvent event) {
		
	HstsUser user= new HstsUser(username_text.getText(), password_text.getText(), 0, null, null);
	
	Message msg = new Message();
	msg.setUser(user);
	msg.setAction("Login");

	try {
		AppsClient.getClient().sendToServer(msg);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
    
    

}

