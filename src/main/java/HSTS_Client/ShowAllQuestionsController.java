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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShowAllQuestionsController implements Initializable {
	@FXML
	private ChoiceBox<String> chooseSubject;

	@FXML
	private ChoiceBox<String> chooseCourse;

	@FXML
	private VBox questions_box;

	@FXML
	private Accordion questions_container;

	@FXML
	private Button show_question_btn;


	private HstsUser user;

	private ArrayList<Question> questions;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}

	@FXML
	void showQuestions(ActionEvent event) {
		boolean badInput = false;

		if (chooseSubject.getSelectionModel().isEmpty() || chooseSubject.getValue().equals("")) {
			chooseSubject.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #00bfff");
		}

		if (chooseCourse.getSelectionModel().isEmpty() || chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #00bfff");
		}

		if (badInput == false) {
			questions_container.getPanes().clear();
			Message msg = new Message();
			msg.setSubject(chooseSubject.getValue());
			msg.setCourse(chooseCourse.getValue());
			msg.setAction("Show Questions");

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
	public void setQuestionsToPage(ArrayList<Question> questions) {
		this.questions = questions;
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {

			
			questions_box.setSpacing(15);
			questions_box.setAlignment(Pos.CENTER);

			for (int i = 0; i < questions.size(); i++) {

				VBox question = new VBox(5);

				HBox content = new HBox(5);
				content.setAlignment(Pos.CENTER);
				Text contentText = new Text("Question content:");
				content.getChildren().add(contentText);
				Text contentTextArea = new Text(questions.get(i).getQuestionContent());

				content.getChildren().add(contentTextArea);


				VBox answerBox = new VBox(5);

				answerBox.setAlignment(Pos.CENTER);
				Text answersText = new Text("Possible answers with the right one chosen: ");
			//	answerBox.setMargin(answersText, new Insets(0, 387, 0, 0));
				answerBox.getChildren().add(answersText);
			//	answerBox.setAlignment(Pos.CENTER);
				ToggleGroup rightAnswer = new ToggleGroup();


				HBox answer1 = new HBox(5);
				answer1.setAlignment(Pos.CENTER);
				RadioButton rightAnswer1 = new RadioButton();
				rightAnswer1.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 1) 
					rightAnswer1.setSelected(true);
				
				Text answerNum1 = new Text("1.");
				Text answer1TextField = new Text(questions.get(i).getAnswer().get(0));
				answer1.getChildren().add(rightAnswer1);
				answer1.getChildren().add(answerNum1);
				answer1.getChildren().add(answer1TextField);

				HBox answer2 = new HBox(5);
				answer2.setAlignment(Pos.CENTER);
				RadioButton rightAnswer2 = new RadioButton();
				rightAnswer2.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 2)
					rightAnswer2.setSelected(true);
				
				Text answerNum2 = new Text("2.");
				Text answer2TextField = new Text(questions.get(i).getAnswer().get(1));
				
				answer2.getChildren().add(rightAnswer2);
				answer2.getChildren().add(answerNum2);
				answer2.getChildren().add(answer2TextField);

				HBox answer3 = new HBox(5);
				answer3.setAlignment(Pos.CENTER);
				RadioButton rightAnswer3 = new RadioButton();
				rightAnswer3.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 3)
					rightAnswer3.setSelected(true);

				Text answerNum3 = new Text("3.");
				Text answer3TextField = new Text(questions.get(i).getAnswer().get(2));
				answer3.getChildren().add(rightAnswer3);
				answer3.getChildren().add(answerNum3);
				answer3.getChildren().add(answer3TextField);

				HBox answer4 = new HBox(5);
				answer4.setAlignment(Pos.CENTER);
				RadioButton rightAnswer4 = new RadioButton();
				rightAnswer4.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 4)
					rightAnswer4.setSelected(true);
				
				if(rightAnswer1.isSelected())
				{
					rightAnswer2.setDisable(true);
					rightAnswer3.setDisable(true);
					rightAnswer4.setDisable(true);
				}
				else	if(rightAnswer2.isSelected())
				{
					rightAnswer1.setDisable(true);
					rightAnswer3.setDisable(true);
					rightAnswer4.setDisable(true);
				}
				else if(rightAnswer3.isSelected())
				{
					rightAnswer1.setDisable(true);
					rightAnswer2.setDisable(true);
					rightAnswer4.setDisable(true);
				}
				else if(rightAnswer4.isSelected())
				{
					rightAnswer1.setDisable(true);
					rightAnswer2.setDisable(true);
					rightAnswer3.setDisable(true);
				}

				Text answerNum4 = new Text("4.");
				Text answer4TextField = new Text(questions.get(i).getAnswer().get(3));
				answer4.getChildren().add(rightAnswer4);
				answer4.getChildren().add(answerNum4);
				answer4.getChildren().add(answer4TextField);

				answerBox.getChildren().add(answer1);
				answerBox.getChildren().add(answer2);
				answerBox.getChildren().add(answer3);
				answerBox.getChildren().add(answer4);

				question.getChildren().add(content);
				question.getChildren().add(answerBox);

				questions_container.getPanes()
						.add(new TitledPane("Question #" + questions.get(i).getQuestionID(), question));
			}

		});
	}

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;
			ArrayList<String> subjects = new ArrayList<String>();
			ArrayList<String> courses = new ArrayList<String>();
			subjects = user.getSubjects();
			courses = user.getCourses();

			if (subjects.get(0) != "" && courses.get(0) != "") {
				subjects.add(0, "");
				courses.add(0, "");
			}

			ObservableList<String> setToSubjects = FXCollections.observableArrayList(subjects);
			ObservableList<String> setToCourse = FXCollections.observableArrayList(courses);

			chooseSubject.setItems(setToSubjects);
			chooseCourse.setItems(setToCourse);
		});
	}

}

