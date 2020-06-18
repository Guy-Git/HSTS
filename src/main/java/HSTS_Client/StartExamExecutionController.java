package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.google.protobuf.Duration;

import HSTS_Entities.Exam;
import HSTS_Entities.ExamForExec;
import HSTS_Entities.ExecutedExam;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;
import HSTS_Entities.StudentsExecutedExam;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
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
	private RadioButton com_exam;

	@FXML
	private ToggleGroup isManual;

	@FXML
	private RadioButton manual_exam;

	@FXML
	private Button saveBtn;

	@FXML
	private VBox exams_box;

	private HstsUser user;

	private ArrayList<Exam> exams;

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

		EventBus.getDefault().unregister(this);
	}

	@FXML
	void pullExams(ActionEvent event) {

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

		if (!badInput) {
			exams_container.getPanes().clear();
			Message msgToServer = new Message();
			msgToServer.setSubject(chooseSubject.getValue());
			msgToServer.setCourse(chooseCourse.getValue());
			msgToServer.setAction("Pull Exams");

			try {
				AppsClient.getClient().sendToServer(msgToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red must be filled");
			alert.setTitle("");
			alert.show();
		}
	}

	@FXML
	void save(ActionEvent event) {
		boolean examType;

		if (exams_container.getExpandedPane() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("You must pick at least one exam!");
			alert.setTitle("");
			alert.show();
			return;
		}

		else if (exam_code_text.getText().length() != 4 || !exam_code_text.getText().matches("[a-zA-Z0-9]*")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Exam code must consist of 4 letters or numbers!");
			alert.setTitle("");
			alert.show();
			return;
		}

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

		if (!badInput) {

			if (isManual.getSelectedToggle() == manual_exam)
				examType = true;
			else
				examType = false;

			ExamForExec newExamForExec = new ExamForExec(exams_container.getExpandedPane().getText().substring(6),
					examType, exam_code_text.getText());

			Message msgToServer = new Message();

			ExecutedExam newExecutedExam = new ExecutedExam();
			newExecutedExam.setExamCode(exam_code_text.getText());
			
			newExecutedExam.setExamID(exams_container.getExpandedPane().getText().substring(6));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String time = simpleDateFormat.format(new Date());
			newExecutedExam.setTimeAndDate(time);
			newExecutedExam.setAssignedBy(user.getUserId());

			msgToServer.setAction("Add exam for execution");
			msgToServer.setExamForExec(newExamForExec);
			msgToServer.setExecutedExam(newExecutedExam);
			
			try {
				AppsClient.getClient().sendToServer(msgToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Stage stage = (Stage) exam_execution_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherExamExecution.fxml"));
				stage.setTitle("High School Test System");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

				EventBus.getDefault().post(user);
				EventBus.getDefault().post(newExamForExec);
				EventBus.getDefault().unregister(this);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red must be filled");
			alert.setTitle("");
			alert.show();
		}
	}

	@Subscribe
	public void setExamsToPage(ArrayList<Exam> exams) {
		this.exams = exams;
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			exams_box.setVisible(true);
			for (int i = 0; i < exams.size(); i++) {
				VBox displayExam = new VBox(15);
				displayExam.setAlignment(Pos.CENTER);
				Text instructions = new Text("Instructions: " + exams.get(i).getInstructions());
				Text notes = new Text("Notes: " + exams.get(i).getNotes());

				displayExam.getChildren().add(instructions);
				displayExam.getChildren().add(notes);

				GridPane questionsGrid = new GridPane();
				questionsGrid.setAlignment(Pos.CENTER);

				// System.out.println(exams.get(1).getInstructions());

				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) {
					VBox questionBox = new VBox(15);

					Text questionContent = new Text(
							"" + (j + 1) + ". " + exams.get(i).getQuestions().get(j).getQuestionContent());
					Text answer1 = new Text("1. " + exams.get(i).getQuestions().get(j).getAnswer().get(0));
					Text answer2 = new Text("2. " + exams.get(i).getQuestions().get(j).getAnswer().get(1));
					Text answer3 = new Text("3. " + exams.get(i).getQuestions().get(j).getAnswer().get(2));
					Text answer4 = new Text("4. " + exams.get(i).getQuestions().get(j).getAnswer().get(3));
					Text rightAnswer = new Text("The right answer is: "
							+ String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
					Text questionPoints = new Text("Add grade for chosen question: "
							+ Integer.toString(exams.get(i).getQuestionGrade().get(j)));

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

				Text examDuration = new Text("Exam duration is: " + exams.get(i).getExamTime() + " Minutes");

				displayExam.getChildren().add(examDuration);

				exams_container.getPanes().add(new TitledPane("Exam #" + exams.get(i).getExamID(), displayExam));
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
