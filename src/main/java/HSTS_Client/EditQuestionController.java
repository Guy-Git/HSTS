package HSTS_Client;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class EditQuestionController implements Initializable {
	@FXML
	private ChoiceBox<String> chooseSubject;

	@FXML
	private ChoiceBox<String> chooseCourse;

	@FXML
	private Button show_question_btn;

	@FXML
	private VBox questions_box;

	@FXML
	private Accordion questions_container;

	@FXML
	private Button saveBtn;

	private HstsUser user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}

	@FXML
	void save(ActionEvent event) {

	}

	@FXML
	void showQuestions(ActionEvent event) 
	{
		boolean badInput = false;

		if (chooseSubject.getSelectionModel().isEmpty()
				|| chooseSubject.getValue().equals("")) {
			chooseSubject.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #00bfff");
		}

		if (chooseCourse.getSelectionModel().isEmpty()
				|| chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #00bfff");
		}

		if (badInput == false) {
			Message msg = new Message();
			msg.setSubject(chooseSubject.getValue());
			msg.setCourse(chooseCourse.getValue());
			msg.setAction("Show questions");

			try {
				AppsClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red must be filled");
			alert.setTitle("");
			// alert.setContentText("The fields marked red must be filled");
			alert.show();
		}
	}

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;
			ArrayList<String> subjects = new ArrayList<String>();
			ArrayList<String> courses = new ArrayList<String>();
			subjects = user.getSubjects();
			courses = user.getCourses();

			if (subjects.get(0) != null && courses.get(0) != "") {
				subjects.add(0, null);
				courses.add(0, "");
			}

			ObservableList<String> setToSubjects = FXCollections.observableArrayList(subjects);
			ObservableList<String> setToCourse = FXCollections.observableArrayList(courses);

			chooseSubject.setItems(setToSubjects);
			chooseCourse.setItems(setToCourse);
		});
	}
	
}
