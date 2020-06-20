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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditQuestionController implements Initializable {
	
	@FXML
    private Button about_btn;

    @FXML
    private Button log_out_btn;

    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button create_question_btn;

    @FXML
    private Button edit_question_btn;

    @FXML
    private Button create_exam_btn;

    @FXML
    private Button edit_exam_btn;

    @FXML
    private Button exam_execution_btn;

    @FXML
    private Button watch_reports_btn;

    @FXML
    private Button main_page_btn;

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
				EventBus.getDefault().unregister(this);

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
				EventBus.getDefault().unregister(this);

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
				EventBus.getDefault().unregister(this);

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
				EventBus.getDefault().unregister(this);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			if (event.getSource() == edit_question_btn) {
				Stage stage = (Stage) edit_question_btn.getScene().getWindow();
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
			
			if (event.getSource() == main_page_btn) {
				Stage stage = (Stage) main_page_btn.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherMainPage.fxml"));
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
					Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherAbout.fxml"));
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
	}
	
	@FXML
	void showQuestions(ActionEvent event) {
		boolean badInput = false;

		if (chooseSubject.getSelectionModel().isEmpty() || chooseSubject.getValue().equals("")) {
			chooseSubject.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
		}

		if (chooseCourse.getSelectionModel().isEmpty() || chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
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
			chooseSubject.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
		}

		if (chooseCourse.getSelectionModel().isEmpty() || chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
		}

		String content = ((TextArea) ((HBox) ((VBox) questionToEdit.getContent()).getChildren().get(0)).getChildren()
				.get(1)).getText();

		if (content.isEmpty()) {
			((TextArea) ((HBox) ((VBox) questionToEdit.getContent()).getChildren().get(0)).getChildren().get(1))
					.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			((TextArea) ((HBox) ((VBox) questionToEdit.getContent()).getChildren().get(0)).getChildren().get(1))
					.setStyle("-fx-inner-background-color: #1E242E; -fx-text-inner-color: white;  -fx-background-radius: 10;");
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
						.getChildren().get(i)).getChildren().get(2)).setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
				badInput = true;
			} else {
				((TextField) ((HBox) ((VBox) ((VBox) questionToEdit.getContent()).getChildren().get(1))
						.getChildren().get(i)).getChildren().get(2)).setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
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
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Question Edited successfully");
			alert.setTitle("");
			// alert.setContentText("The fields marked red must be filled");
			alert.show();
			
			alert.setOnCloseRequest(new EventHandler<DialogEvent>() 
			{
		        @Override
		        public void handle(DialogEvent event) {
		        	Stage stage = (Stage) save_btn.getScene().getWindow();
					try {
						Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherMainPage.fxml"));
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
		    });
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
			if(!questions.isEmpty())
				save_btn.setVisible(true);
			questions_box.setSpacing(15);
			questions_box.setAlignment(Pos.CENTER);

			for (int i = 0; i < questions.size(); i++) {

				VBox question = new VBox(15);

				HBox content = new HBox(15);
				content.setAlignment(Pos.TOP_LEFT);
				Text contentText = new Text("Enter question content: ");
				contentText.setFont(Font.font("Century Gothic", 14));
				contentText.setFill(Color.WHITE);
				content.getChildren().add(contentText);
				TextArea contentTextArea = new TextArea(questions.get(i).getQuestionContent());
				contentTextArea.setStyle("-fx-text-inner-color: white; -fx-font-size: 14; -fx-text-box-border: transparent;");
				
				contentTextArea.setWrapText(true);
				contentTextArea.setPrefWidth(360);
				contentTextArea.setPrefHeight(80);
				content.getChildren().add(contentTextArea);

				VBox answerBox = new VBox(15);
				Text answersText = new Text("Enter answers and choose the right one: ");
				answersText.setFont(Font.font("Century Gothic", 14));
				answersText.setFill(Color.WHITE);
				answerBox.setMargin(answersText, new Insets(0, 0, 0, 0));
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
				answer1TextField.setFont(Font.font("Century Gothic", 14));
				answer1TextField.setStyle("-fx-text-inner-color: white; -fx-text-box-border: transparent;");
				
				
				answer1.getChildren().add(rightAnswer1);
				answer1.getChildren().add(answerNum1);
				answer1.getChildren().add(answer1TextField);

				HBox answer2 = new HBox(15);
				answer2.setAlignment(Pos.CENTER);
				RadioButton rightAnswer2 = new RadioButton();
				rightAnswer2.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 2)
					rightAnswer2.setSelected(true);
				Text answerNum2 = new Text("2.");
				TextField answer2TextField = new TextField(questions.get(i).getAnswer().get(1));
				answer2TextField.setFont(Font.font("Century Gothic", 14));
				answer2TextField.setStyle("-fx-text-inner-color: white; -fx-text-box-border: transparent;");
				
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
				answer3TextField.setFont(Font.font("Century Gothic", 14));
				answer3TextField.setStyle("-fx-text-inner-color: white; -fx-text-box-border: transparent;");
				
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
				answer4TextField.setFont(Font.font("Century Gothic", 14));
				answer4TextField.setStyle("-fx-text-inner-color: white; -fx-text-box-border: transparent;");
				
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
				save_btn.setLayoutX(40);
				//questions_box.setMargin(save_btn, new Insets(0, 0, 0, 20));
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
