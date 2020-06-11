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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrincipalTimeExtensionController implements Initializable{

	private ArrayList<TimeExtension> timeExtensionsArr;
	
	private HstsUser user;

	@FXML
	private Text enter_name_text;

	@FXML
	private Text time_text;

	@FXML
	private Button time_ext_btn;

	@FXML
	private Button watch_reports_btn;

	@FXML
	private Button about_btn;
	
	@FXML
	private VBox time_ext_vbox;
	
	@FXML
	private HBox time_ext_hbox;
	
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
			System.out.println("test");

		//	while(time_ext_vbox.getChildren().get(3).getClass() != Button.class)
			//	time_ext_vbox.getChildren().remove(time_ext_vbox.getChildren().get(2));
			
			GridPane timeExtensionGrid = new GridPane();
			timeExtensionGrid.setAlignment(Pos.CENTER);
			time_ext_vbox.setVisible(true);
			time_ext_vbox.setMargin(time_ext_hbox, new Insets(0, 0, 10, 0));
			
			for (int i = 0; i < timeExtensionsArr.size(); i++) {
				HBox chooseHB = new HBox();
				chooseHB.setAlignment(Pos.CENTER);
				CheckBox chooseTimeExtension = new CheckBox();
				chooseHB.getChildren().add(chooseTimeExtension);
				
				VBox timeExtensionsBox = new VBox();
				Text examID = new Text("" + (i + 1) + ". Exam ID: " + timeExtensionsArr.get(i).getExamID());
				Text subject = new Text(" Subject: " + timeExtensionsArr.get(i).getSubject());
				Text course = new Text(" Course: " + timeExtensionsArr.get(i).getCourse());
				Text reason = new Text(" Reason " + timeExtensionsArr.get(i).getReason());
				//Text requestedTime = new Text(" Requested time: " + timeExtensionsArr.get(i).getRequestedTime());
				Text requestedTime = new Text(" Requested Time: ");
				TextField time = new TextField();
				
				timeExtensionsBox.getChildren().add(examID);
				timeExtensionsBox.getChildren().add(subject);
				timeExtensionsBox.getChildren().add(course);
				timeExtensionsBox.getChildren().add(reason);
				timeExtensionsBox.getChildren().add(requestedTime);

				HBox gradesHB = new HBox(); // change name
				time.setPrefWidth(50);
				time.setMaxWidth(50);
				gradesHB.getChildren().add(requestedTime);
				gradesHB.getChildren().add(time);
				gradesHB.setSpacing(10);
				timeExtensionsBox.getChildren().add(gradesHB);

				timeExtensionsBox.setMargin(examID, new Insets(0, 0, 0, 5));
				timeExtensionsBox.setMargin(reason, new Insets(0, 0, 0, 5));
				timeExtensionsBox.setMargin(gradesHB, new Insets(0, 5, 0, 5));

				//timeExtensionsBox.setMargin(answer1, new Insets(0, 0, 0, 35));
				//timeExtensionsBox.setMargin(answer2, new Insets(0, 0, 0, 35));
				

				timeExtensionsBox.setSpacing(15);
				timeExtensionGrid.setVgap(10);
				timeExtensionsBox.setStyle("-fx-background-color: #ADD8E6");
				timeExtensionGrid.add(timeExtensionsBox, 0, i + 1, 1, 1);

				chooseHB.getChildren().add(timeExtensionsBox);
				chooseHB.setSpacing(15);
				time_ext_vbox.getChildren().add(chooseHB);
			}

		//	HBox switchHBox = (HBox) time_ext_vbox.getChildren().remove(2);
		//	time_ext_vbox.getChildren().add(switchHBox);
		//	Button switchButton = (Button) time_ext_vbox.getChildren().remove(2);
		//	time_ext_vbox.getChildren().add(switchButton);
		});
	}
}
