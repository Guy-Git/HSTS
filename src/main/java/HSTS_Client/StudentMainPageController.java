package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentMainPageController implements Initializable {

	@FXML
    private Button about_btn;

    @FXML
    private Button main_page_btn;

    @FXML
    private Button log_out_btn;

    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button start_exam_btn;

    @FXML
    private Button exam_grades_btn;

    @FXML
    private Text message_text;

    @FXML
    private Text time_text;

    @FXML
    private Text enter_name_text;

    private HstsUser user;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	EventBus.getDefault().register(this);
    	
    	Thread timerThread = new Thread(() -> {
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

		if (event.getSource() == main_page_btn) 
		{
			Stage stage = (Stage) main_page_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentMainPage.fxml"));
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
		
		if (event.getSource() == about_btn) 
		{
			Stage stage = (Stage) about_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentAbout.fxml"));
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
		
		if (event.getSource() == start_exam_btn) 
		{
			Stage stage = (Stage) start_exam_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentExamExecution.fxml"));
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
		
		if (event.getSource() == log_out_btn) {
			Stage stage = (Stage) log_out_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/Login.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				EventBus.getDefault().post(user);
				EventBus.getDefault().unregister(this);

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

//			if (event.getSource() == exam_grades_btn) 

	}

	@Subscribe
	public void onUserEvent(HstsUser user) 
	{
		this.user = user;
		enter_name_text.setText(enter_name_text.getText() + user.getFullName());
		
		LocalDateTime localDateTime = LocalDateTime.now();
		if (localDateTime.getHour() >= 1 && localDateTime.getHour() <= 12)
			message_text.setText("Good Morning, ");
		if (localDateTime.getHour() > 12 && localDateTime.getHour() <= 18)
			message_text.setText("Good Afternoon, ");
		if (localDateTime.getHour() > 18)
			message_text.setText("Good Evening, ");
	}

}
