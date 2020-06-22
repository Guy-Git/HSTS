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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowAllQuestionsController implements Initializable {
	
	@FXML
    private Button all_questions_btn;

    @FXML
    private Button about_btn;

    @FXML
    private Button main_page_btn;

    @FXML
    private Button log_out_btn;

    @FXML
    private Button executed_exams_btn;
    
    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button all_exams_btn;

    @FXML
    private Button time_ext_btn;

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
		
		if (event.getSource() == executed_exams_btn) {
			Stage stage = (Stage) executed_exams_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/PrincipalExecutedExams.fxml"));
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
//			if (event.getSource() == watch_reports_btn) 

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

	
	@Subscribe
	public void setQuestionsToPage(ArrayList<Question> questions) {
		this.questions = questions;
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			questions_box.setSpacing(15);
			questions_box.setAlignment(Pos.CENTER);

			for (int i = 0; i < questions.size(); i++) {

				VBox question = new VBox(15);

				HBox content = new HBox(15);
				content.setAlignment(Pos.TOP_LEFT);
				//content.setPadding(new Insets(0, 0, 0, 80));
				Label contentText = new Label("Question content: ");
				contentText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				//contentText.setFill(Color.WHITE);
				content.getChildren().add(contentText);
				
				Text contentTextArea = new Text(questions.get(i).getQuestionContent());
				contentTextArea.setStyle("-fx-text-color: white; -fx-font-size: 14;");
				contentTextArea.setFont(Font.font("Century Gothic", 14));
				contentTextArea.setFill(Color.WHITE);
				contentTextArea.setWrappingWidth(350);
				content.getChildren().add(contentTextArea);

				VBox answerBox = new VBox(15);
				Label answersText = new Label("               Answers: ");
				answersText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				//answersText.setFill(Color.WHITE);
				//answerBox.setMargin(answersText, new Insets(0, 265, 0, 0));
				answerBox.getChildren().add(answersText);
				answerBox.setAlignment(Pos.TOP_LEFT);
				ToggleGroup rightAnswer = new ToggleGroup();

				HBox answer1 = new HBox(15);
				answer1.setAlignment(Pos.TOP_LEFT);
				answer1.setPadding(new Insets(0, 0, 0, 130));			

				RadioButton rightAnswer1 = new RadioButton();
				rightAnswer1.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 1)
					rightAnswer1.setSelected(true);
				rightAnswer1.setDisable(true);
				Text answerNum1 = new Text("1.  ");
				answerNum1.setFont(Font.font("Century Gothic", 14));
				answerNum1.setFill(Color.WHITE);
				Text answer1TextField = new Text(questions.get(i).getAnswer().get(0));
				answer1TextField.setFont(Font.font("Century Gothic", 14));
				answer1TextField.setFill(Color.WHITE);
				answer1TextField.setWrappingWidth(300);
				
				answer1.getChildren().add(rightAnswer1);
				answer1.getChildren().add(answerNum1);
				answer1.getChildren().add(answer1TextField);

				HBox answer2 = new HBox(15);
				answer2.setAlignment(Pos.TOP_LEFT);
				answer2.setPadding(new Insets(0, 0, 0, 130));			

				RadioButton rightAnswer2 = new RadioButton();
				rightAnswer2.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 2)
					rightAnswer2.setSelected(true);
				rightAnswer2.setDisable(true);
				Text answerNum2 = new Text("2.  ");
				answerNum2.setFont(Font.font("Century Gothic", 14));
				answerNum2.setFill(Color.WHITE);
				Text answer2TextField = new Text(questions.get(i).getAnswer().get(1));
				answer2TextField.setFont(Font.font("Century Gothic", 14));
				answer2TextField.setFill(Color.WHITE);
				answer2TextField.setWrappingWidth(300);

				answer2.getChildren().add(rightAnswer2);
				answer2.getChildren().add(answerNum2);
				answer2.getChildren().add(answer2TextField);

				HBox answer3 = new HBox(15);
				answer3.setAlignment(Pos.TOP_LEFT);
				answer3.setPadding(new Insets(0, 0, 0, 130));			

				RadioButton rightAnswer3 = new RadioButton();
				rightAnswer3.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 3)
					rightAnswer3.setSelected(true);
				rightAnswer3.setDisable(true);
				Text answerNum3 = new Text("3.  ");
				answerNum3.setFont(Font.font("Century Gothic", 14));
				answerNum3.setFill(Color.WHITE);
				Text answer3TextField = new Text(questions.get(i).getAnswer().get(2));
				answer3TextField.setFont(Font.font("Century Gothic", 14));
				answer3TextField.setFill(Color.WHITE);
				answer3TextField.setWrappingWidth(300);

				answer3.getChildren().add(rightAnswer3);
				answer3.getChildren().add(answerNum3);
				answer3.getChildren().add(answer3TextField);

				HBox answer4 = new HBox(15);
				answer4.setAlignment(Pos.TOP_LEFT);
				answer4.setPadding(new Insets(0, 0, 0, 130));			

				RadioButton rightAnswer4 = new RadioButton();
				rightAnswer4.setToggleGroup(rightAnswer);
				if (questions.get(i).getRightAnswer() == 4)
					rightAnswer4.setSelected(true);
				rightAnswer4.setDisable(true);
				Text answerNum4 = new Text("4.  ");
				answerNum4.setFont(Font.font("Century Gothic", 14));
				answerNum4.setFill(Color.WHITE);
				Text answer4TextField = new Text(questions.get(i).getAnswer().get(3));
				answer4TextField.setFont(Font.font("Century Gothic", 14));
				answer4TextField.setFill(Color.WHITE);
				answer4TextField.setWrappingWidth(300);

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

