package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

public class CreateQuestionController implements Initializable {

	@FXML
	private TextArea contentText;

	@FXML
	private RadioButton rightAnswer1;

	@FXML
	private ToggleGroup right_answer;

	@FXML
	private RadioButton rightAnswer2;

	@FXML
	private RadioButton rightAnswer3;

	@FXML
	private RadioButton rightAnswer4;

	@FXML
	private TextArea answer1Text;

	@FXML
	private TextArea answer2Text;

	@FXML
	private TextArea answer3Text;

	@FXML
	private TextArea answer4Text;

	@FXML
	private Button saveBtn;

	@FXML
	private Button clearBtn;

	@FXML
	private ChoiceBox<Integer> chooseSubject;

	@FXML
	private ChoiceBox<String> chooseCourse;

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

	private HstsUser user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
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

		if (event.getSource() == create_exam_btn) 
		{
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
		
//			if (event.getSource() == exam_execution_btn) 
//			if (event.getSource() == watch_reports_btn) 
//			if (event.getSource() == about_btn) 

	}

	@FXML
	void save(ActionEvent event) {
		ArrayList<String> answers = new ArrayList<String>();
		Question question;
		int rightAnswer = 0;
		boolean badInput = false;

		if (chooseSubject.getSelectionModel().getSelectedItem() == null
				|| chooseSubject.getSelectionModel().isEmpty()) {
			chooseSubject.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #00bfff");
		}

		if (chooseCourse.getSelectionModel().getSelectedItem().equals("")
				|| chooseCourse.getSelectionModel().isEmpty()) {
			chooseCourse.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #00bfff");
		}

		if (contentText.getText().isEmpty()) {
			contentText.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			contentText.setStyle("-fx-background-color: #00bfff");
		}

		if (answer1Text.getText().isEmpty()) {
			answer1Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			answer1Text.setStyle("-fx-background-color: #00bfff");
		}

		if (answer2Text.getText().isEmpty()) {
			answer2Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			answer2Text.setStyle("-fx-background-color: #00bfff");
		}

		if (answer3Text.getText().isEmpty()) {
			answer3Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			answer3Text.setStyle("-fx-background-color: #00bfff");
		}

		if (answer4Text.getText().isEmpty()) {
			answer4Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			answer4Text.setStyle("-fx-background-color: #00bfff");
		}

		if (badInput == false) {
			if (right_answer.getSelectedToggle() == rightAnswer1) {
				rightAnswer = 1;
			} else if (right_answer.getSelectedToggle() == rightAnswer2) {
				rightAnswer = 2;
			} else if (right_answer.getSelectedToggle() == rightAnswer3) {
				rightAnswer = 3;
			} else if (right_answer.getSelectedToggle() == rightAnswer4) {
				rightAnswer = 4;
			}

			answers.add(answer1Text.getText());
			answers.add(answer2Text.getText());
			answers.add(answer3Text.getText());
			answers.add(answer4Text.getText());

			question = new Question(contentText.getText(), answers, rightAnswer, chooseCourse.getValue(),
					chooseSubject.getValue());

			Message msg = new Message();
			msg.setQuestion(question);
			msg.setAction("Create Question");

			try {
				AppsClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red must be filled");
			alert.setTitle("");
			// alert.setContentText("The fields marked red must be filled");
			alert.show();
		}
	}

	@FXML
    void clear(ActionEvent event) {
		contentText.setText("");
		answer1Text.setText("");
		answer2Text.setText("");
		answer3Text.setText("");
		answer4Text.setText("");
		right_answer.selectToggle(rightAnswer1);
		chooseSubject.setValue(null);
		chooseCourse.setValue("");
    }

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;
			ArrayList<Integer> subjects = new ArrayList<Integer>();
			ArrayList<String> courses = new ArrayList<String>();
			subjects = user.getSubjects();
			courses = user.getCourses();
			
			if (subjects.get(0) != null && courses.get(0) != "") {
				subjects.add(0, null);
				courses.add(0, "");
			}

			ObservableList<Integer> setToSubjects = FXCollections.observableArrayList(subjects);
			ObservableList<String> setToCourse = FXCollections.observableArrayList(courses);

			chooseSubject.setItems(setToSubjects);
			chooseCourse.setItems(setToCourse);
		});
	}
}
