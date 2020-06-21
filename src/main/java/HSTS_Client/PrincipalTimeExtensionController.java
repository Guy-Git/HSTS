package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;
import HSTS_Entities.TimeExtension;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrincipalTimeExtensionController implements Initializable{

	private ArrayList<TimeExtension> timeExtensionsArr;
	
	private HstsUser user;

	 @FXML
	 private Button time_ext_btn;

	 @FXML
	 private Button watch_reports_btn;

	 @FXML
	 private Button about_btn;

	 @FXML
	 private Button log_out_btn;
	 
	 @FXML
	 private Button all_exams_btn;
    
	 @FXML
	 private Button all_questions_btn;

	 @FXML
	 private VBox time_ext_vbox;

	 @FXML
	 private HBox time_ext_hbox;

	 @FXML
	 private Button approve_btn;
	 
	 @FXML
	 private ScrollPane scroll_bar;
	 
	 @FXML
	 private Button main_page_btn;

	 @FXML
	 private Text time_ext_text;
	 
	 @FXML
	 private AnchorPane logo;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		Message msg = new Message();
		msg.setAction("show time extensions");
		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
				EventBus.getDefault().post(user);
				EventBus.getDefault().unregister(this);

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
				EventBus.getDefault().post(user);
				EventBus.getDefault().unregister(this);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (event.getSource() == about_btn) {
			Stage stage = (Stage) about_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalAbout.fxml"));
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
		
		if (event.getSource() == time_ext_btn) {
			Stage stage = (Stage) time_ext_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalTimeExtension.fxml"));
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
		
		if (event.getSource() == all_questions_btn) {
			Stage stage = (Stage) all_questions_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/ShowAllQuestions.fxml"));
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
//			if (event.getSource() == watch_reports_btn) 
	}
	
	@Subscribe
	public void onUserEvent(HstsUser user) {
		this.user = user;
	}
	
	@Subscribe
	public void onShowTimeExtensions(ArrayList<TimeExtension> timeExtensionsArr) {
		Platform.runLater(() -> {
			this.timeExtensionsArr = timeExtensionsArr;
			EventBus.getDefault().clearCaches();
			time_ext_vbox.getChildren().clear();
			
			GridPane timeExtensionGrid = new GridPane();
			timeExtensionGrid.setAlignment(Pos.CENTER);
			time_ext_vbox.setVisible(true);
			time_ext_vbox.setSpacing(15);
			time_ext_vbox.getChildren().clear();		
			
			if(timeExtensionsArr.isEmpty()) {
				time_ext_text.setText("No new time extension requests");
				time_ext_text.setFill(Color.WHITE);
				time_ext_text.setFont(Font.font ("Century Gothic", 16));
				time_ext_text.setTextAlignment(TextAlignment.CENTER);
				
				time_ext_vbox.getChildren().add(time_ext_text);
				time_ext_vbox.setAlignment(Pos.CENTER);
			}
			
			else {
				time_ext_text.setText("Active time extension requests");
				time_ext_text.setFont(Font.font ("Century Gothic", 20));
				time_ext_text.setFill(Color.WHITE);
				time_ext_text.setTextAlignment(TextAlignment.CENTER);
				
				Text warning = new Text("Please check requests you want to approve. \nUchecked requests are denied.\n");
				warning.setFont(Font.font ("Century Gothic", 14));
				warning.setFill(Color.web("#5dcfde"));
				warning.setTextAlignment(TextAlignment.CENTER);
				
				time_ext_vbox.getChildren().add(time_ext_text);
				time_ext_vbox.getChildren().add(warning);
				time_ext_vbox.setAlignment(Pos.CENTER);
			}
			
			for (int i = 0; i < timeExtensionsArr.size(); i++) {
				HBox chooseHB = new HBox();
				chooseHB.setAlignment(Pos.TOP_CENTER);
				CheckBox chooseTimeExtension = new CheckBox();
				chooseHB.getChildren().add(chooseTimeExtension);
				Text timeExtensionNumber = new Text("" + (i + 1) + ". ");
				timeExtensionNumber.setFont(Font.font("Century Gothic", 14));
				timeExtensionNumber.setFill(Color.WHITE);
				chooseHB.getChildren().add(timeExtensionNumber);
				Line line = new Line();
				line.setEndX(250);
				line.setStroke(Color.web("#3A495C"));
				line.setStrokeWidth(1);
				line.setStrokeLineCap(StrokeLineCap.ROUND);
				
				VBox timeExtensionsBox = new VBox();
				TextFlow examID = new TextFlow();
				examID.setPrefWidth(300);
				Text examID1 = new Text(" Exam ID: ");
				Text examID2 = new Text(timeExtensionsArr.get(i).getExamID());
				examID.getChildren().addAll(examID1, examID2);
				examID1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				examID2.setFont(Font.font("Century Gothic", 14));
				examID1.setFill(Color.WHITE);
				examID2.setFill(Color.WHITE);

				TextFlow subject = new TextFlow();
				subject.setPrefWidth(300);
				Text subject1 = new Text(" Subject: ");
				Text subject2 = new Text(timeExtensionsArr.get(i).getSubject());
				subject.getChildren().addAll(subject1, subject2);
				subject1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				subject2.setFont(Font.font("Century Gothic", 14));
				subject1.setFill(Color.WHITE);
				subject2.setFill(Color.WHITE);
				
				TextFlow course = new TextFlow();
				course.setPrefWidth(300);
				Text course1 = new Text(" Course: ");
				Text course2 = new Text(timeExtensionsArr.get(i).getCourse());
				course.getChildren().addAll(course1, course2);
				course1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				course2.setFont(Font.font("Century Gothic", 14));
				course1.setFill(Color.WHITE);
				course2.setFill(Color.WHITE);
				
				TextFlow reason = new TextFlow();
				reason.setPrefWidth(300);
				Text reason1 = new Text(" Reason for time extension: ");
				Text reason2 = new Text(timeExtensionsArr.get(i).getReason());
				reason.getChildren().addAll(reason1, reason2);
				reason1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				reason2.setFont(Font.font("Century Gothic", 14));
				reason2.setWrappingWidth(320);
				reason1.setFill(Color.WHITE);
				reason2.setFill(Color.WHITE);
				
				TextFlow requestedTime = new TextFlow();
				requestedTime.setPrefWidth(300);
				Text requestedTime1 = new Text(" Requested time (minutes): ");
				Text requestedTime2 = new Text("" + timeExtensionsArr.get(i).getRequestedTime());
				requestedTime.getChildren().addAll(requestedTime1, requestedTime2);
				requestedTime1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				requestedTime2.setFont(Font.font("Century Gothic", 14));
				requestedTime1.setFill(Color.WHITE);
				requestedTime2.setFill(Color.WHITE);

				timeExtensionsBox.getChildren().add(examID);
				timeExtensionsBox.getChildren().add(subject);
				timeExtensionsBox.getChildren().add(course);
				timeExtensionsBox.getChildren().add(reason);
				timeExtensionsBox.getChildren().add(requestedTime);
				timeExtensionsBox.getChildren().add(line);
				
				timeExtensionsBox.setSpacing(15);
				timeExtensionGrid.setVgap(10);
			//	timeExtensionsBox.setStyle("-fx-background-color: #3A495C; -fx-background-radius: 5;");
				timeExtensionGrid.add(timeExtensionsBox, 0, i + 1, 1, 1);

				chooseHB.getChildren().add(timeExtensionsBox);
				chooseHB.setSpacing(10);
				time_ext_vbox.getChildren().add(chooseHB);
			}
			
			if (!timeExtensionsArr.isEmpty()) {
				Text empty = new Text();
				approve_btn.setVisible(true);
				approve_btn.setFont(Font.font ("Century Gothic", 14));
				time_ext_vbox.getChildren().add(approve_btn);
				time_ext_vbox.getChildren().add(empty);
				time_ext_vbox.setAlignment(Pos.CENTER);
			}
		});
	}
	
	@FXML
	void approve(ActionEvent event) {
		for (int j = 0; j < time_ext_vbox.getChildren().size() - 4; j++) {
			if (((CheckBox) (((HBox) time_ext_vbox.getChildren().get(j + 2)).getChildren().get(0))).isSelected()) {
				timeExtensionsArr.get(j).setApproved(true);				
			}
			timeExtensionsArr.get(j).setStatus(false);
		}
	
		Message msg = new Message();
		msg.setTimeExtensionArr(timeExtensionsArr);
		msg.setAction("Update time extension requests");
		
		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Approved time extensions sent");
		alert.setTitle("");
		alert.show();
		
		alert.setOnCloseRequest(new EventHandler<DialogEvent>() 
		{
	        @Override
	        public void handle(DialogEvent event) {
	        	Stage stage = (Stage) approve_btn.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalMainPage.fxml"));
					stage.setTitle("High School Test System");
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					EventBus.getDefault().post(user);
					EventBus.getDefault().unregister(PrincipalTimeExtensionController.this);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    });
	}
	
	
}
