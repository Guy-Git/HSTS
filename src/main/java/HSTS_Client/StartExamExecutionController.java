package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.Exam;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartExamExecutionController implements Initializable {

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
	private Button show_question_btn;

	@FXML
	private Accordion exams_container;

	@FXML
	private TextField exam_code_text;

	@FXML
	private ToggleGroup isManual;

	@FXML
	private Button saveBtn;

	private HstsUser user;

	private ArrayList<Exam> exams;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);

		exams_container.getPanes().add(new TitledPane("Exam #KAKIFAKI", new Text("KAKIZ")));
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
		
		if (event.getSource() == exam_execution_btn) {
			Stage stage = (Stage) exam_execution_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StartExamExecution.fxml"));
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
	void pullExams(ActionEvent event) {
		Message msg = new Message();
		msg.setSubject(chooseSubject.getValue());
		msg.setCourse(chooseCourse.getValue());
		msg.setAction("Pull Exams");

		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void save(ActionEvent event) {

	}

	@Subscribe
	public void setExamsToPage(ArrayList<Exam> exams) {
		this.exams = exams;
		EventBus.getDefault().clearCaches();
		
		Platform.runLater(() -> 
		{
			for (int i = 0; i < exams.size(); i++) 
			{
				VBox displayExam = new VBox(15);
				displayExam.setAlignment(Pos.CENTER);
				Text instructions = new Text("Instructions: " + exams.get(i).getInstructions());
				Text notes = new Text("Notes: " + exams.get(i).getNotes());
				
				displayExam.getChildren().add(instructions);
				displayExam.getChildren().add(notes);
				
				GridPane questionsGrid = new GridPane();
				questionsGrid.setAlignment(Pos.CENTER);
				
				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) 
				{
					VBox questionBox = new VBox(15);

					Text questionContent = new Text("" + (j + 1) + ". " + exams.get(i).getQuestions().get(j).getQuestionContent());
					Text answer1 = new Text("1. " + exams.get(i).getQuestions().get(j).getAnswer().get(0));
					Text answer2 = new Text("2. " + exams.get(i).getQuestions().get(j).getAnswer().get(1));
					Text answer3 = new Text("3. " + exams.get(i).getQuestions().get(j).getAnswer().get(2));
					Text answer4 = new Text("4. " + exams.get(i).getQuestions().get(j).getAnswer().get(3));
					Text rightAnswer = new Text(
							"The right answer is: " + String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
					Text questionPoints = new Text("Add grade for chosen question: " + Integer.toString(exams.get(i).getQuestionGrade().get(j)));
	
					questionBox.getChildren().add(questionContent);
					questionBox.getChildren().add(answer1);
					questionBox.getChildren().add(answer2);
					questionBox.getChildren().add(answer3);
					questionBox.getChildren().add(answer4);
					questionBox.getChildren().add(rightAnswer);
					questionBox.getChildren().add(questionPoints);
	
					questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));
					questionBox.setMargin(rightAnswer, new Insets(0, 0, 0, 5));
					questionBox.setMargin(questionPoints, new Insets(0, 5, 0, 5));
	
					questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
					questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
					questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
					questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));
	
					questionBox.setSpacing(15);
	
					questionsGrid.setVgap(10);
					questionBox.setStyle("-fx-background-color: #ADD8E6");
					questionsGrid.add(questionBox, 0, i + 1, 1, 1);
					displayExam.getChildren().add(questionBox);
				}

				exams_container.getPanes().add(new TitledPane("Exam #" + exams.get(i).getExamID(), displayExam));
			}

//			while(show_questions.getChildren().get(3).getClass() != Button.class)
//				show_questions.getChildren().remove(show_questions.getChildren().get(2));
//			
//			GridPane questionsGrid = new GridPane();
//			questionsGrid.setAlignment(Pos.CENTER);
//			show_questions.setVisible(true);
//			show_questions.setMargin(instructions_box, new Insets(0, 0, 10, 0));
//
//			for (int i = 0; i < questions.size(); i++) {
//
//				HBox chooseHB = new HBox();
//				chooseHB.setAlignment(Pos.CENTER);
//				CheckBox chooseQuestion = new CheckBox();
//				chooseHB.getChildren().add(chooseQuestion);
//
//				VBox questionBox = new VBox();
//				Text questionContent = new Text("" + (i + 1) + ". " + questions.get(i).getQuestionContent());
//				Text answer1 = new Text("1. " + questions.get(i).getAnswer().get(0));
//				Text answer2 = new Text("2. " + questions.get(i).getAnswer().get(1));
//				Text answer3 = new Text("3. " + questions.get(i).getAnswer().get(2));
//				Text answer4 = new Text("4. " + questions.get(i).getAnswer().get(3));
//				Text rightAnswer = new Text(
//						"The right answer is: " + String.valueOf(questions.get(i).getRightAnswer()));
//				Text gradeText = new Text("Add grade for chosen question: ");
//				TextField gradeTextField = new TextField();
//
//				questionBox.getChildren().add(questionContent);
//				questionBox.getChildren().add(answer1);
//				questionBox.getChildren().add(answer2);
//				questionBox.getChildren().add(answer3);
//				questionBox.getChildren().add(answer4);
//				questionBox.getChildren().add(rightAnswer);
//
//				HBox gradesHB = new HBox();
//				gradeTextField.setPrefWidth(50);
//				gradeTextField.setMaxWidth(50);
//				gradesHB.getChildren().add(gradeText);
//				gradesHB.getChildren().add(gradeTextField);
//				gradesHB.setSpacing(10);
//				questionBox.getChildren().add(gradesHB);
//
//				questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));
//				questionBox.setMargin(rightAnswer, new Insets(0, 0, 0, 5));
//				questionBox.setMargin(gradesHB, new Insets(0, 5, 0, 5));
//
//				questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
//				questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
//				questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
//				questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));
//
//				questionBox.setSpacing(15);
//
//				questionsGrid.setVgap(10);
//				questionBox.setStyle("-fx-background-color: #ADD8E6");
//				questionsGrid.add(questionBox, 0, i + 1, 1, 1);
//
//				chooseHB.getChildren().add(questionBox);
//				chooseHB.setSpacing(15);
//				show_questions.getChildren().add(chooseHB);
//			}

//			HBox switchHBox = (HBox) show_questions.getChildren().remove(2);
//			show_questions.getChildren().add(switchHBox);
//			Button switchButton = (Button) show_questions.getChildren().remove(2);
//			show_questions.getChildren().add(switchButton);
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
