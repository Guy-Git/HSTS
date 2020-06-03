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

public class StudentMainPageController implements Initializable {

    @FXML
    private Text enter_name_text;

    @FXML
    private Text time_text;

    @FXML
    private Button exam_execution_btn;

    @FXML
    private Button watch_reports_btn;

    @FXML
    private Button about_btn;
    
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

//			if (event.getSource() == exam_execution_btn) 
//			if (event.getSource() == watch_reports_btn) 
//			if (event.getSource() == about_btn) 

	}

	@Subscribe
	public void onUserEvent(HstsUser user) 
	{
		this.user = user;
		enter_name_text.setText(enter_name_text.getText() + user.getFullName());
	}

}
