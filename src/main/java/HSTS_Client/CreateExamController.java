package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.Exam;
import HSTS_Entities.HstsUser;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
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
import HSTS_Entities.Message;

public class CreateExamController implements Initializable {

    @FXML
    private Button log_out_btn;

    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button edit_question_btn;

    @FXML
    private Button edit_exam_btn;

    @FXML
    private Button main_page_btn;
	
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

	@FXML
	private ChoiceBox<String> chooseSubject;

	@FXML
	private ChoiceBox<String> chooseCourse;

	@FXML
	private Button save_btn;

	@FXML
	private Button show_question_btn;

	@FXML
	private VBox show_questions;

	@FXML
	private TextArea instructions_text;

	@FXML
	private HBox instructions_box;

	@FXML
	private TextField time_text;

	@FXML
	private Text for_multi_line;

	@FXML
	private TextArea notes_text;

	@FXML
	private Text notes;

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
	void save(ActionEvent event) {

		boolean badInput = false;
		
		if (chooseSubject.getSelectionModel().isEmpty()
				|| chooseSubject.getValue().equals("")) {
			chooseSubject.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (chooseCourse.getSelectionModel().isEmpty()
				|| chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (instructions_text.getText().isEmpty()) {
			instructions_text.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			instructions_text.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (time_text.getText().isEmpty() || !time_text.getText().matches("[0-9]+")) {
			time_text.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			time_text.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		ArrayList<Question> examQuestions = new ArrayList<Question>();
		ArrayList<Integer> questionsPoints = new ArrayList<Integer>();
		int chosenQuestions = 0;

		for (int j = 5; j < show_questions.getChildren().size() - 2; j++) {
			HBox chooseQuestion = (HBox) show_questions.getChildren().get(j);
			VBox questionBox = (VBox) chooseQuestion.getChildren().get(1);
			HBox gradesBox = (HBox) questionBox.getChildren().get(6);
			String questionPoints = ((TextField) gradesBox.getChildren().get(1)).getText();
			
			if (((CheckBox) (((HBox) show_questions.getChildren().get(j)).getChildren().get(0))).isSelected()) {
				chosenQuestions++;
				examQuestions.add(questions.get(j - 5));
				if (questionPoints.matches("[0-9]+") && Integer.valueOf(questionPoints) > 0
						&& Integer.valueOf(questionPoints) <= 100)
					questionsPoints.add(Integer.valueOf(questionPoints));
			}
		}
		if (chosenQuestions == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Please choose questions!");
			alert.setTitle("");
			alert.show();
			return;
		}

		else if (questionsPoints.size() != chosenQuestions) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Some of the questions points field\nare empty or illegal");
			alert.setTitle("");
			alert.show();
			return;
		}
		
		else 
		{
			int sumOfPoints = 0;
			for(int i = 0; i < questionsPoints.size(); i++)
			{
				sumOfPoints += questionsPoints.get(i);
			}
			
			if(sumOfPoints != 100)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Question Points must sum up to 100");
				alert.setTitle("");
				alert.show();
				return;
			}	
		}

		if (!badInput) {
			Exam newExam = new Exam(examQuestions, instructions_text.getText(), notes_text.getText(),
					user.getFullName(), Integer.valueOf(time_text.getText()), questionsPoints, chooseSubject.getValue(),
					chooseCourse.getValue());

			ArrayList<Exam> exams = new ArrayList<Exam>();
			exams.add(newExam);

			for (Question question : examQuestions) {
				question.setExams(exams);
			}
			newExam.setQuestions(examQuestions);

			Message msgToServer = new Message();

			msgToServer.setAction("Add Exam");
			msgToServer.setExam(newExam);

			try {
				AppsClient.getClient().sendToServer(msgToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block e.printStackTrace();
			}
		}
		
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red\nare empty or illegal");
			alert.setTitle("");
			// alert.setContentText("The fields marked red must be filled");
			alert.show();
		}
	}

	@FXML
	void showQuestions(ActionEvent event) {
		boolean badInput = false;

		if (chooseSubject.getSelectionModel().isEmpty()
				|| chooseSubject.getValue().equals("")) {
			chooseSubject.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (chooseCourse.getSelectionModel().isEmpty()
				|| chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (badInput == false) {
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

			while (show_questions.getChildren().get(6).getClass() != Button.class)
				show_questions.getChildren().remove(show_questions.getChildren().get(5));
			
			GridPane questionsGrid = new GridPane();
			questionsGrid.setAlignment(Pos.CENTER);
			show_questions.setVisible(true);
			show_questions.setSpacing(15);
			show_questions.setMargin(instructions_box, new Insets(0, 0, 10, 0));
				
			
			for (int i = 0; i < questions.size(); i++) {

				HBox chooseHB = new HBox();
				chooseHB.setAlignment(Pos.TOP_LEFT);
				chooseHB.setPadding(new Insets(0, 0, 0, 80));
				CheckBox chooseQuestion = new CheckBox();
				chooseHB.getChildren().add(chooseQuestion);
				Line line = new Line();
				line.setEndX(250);
				line.setStroke(Color.web("#3A495C"));
				line.setStrokeWidth(1);
				line.setStrokeLineCap(StrokeLineCap.ROUND);
				
				VBox questionBox = new VBox();
				TextFlow questionContent = new TextFlow();
				Text questionContent1 = new Text("" + (i + 1) + ".    ");
				Text questionContent2 = new Text(questions.get(i).getQuestionContent());
				Text answer1 = new Text("1. " + questions.get(i).getAnswer().get(0));
				Text answer2 = new Text("2. " + questions.get(i).getAnswer().get(1));
				Text answer3 = new Text("3. " + questions.get(i).getAnswer().get(2));
				Text answer4 = new Text("4. " + questions.get(i).getAnswer().get(3));
				questionContent1.setFont(Font.font("Century Gothic",FontWeight.BOLD, 14));
				questionContent1.setFill(Color.WHITE);
				questionContent2.setFont(Font.font("Century Gothic", 14));
				questionContent2.setFill(Color.WHITE);
				questionContent.getChildren().add(questionContent1);
				questionContent.getChildren().add(questionContent2);
				
				answer1.setFont(Font.font("Century Gothic", 14));
				answer1.setFill(Color.WHITE);
				answer1.setWrappingWidth(250);
				
				answer2.setFont(Font.font("Century Gothic", 14));
				answer2.setFill(Color.WHITE);
				answer2.setWrappingWidth(250);

				answer3.setFont(Font.font("Century Gothic", 14));
				answer3.setFill(Color.WHITE);
				answer3.setWrappingWidth(250);

				answer4.setFont(Font.font("Century Gothic", 14));
				answer4.setFill(Color.WHITE);
				answer4.setWrappingWidth(250);

				
				TextFlow rightAnswer = new TextFlow();
				rightAnswer.setPrefWidth(300);
				Text rightAnswer1 = new Text("    The right answer is: ");
				Text rightAnswer2 = new Text(String.valueOf(questions.get(i).getRightAnswer()));
				rightAnswer.getChildren().addAll(rightAnswer1, rightAnswer2);
				rightAnswer1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				rightAnswer2.setFont(Font.font("Century Gothic", 14));
				rightAnswer1.setFill(Color.WHITE);
				rightAnswer2.setFill(Color.WHITE);
				
				//Text rightAnswer = new Text(
					//	"The right answer is: " + String.valueOf(questions.get(i).getRightAnswer()));
				
				Text gradeText = new Text("    Add grade for chosen question: ");
				gradeText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				gradeText.setFill(Color.WHITE);
				
				TextField gradeTextField = new TextField();
				gradeTextField.setStyle("-fx-font-family: 'Century Gothic'; -fx-text-inner-color: white; -fx-background-color: #3F4E63");

				questionBox.getChildren().add(questionContent);
				questionBox.getChildren().add(answer1);
				questionBox.getChildren().add(answer2);
				questionBox.getChildren().add(answer3);
				questionBox.getChildren().add(answer4);
				questionBox.getChildren().add(rightAnswer);
				
				HBox gradesHB = new HBox();
				gradeTextField.setPrefWidth(50);
				gradeTextField.setMaxWidth(50);
				gradesHB.getChildren().add(gradeText);
				gradesHB.getChildren().add(gradeTextField);
				gradesHB.setSpacing(10);
				questionBox.getChildren().add(gradesHB);
				questionBox.getChildren().add(line);


				questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));
				questionBox.setMargin(rightAnswer, new Insets(0, 0, 0, 5));
				questionBox.setMargin(gradesHB, new Insets(0, 5, 0, 5));

				questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));

				questionBox.setSpacing(15);
				questionBox.setAlignment(Pos.TOP_LEFT);
				
				questionsGrid.setVgap(10);
				//questionBox.setStyle("-fx-background-color: #344152; -fx-background-radius: 10;");
				questionsGrid.add(questionBox, 0, i + 1, 1, 1);

				chooseHB.getChildren().add(questionBox);
				chooseHB.setSpacing(15);
				show_questions.getChildren().add(chooseHB);
			}

			HBox switchHBox = (HBox) show_questions.getChildren().remove(5);
			show_questions.getChildren().add(switchHBox);
			Button switchButton = (Button) show_questions.getChildren().remove(5);
			show_questions.getChildren().add(switchButton);
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
