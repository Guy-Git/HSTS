package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrincipalAboutController implements Initializable {

	private HstsUser user;
	
	 @FXML
	 private Button executed_exams_btn;
	
    @FXML
    private Button about_btn;

    @FXML
    private Button log_out_btn;

    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button all_exams_btn;
    
    @FXML
    private Button all_questions_btn;
    
    @FXML
    private Button time_ext_btn;

    @FXML
    private Button watch_reports_btn;

    @FXML
    private Button main_page_btn;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
	}
    
    @FXML
	void menuClick(ActionEvent event) {
		if (event.getSource() == main_page_btn) {
			Stage stage = (Stage) main_page_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalMainPage.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == executed_exams_btn) {
			Stage stage = (Stage) executed_exams_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalExecutedExams.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == all_exams_btn) {
			Stage stage = (Stage) all_exams_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/ShowAllExams.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == time_ext_btn) {
			Stage stage = (Stage) time_ext_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalTimeExtension.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//			if (event.getSource() == watch_reports_btn) 

		if (event.getSource() == about_btn) {
			Stage stage = (Stage) about_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalAbout.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == all_questions_btn) {
			Stage stage = (Stage) all_questions_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/ShowAllQuestions.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == log_out_btn) {
			Stage stage = (Stage) log_out_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/Login.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
				Message msg = new Message();
				msg.setAction("user log out");
				msg.setUser(this.user);
				try {
					AppsClient.getClient().sendToServer(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
    
    @Subscribe
	public void onUserEvent(HstsUser user) {
		this.user = user;
	}

}
