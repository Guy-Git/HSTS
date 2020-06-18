package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import HSTS_Entities.HstsUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeacherMainPageController implements Initializable {

	@FXML
	private Text enter_name_text;

	@FXML
	private Button create_question_btn;

	@FXML
	private Button create_exam_btn;

	@FXML
	private Button exam_execution_btn;

	@FXML
	private Button watch_reports_btn;

	@FXML
	private Button about_btn;

	@FXML
	private Text time_text;
	
    @FXML
    private Button edit_exam_btn;
    
    @FXML
    private Button review_btn;

	private HstsUser user;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		EventBus.getDefault().register(this);
		
		Thread timerThread = new Thread(() -> 
		{
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        while (true) {
	            final String time = simpleDateFormat.format(new Date());
	            Platform.runLater(() -> {
	                time_text.setText(time);
	            });
	            try {
	            	Thread.sleep(1000); //1 second
	            } catch (InterruptedException e) {
	            	e.printStackTrace();
	            }
	        }
	    });   
		timerThread.start();
	}

	@FXML
	void menuClick(ActionEvent event) {
		if (event.getSource() == create_question_btn) {
			Stage stage = (Stage) create_question_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/CreateQuestion.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				EventBus.getDefault().post(user);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (event.getSource() == create_exam_btn) {
			Stage stage = (Stage) create_exam_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/CreateExam.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				EventBus.getDefault().post(user);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == exam_execution_btn) {
			Stage stage = (Stage) exam_execution_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StartExamExecution.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				EventBus.getDefault().post(user);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == edit_exam_btn) {
			Stage stage = (Stage) edit_exam_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/EditExam.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				EventBus.getDefault().post(user);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == review_btn) {
			Stage stage = (Stage) review_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/ExamsReview.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				EventBus.getDefault().post(user);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//			if (event.getSource() == exam_execution_btn) 
//			if (event.getSource() == watch_reports_btn) 
			if (event.getSource() == about_btn) 
			{
				Stage stage = (Stage) about_btn.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/EditQuestion.fxml"));
					stage.setTitle("High School Test System");
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					EventBus.getDefault().post(user);
					EventBus.getDefault().unregister(this);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}

	@Subscribe
	public void onUserEvent(HstsUser user) {
		this.user = user;
		enter_name_text.setText(enter_name_text.getText() + user.getFullName());
	}

}
