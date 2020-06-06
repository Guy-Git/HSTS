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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import HSTS_Entities.Message;

public class CreateExamController implements Initializable {

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

	private HstsUser user;

	private ArrayList<Question> questions;

	private boolean newPage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		for_multi_line.setText("Instructions for\nthe students:");
		for_multi_line.setWrappingWidth(80);
		newPage = true;
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

		if (event.getSource() == create_exam_btn) {
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

//		if (event.getSource() == exam_execution_btn) 
//		if (event.getSource() == watch_reports_btn) 
//		if (event.getSource() == about_btn) 

	}

	@FXML
	void save(ActionEvent event) {
		ArrayList<Question> examQuestions = new ArrayList<Question>();
		ArrayList<Integer> questionPoints = new ArrayList<Integer>();

		for (int j = 2; j < show_questions.getChildren().size() - 2; j++) {
			HBox chooseQuestion = (HBox) show_questions.getChildren().get(j);
			VBox questionBox = (VBox) chooseQuestion.getChildren().get(1);
			HBox gradesBox = (HBox) questionBox.getChildren().get(6);
			String grade = ((TextField) gradesBox.getChildren().get(1)).getText();

			if (((CheckBox) (((HBox) show_questions.getChildren().get(j)).getChildren().get(0))).isSelected()) {
				examQuestions.add(questions.get(j - 2));
				questionPoints.add(Integer.valueOf(grade));
			}
		}

		Exam newExam = new Exam(examQuestions, instructions_text.getText(), notes_text.getText(), user.getFullName(),
				Integer.valueOf(time_text.getText()), questionPoints, chooseSubject.getValue(),
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

	@FXML
	void showQuestions(ActionEvent event) {

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

	@Subscribe
	public void setQuestionsToPage(ArrayList<Question> questions) 
	{
		this.questions = questions;
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			
			while(show_questions.getChildren().get(3).getClass() != Button.class)
				show_questions.getChildren().remove(show_questions.getChildren().get(2));
			
			GridPane questionsGrid = new GridPane();
			questionsGrid.setAlignment(Pos.CENTER);
			show_questions.setVisible(true);
			show_questions.setMargin(instructions_box, new Insets(0, 0, 10, 0));

			for (int i = 0; i < questions.size(); i++) {

				HBox chooseHB = new HBox();
				chooseHB.setAlignment(Pos.CENTER);
				CheckBox chooseQuestion = new CheckBox();
				chooseHB.getChildren().add(chooseQuestion);

				VBox questionBox = new VBox();
				Text questionContent = new Text("" + (i + 1) + ". " + questions.get(i).getQuestionContent());
				Text answer1 = new Text("1. " + questions.get(i).getAnswer().get(0));
				Text answer2 = new Text("2. " + questions.get(i).getAnswer().get(1));
				Text answer3 = new Text("3. " + questions.get(i).getAnswer().get(2));
				Text answer4 = new Text("4. " + questions.get(i).getAnswer().get(3));
				Text rightAnswer = new Text(
						"The right answer is: " + String.valueOf(questions.get(i).getRightAnswer()));
				Text gradeText = new Text("Add grade for chosen question: ");
				TextField gradeTextField = new TextField();

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

				questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));
				questionBox.setMargin(rightAnswer, new Insets(0, 0, 0, 5));
				questionBox.setMargin(gradesHB, new Insets(0, 5, 0, 5));

				questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));

				questionBox.setSpacing(15);

				questionsGrid.setVgap(10);
				questionBox.setStyle("-fx-background-color: #ADD8E6");
				questionsGrid.add(questionBox, 0, i + 1, 1, 1);

				chooseHB.getChildren().add(questionBox);
				chooseHB.setSpacing(15);
				show_questions.getChildren().add(chooseHB);
			}

			HBox switchHBox = (HBox) show_questions.getChildren().remove(2);
			show_questions.getChildren().add(switchHBox);
			Button switchButton = (Button) show_questions.getChildren().remove(2);
			show_questions.getChildren().add(switchButton);
			newPage = false;
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
