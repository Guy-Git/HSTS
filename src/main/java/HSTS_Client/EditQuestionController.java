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

public class EditQuestionController implements Initializable {
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

	@FXML
	private Button save_btn;

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

	@FXML
	void save(ActionEvent event) {
		TitledPane questionToEdit = questions_container.getExpandedPane();
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

		String content = ((TextArea) ((HBox) ((VBox) questionToEdit.getContent()).getChildren().get(0)).getChildren()
				.get(1)).getText();

		if (content.isEmpty()) {
			((TextArea) ((HBox) ((VBox) questionToEdit.getContent()).getChildren().get(0)).getChildren().get(1))
					.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			((TextArea) ((HBox) ((VBox) questionToEdit.getContent()).getChildren().get(0)).getChildren().get(1))
					.setStyle("-fx-background-color: #00bfff");
		}

		ArrayList<String> answers = new ArrayList<String>();

		int rightAnswer = 0;

		for (int i = 1; i < ((VBox) ((VBox) questionToEdit.getContent()).getChildren().get(1)).getChildren()
				.size(); i++) 
		{
			if (((TextField) ((HBox) ((VBox) ((VBox) questionToEdit.getContent()).getChildren().get(1))
					.getChildren().get(i)).getChildren().get(2)).getText().isEmpty()) 
			{
				((TextField) ((HBox) ((VBox) ((VBox) questionToEdit.getContent()).getChildren().get(1))
						.getChildren().get(i)).getChildren().get(2)).setStyle("-fx-background-color: RED");
				badInput = true;
			} else {
				((TextField) ((HBox) ((VBox) ((VBox) questionToEdit.getContent()).getChildren().get(1))
						.getChildren().get(i)).getChildren().get(2)).setStyle("-fx-background-color: #00bfff");
			}
			
			answers.add(((TextField) ((HBox) ((VBox) ((VBox) questionToEdit.getContent()).getChildren().get(1))
					.getChildren().get(i)).getChildren().get(2)).getText());

			if (((RadioButton) ((HBox) ((VBox) ((VBox) questionToEdit.getContent()).getChildren().get(1)).getChildren()
					.get(i)).getChildren().get(0)).isSelected()) {
				rightAnswer = i;
			}
		}

		if (badInput == false) {
			Question newEditedQuestion = new Question(content, answers, rightAnswer, chooseCourse.getValue(),
					chooseSubject.getValue());
			Message msgToServer = new Message();
			msgToServer.setQuestion(newEditedQuestion);
			msgToServer.setAction("Create Question");

			try {
				AppsClient.getClient().sendToServer(msgToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block e.printStackTrace();
			}
		}
		
		else 
		{
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

			save_btn.setVisible(true);
			questions_box.setSpacing(15);
			questions_box.setAlignment(Pos.CENTER);

			for (int i = 0; i < questions.size(); i++) {

				VBox question = new VBox(15);

				HBox content = new HBox(15);
				content.setAlignment(Pos.TOP_LEFT);
				Text contentText = new Text("Enter question content: ");
				content.getChildren().add(contentText);
				TextArea contentTextArea = new TextArea(questions.get(i).getQuestionContent());
				contentTextArea.setWrapText(true);
				contentTextArea.setPrefWidth(380);
				contentTextArea.setPrefHeight(80);
				content.getChildren().add(contentTextArea);

				VBox answerBox = new VBox(15);
				Text answersText = new Text("Enter answers and choose the right one: ");
				answerBox.setMargin(answersText, new Insets(0, 387, 0, 0));
				answerBox.getChildren().add(answersText);
				answerBox.setAlignment(Pos.CENTER);
				ToggleGroup rightAnswer = new ToggleGroup();

				HBox answer1 = new HBox(15);
				answer1.setAlignment(Pos.CENTER);
				RadioButton rightAnswer1 = new RadioButton();
				rightAnswer1.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 1)
					rightAnswer1.setSelected(true);
				Text answerNum1 = new Text("1.");
				TextField answer1TextField = new TextField(questions.get(i).getAnswer().get(0));
				answer1.getChildren().add(rightAnswer1);
				answer1.getChildren().add(answerNum1);
				answer1.getChildren().add(answer1TextField);

				HBox answer2 = new HBox(15);
				answer2.setAlignment(Pos.CENTER);
				RadioButton rightAnswer2 = new RadioButton();
				rightAnswer2.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 2)
					rightAnswer1.setSelected(true);
				Text answerNum2 = new Text("2.");
				TextField answer2TextField = new TextField(questions.get(i).getAnswer().get(1));
				answer2.getChildren().add(rightAnswer2);
				answer2.getChildren().add(answerNum2);
				answer2.getChildren().add(answer2TextField);

				HBox answer3 = new HBox(15);
				answer3.setAlignment(Pos.CENTER);
				RadioButton rightAnswer3 = new RadioButton();
				rightAnswer3.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 3)
					rightAnswer3.setSelected(true);
				Text answerNum3 = new Text("3.");
				TextField answer3TextField = new TextField(questions.get(i).getAnswer().get(2));
				answer3.getChildren().add(rightAnswer3);
				answer3.getChildren().add(answerNum3);
				answer3.getChildren().add(answer3TextField);

				HBox answer4 = new HBox(15);
				answer4.setAlignment(Pos.CENTER);
				RadioButton rightAnswer4 = new RadioButton();
				rightAnswer4.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 4)
					rightAnswer4.setSelected(true);
				Text answerNum4 = new Text("4.");
				TextField answer4TextField = new TextField(questions.get(i).getAnswer().get(3));
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

			if (questions_box.getChildren().size() == 1)
			{
				questions_box.getChildren().add(save_btn);
				questions_box.setMargin(save_btn, new Insets(0, 0, 15, 0));
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
