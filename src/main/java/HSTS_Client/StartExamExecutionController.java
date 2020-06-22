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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class StartExamExecutionController implements Initializable {

	@FXML
	private Button about_btn;

	@FXML
	private Button executed_exams_btn;

	@FXML
	private Button review_btn;

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
	private Button main_page_btn;

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

		if (event.getSource() == review_btn) {
			Stage stage = (Stage) review_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/ExamsReview.fxml"));
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
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherShowExecutedExams.fxml"));
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
	void pullExams(ActionEvent event) {

		boolean badInput = false;

		if (chooseSubject.getSelectionModel().isEmpty() || chooseSubject.getValue().equals("")) {
			chooseSubject.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseSubject
					.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
		}

		if (chooseCourse.getSelectionModel().isEmpty() || chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseCourse
					.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
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
		Message msgToServer = new Message();

		msgToServer.setAction("Check exam code - teacher");
		msgToServer.setExecCode(exam_code_text.getText());

		try {
			AppsClient.getClient().sendToServer(msgToServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void checkCode(Message msg) {
		Platform.runLater(() -> {

			if (msg.getAction().equals("code used")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("The code is already used,\nplease choose another one");
				alert.setTitle("");
				alert.show();
			}

			else {
				checkAndSend();
			}
		});
	}

	public void checkAndSend() {
		boolean examType;

		if (exams_container.getExpandedPane() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("You must pick an one exam!");
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

		else if (com_exam.isSelected() == false && manual_exam.isSelected() == false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Choose manual or computerized exam!");
			alert.setTitle("");
			alert.show();
			return;
		}

		boolean badInput = false;

		if (chooseSubject.getSelectionModel().isEmpty() || chooseSubject.getValue().equals("")) {
			chooseSubject.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseSubject
					.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
		}

		if (chooseCourse.getSelectionModel().isEmpty() || chooseCourse.getValue().equals("")) {
			chooseCourse.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			chooseCourse
					.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
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
			if (exams.size() != 0)
				exams_box.setVisible(true);
			else
				exams_box.setVisible(false);

			for (int i = 0; i < exams.size(); i++) {
				VBox displayExam = new VBox(15);
				displayExam.setAlignment(Pos.CENTER);

				HBox instructionsHBox = new HBox();
				instructionsHBox.setSpacing(10);
				instructionsHBox.setAlignment(Pos.TOP_CENTER);
				Label instructions = new Label("Instructions: ");
				instructions.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				Text instructionsArea = new Text(exams.get(i).getInstructions());
				instructionsArea.setFont(Font.font("Century Gothic", 14));
				instructionsArea.setFill(Color.WHITE);
				instructionsArea.setWrappingWidth(300);
				instructionsHBox.getChildren().addAll(instructions, instructionsArea);
				instructionsHBox.setMargin(instructionsArea, new Insets(0, 34, 0, 0));
				instructionsHBox.setPadding(new Insets(0, 0, 0, 78));

				HBox notesHBox = new HBox(10);
				notesHBox.setAlignment(Pos.TOP_CENTER);
				Label notes = new Label("Notes: ");
				notes.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				Text notesArea = new Text(exams.get(i).getNotes());
				notesArea.setFont(Font.font("Century Gothic", 14));
				notesArea.setFill(Color.WHITE);
				notesArea.setWrappingWidth(300);
				notesHBox.getChildren().addAll(notes, notesArea);
				notesHBox.setPadding(new Insets(0, 0, 0, 78));

				displayExam.getChildren().add(instructionsHBox);
				displayExam.getChildren().add(notesHBox);

				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) {
					VBox questionBox = new VBox(15);
					HBox questionHBox = new HBox(15);
					questionHBox.setAlignment(Pos.TOP_LEFT);
					questionHBox.setPadding(new Insets(0, 0, 0, 150));

					TextFlow questionContent = new TextFlow();
					Text questionContent1 = new Text("" + (j + 1) + ".    ");
					Text questionContent2 = new Text(exams.get(i).getQuestions().get(j).getQuestionContent());
					Text answer1 = new Text("1. " + exams.get(i).getQuestions().get(j).getAnswer().get(0));
					Text answer2 = new Text("2. " + exams.get(i).getQuestions().get(j).getAnswer().get(1));
					Text answer3 = new Text("3. " + exams.get(i).getQuestions().get(j).getAnswer().get(2));
					Text answer4 = new Text("4. " + exams.get(i).getQuestions().get(j).getAnswer().get(3));

					answer1.setWrappingWidth(250);
					answer2.setWrappingWidth(250);
					answer3.setWrappingWidth(250);
					answer4.setWrappingWidth(250);

					questionContent1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					questionContent1.setFill(Color.WHITE);
					questionContent2.setFont(Font.font("Century Gothic", 14));
					questionContent2.setFill(Color.WHITE);
					questionContent.getChildren().add(questionContent1);
					questionContent.getChildren().add(questionContent2);
					answer1.setFont(Font.font("Century Gothic", 14));
					answer1.setFill(Color.WHITE);
					answer2.setFont(Font.font("Century Gothic", 14));
					answer2.setFill(Color.WHITE);
					answer3.setFont(Font.font("Century Gothic", 14));
					answer3.setFill(Color.WHITE);
					answer4.setFont(Font.font("Century Gothic", 14));
					answer4.setFill(Color.WHITE);

					TextFlow rightAnswer = new TextFlow();
					Text rightAnswer1 = new Text("   The right answer is:    ");
					Text rightAnswer2 = new Text(String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
					rightAnswer.getChildren().add(rightAnswer1);
					rightAnswer.getChildren().add(rightAnswer2);
					rightAnswer1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					rightAnswer1.setFill(Color.WHITE);
					rightAnswer2.setFont(Font.font("Century Gothic", 14));
					rightAnswer2.setFill(Color.WHITE);

					TextFlow grade = new TextFlow();
					Text gradeText = new Text("    Grade for this question:    ");
					Text gradeTextField = new Text(Integer.toString(exams.get(i).getQuestionGrade().get(j)));
					grade.getChildren().add(gradeText);
					grade.getChildren().add(gradeTextField);
					gradeText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					gradeText.setFill(Color.WHITE);
					gradeTextField.setFont(Font.font("Century Gothic", 14));
					gradeTextField.setFill(Color.WHITE);

					questionBox.getChildren().add(questionContent);
					questionBox.getChildren().add(answer1);
					questionBox.getChildren().add(answer2);
					questionBox.getChildren().add(answer3);
					questionBox.getChildren().add(answer4);
					questionBox.getChildren().add(rightAnswer);

					HBox gradesHB = new HBox();
					gradesHB.getChildren().add(grade);
					gradesHB.setSpacing(10);
					questionBox.getChildren().add(gradesHB);
					questionHBox.getChildren().add(questionBox);

					questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));
					questionBox.setMargin(rightAnswer, new Insets(0, 0, 0, 5));
					// questionBox.setMargin(gradeText, new Insets(0, 5, 0, 5));

					questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
					questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
					questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
					questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));

					questionBox.setSpacing(15);
					displayExam.getChildren().add(questionHBox);

					Line line = new Line();
					line.setEndX(250);
					line.setStroke(Color.web("#1E242E"));
					line.setStrokeWidth(1);
					line.setStrokeLineCap(StrokeLineCap.ROUND);
					displayExam.getChildren().add(line);

				}

				Text examDuration = new Text("Exam time (minutes): ");
				examDuration.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				examDuration.setFill(Color.WHITE);
				Text editTime = new Text();
				editTime.setText(Integer.toString(exams.get(i).getExamTime()));
				editTime.setFont(Font.font("Century Gothic", 14));
				editTime.setFill(Color.WHITE);

				HBox timeHBox = new HBox(15);
				timeHBox.setAlignment(Pos.CENTER);
				timeHBox.getChildren().add(examDuration);
				timeHBox.getChildren().add(editTime);

				displayExam.getChildren().add(timeHBox);
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
