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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

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
	 private VBox time_ext_vbox;

	 @FXML
	 private HBox time_ext_hbox;

	 @FXML
	 private Button approve_btn;
	 
	 @FXML
	 private ScrollPane scroll_bar;
	
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
		if (event.getSource() == time_ext_btn) {
			Stage stage = (Stage) time_ext_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalTimeExtension.fxml"));
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
		
		if (event.getSource() == log_out_btn) {
			Stage stage = (Stage) time_ext_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/Login.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				EventBus.getDefault().post(user);
				
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
//			if (event.getSource() == about_btn) 
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
				Text noTimeExt = new Text("no new time extensions");
				noTimeExt.setFill(Color.WHITE);
				noTimeExt.setFont(Font.font ("Century Gothic", 16));
				time_ext_vbox.getChildren().add(noTimeExt);
				time_ext_vbox.setAlignment(Pos.CENTER);
			}
			
			else {
				Text timeExt = new Text("active time extension requests");
				Text warning = new Text("unapproved time extensions are denied");
				timeExt.setFont(Font.font ("Century Gothic", 18));
				warning.setFont(Font.font ("Century Gothic", 14));
				timeExt.setFill(Color.WHITE);
				warning.setFill(Color.RED);

				time_ext_vbox.getChildren().add(timeExt);
				time_ext_vbox.getChildren().add(warning);
				time_ext_vbox.setAlignment(Pos.CENTER);
			}
			
			for (int i = 0; i < timeExtensionsArr.size(); i++) {
				HBox chooseHB = new HBox();
				chooseHB.setAlignment(Pos.TOP_CENTER);
				CheckBox chooseTimeExtension = new CheckBox();
				chooseHB.getChildren().add(chooseTimeExtension);
				Text timeExtensionNumber = new Text("" + (i + 1) + ". ");
				timeExtensionNumber.setFill(Color.WHITE);
				chooseHB.getChildren().add(timeExtensionNumber);
				
				VBox timeExtensionsBox = new VBox();
				TextFlow examID = new TextFlow();
				examID.setPrefWidth(300);
				Text examID1 = new Text(" Exam ID: ");
				Text examID2 = new Text(timeExtensionsArr.get(i).getExamID());
				examID.getChildren().addAll(examID1, examID2);
				examID1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12));
				examID2.setFont(Font.font("Century Gothic", 12));
				examID1.setFill(Color.WHITE);
				examID2.setFill(Color.WHITE);

				TextFlow subject = new TextFlow();
				subject.setPrefWidth(300);
				Text subject1 = new Text(" Subject: ");
				Text subject2 = new Text(timeExtensionsArr.get(i).getSubject());
				subject.getChildren().addAll(subject1, subject2);
				subject1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12));
				subject2.setFont(Font.font("Century Gothic", 12));
				subject1.setFill(Color.WHITE);
				subject2.setFill(Color.WHITE);
				
				TextFlow course = new TextFlow();
				course.setPrefWidth(300);
				Text course1 = new Text(" Course: ");
				Text course2 = new Text(timeExtensionsArr.get(i).getCourse());
				course.getChildren().addAll(course1, course2);
				course1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12));
				course2.setFont(Font.font("Century Gothic", 12));
				course1.setFill(Color.WHITE);
				course2.setFill(Color.WHITE);
				
				TextFlow reason = new TextFlow();
				reason.setPrefWidth(300);
				Text reason1 = new Text(" Reason for time extension: ");
				Text reason2 = new Text(timeExtensionsArr.get(i).getReason());
				reason.getChildren().addAll(reason1, reason2);
				reason1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12));
				reason2.setFont(Font.font("Century Gothic", 12));
				reason2.setWrappingWidth(320);
				reason1.setFill(Color.WHITE);
				reason2.setFill(Color.WHITE);
				
				TextFlow requestedTime = new TextFlow();
				requestedTime.setPrefWidth(300);
				Text requestedTime1 = new Text(" Requested time: ");
				Text requestedTime2 = new Text("" + timeExtensionsArr.get(i).getRequestedTime());
				requestedTime.getChildren().addAll(requestedTime1, requestedTime2);
				requestedTime1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 12));
				requestedTime2.setFont(Font.font("Century Gothic", 12));
				requestedTime1.setFill(Color.WHITE);
				requestedTime2.setFill(Color.WHITE);

				timeExtensionsBox.getChildren().add(examID);
				timeExtensionsBox.getChildren().add(subject);
				timeExtensionsBox.getChildren().add(course);
				timeExtensionsBox.getChildren().add(reason);
				timeExtensionsBox.getChildren().add(requestedTime);

				timeExtensionsBox.setSpacing(20);
				timeExtensionGrid.setVgap(10);
				timeExtensionsBox.setStyle("-fx-background-color:  #3C3E63; -fx-background-radius: 5;");
				timeExtensionGrid.add(timeExtensionsBox, 0, i + 1, 1, 1);

				chooseHB.getChildren().add(timeExtensionsBox);
				chooseHB.setSpacing(30);
				time_ext_vbox.getChildren().add(chooseHB);
			}
			
			if (!timeExtensionsArr.isEmpty()) {
				Text empty = new Text();
				approve_btn.setVisible(true);
				approve_btn.setFont(Font.font ("Century Gothic", 12));
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
	}
	
	
}
