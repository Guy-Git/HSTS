package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.ietf.jgss.Oid;

//import com.sun.org.apache.bcel.internal.generic.NEW;

import HSTS_Entities.Exam;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ShowAllExamsController implements Initializable {

	@FXML
    private Button all_questions_btn;

	@FXML
    private Button executed_exams_btn;
	
    @FXML
    private Button about_btn;

    @FXML
    private Button main_page_btn;

    @FXML
    private Button log_out_btn;

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
	private Button show_question_btn;

	@FXML
	private VBox exams_box;

	@FXML
	private Accordion exams_container;

	@FXML
	private RadioButton com_exam;

	private HstsUser user;

	private ArrayList<Exam> exams;

	private ArrayList<Question> questions;

	private ArrayList<String> allQuestions;
	
	private String teacherName;

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
	void pullExams(ActionEvent event) {

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

		if (!badInput) {
			exams_container.getPanes().clear();
			Message msgToServer = new Message();
			msgToServer.setSubject(chooseSubject.getValue());
			msgToServer.setCourse(chooseCourse.getValue());
			msgToServer.setAction("Pull Exams and Questions");

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


	@Subscribe
	public void setExamsToPage(Message msg) {

		this.exams = msg.getExams();
		this.questions = msg.getQuestions();
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			exams_box.setVisible(true);

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
				
				HBox teacherHbox = new HBox(10);
				teacherHbox.setAlignment(Pos.TOP_CENTER);
				Label teacher = new Label("Teacher: "); 
				teacher.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				Text teacherName = new Text(exams.get(i).getTeacherName());
				teacherName.setFont(Font.font("Century Gothic", 14));
				teacherName.setFill(Color.WHITE);
				teacherName.setWrappingWidth(300);
				teacherHbox.getChildren().addAll(teacher, teacherName);
				teacherHbox.setPadding(new Insets(0, 0, 0, 65));

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
				
				displayExam.getChildren().add(teacherHbox);
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
				examDuration.setFont(Font.font("Century Gothic",FontWeight.BOLD, 14));
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

	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setQuestionsToPage(questions);
		}
	};

	public void setQuestionsToPage(ArrayList<Question> questions) {

		TitledPane chosenExamPane = exams_container.getExpandedPane();
		String chosenExamId = chosenExamPane.getText().substring(6);
		VBox examBox = (VBox) chosenExamPane.getContent();
		Exam chosenExam = null;
		allQuestions = new ArrayList<String>();
		for (Exam exam : exams) {
			if (exam.getExamID().equals(chosenExamId))
				chosenExam = exam;
		}

		boolean toAdd = true;

		if (questions.size() == chosenExam.getQuestionGrade().size()) {
			Text emptyQuestions = new Text("There are no additional questions to add to the exam!");
			examBox.getChildren().add(emptyQuestions);
		} else {
			for (int i = 0; i < questions.size(); i++) {
				toAdd = true;
				for (int j = 0; j < chosenExam.getQuestions().size(); j++) {
					if (chosenExam.getQuestions().get(j).getQuestionID().equals(questions.get(i).getQuestionID())) {
						toAdd = false;
					}
				}
				if (toAdd) {
					System.out.println(questions.get(i).getQuestionID());
					allQuestions.add(questions.get(i).getQuestionID());
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
					Text gradeText = new Text("Add grade for chosen question:");
					Text gradeTextField = new Text();

					questionBox.getChildren().add(questionContent);
					questionBox.getChildren().add(answer1);
					questionBox.getChildren().add(answer2);
					questionBox.getChildren().add(answer3);
					questionBox.getChildren().add(answer4);
					questionBox.getChildren().add(rightAnswer);

					HBox gradesHB = new HBox();
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
					questionBox.setStyle("-fx-background-color: #ADD8E6");
					chooseHB.getChildren().add(questionBox);
					chooseHB.setSpacing(15);
					examBox.getChildren().add(chooseHB);
				}
			}
		}
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

