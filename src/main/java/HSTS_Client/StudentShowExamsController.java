package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.ietf.jgss.Oid;
import java.awt.Color;

//import com.sun.org.apache.bcel.internal.generic.NEW;

import HSTS_Entities.Exam;
import HSTS_Entities.ExecutedExam;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;
import HSTS_Entities.StudentsExecutedExam;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class StudentShowExamsController implements Initializable {

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
	private Button start_exam_btn;

	@FXML
	private Button exam_grades_btn;

	@FXML
	private VBox exams_box;

	@FXML
	private Accordion exams_container;

	@FXML
	private RadioButton com_exam;

	private HstsUser user;

	private ArrayList<Exam> exams;

	private ArrayList<StudentsExecutedExam> examsOfStudent;

	private ArrayList<Question> questions;

	private ArrayList<String> allQuestions;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		/*
		 * Message msg=new Message(); msg.setAction("Pull student's exams");
		 * msg.setUser(user); try { AppsClient.getClient().sendToServer(msg); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@FXML
	void menuClick(ActionEvent event) {

		if (event.getSource() == main_page_btn) {
			Stage stage = (Stage) main_page_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentMainPage.fxml"));
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
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentAbout.fxml"));
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

		if (event.getSource() == exam_grades_btn) {
			Stage stage = (Stage) exam_grades_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentShowExams.fxml"));
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

		if (event.getSource() == start_exam_btn) {
			Stage stage = (Stage) start_exam_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentExamExecution.fxml"));
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

	@Subscribe
	public void setExamsToPage(Message msg) {

		this.exams = msg.getExams();
		this.examsOfStudent = msg.getExamsByStudent();
//		this.questions = msg.getQuestions();
//		this.finishedExamsOfStudent=msg.getStudentExecutedExamsArrayList();
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			exams_box.setVisible(true);
			if (exams.size() != 0 && examsOfStudent.size() != 0) {
				for (int i = 0; i < exams.size(); i++) {
					VBox displayExam = new VBox(15);
					displayExam.setAlignment(Pos.TOP_CENTER);

					HBox instructionsHBox = new HBox();
					instructionsHBox.setSpacing(10);
					instructionsHBox.setAlignment(Pos.TOP_CENTER);

					TextFlow editInstructionsArea = new TextFlow();
					Text instructions = new Text("Instructions: ");
					instructions.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					instructions.setStyle("-fx-fill: white");
					Text instructionsArea = new Text(exams.get(i).getInstructions());
					instructionsArea.setFont(Font.font("Century Gothic", 14));
					instructionsArea.setStyle("-fx-fill: white");
					instructionsArea.setWrappingWidth(300);
					editInstructionsArea.getChildren().addAll(instructions, instructionsArea);

					TextFlow grade = new TextFlow();
					Text grade1 = new Text("Grade: ");
					grade1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
					grade1.setStyle("-fx-fill: #5dcfde");
					Text grade2 = new Text("" + examsOfStudent.get(i).getGrade());
					grade2.setFont(Font.font("Century Gothic", 18));
					grade2.setStyle("-fx-fill: #5dcfde");
					grade.getChildren().addAll(grade1, grade2);

					TextFlow ReasonOfGradeChangeArea = new TextFlow();
					Text ReasonOfGradeChangeArea1 = new Text("Reason for change in grade: ");
					ReasonOfGradeChangeArea1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					ReasonOfGradeChangeArea1.setStyle("-fx-fill: white");
					Text ReasonOfGradeChangeArea2 = new Text("" + examsOfStudent.get(i).getReasonOfGradeChange());
					ReasonOfGradeChangeArea2.setFont(Font.font("Century Gothic", 14));
					ReasonOfGradeChangeArea2.setStyle("-fx-fill: white");
					ReasonOfGradeChangeArea.getChildren().addAll(ReasonOfGradeChangeArea1, ReasonOfGradeChangeArea2);

					instructionsHBox.getChildren().add(editInstructionsArea);
					HBox notesHBox = new HBox(10);
					notesHBox.setAlignment(Pos.CENTER);

					TextFlow editNotesArea = new TextFlow();
					Text note1 = new Text("Notes: ");
					note1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					note1.setStyle("-fx-fill: white");
					Text note2 = new Text(exams.get(i).getNotes());
					note2.setFont(Font.font("Century Gothic", 14));
					note2.setStyle("-fx-fill: white");
					editNotesArea.getChildren().addAll(note1, note2);

					if (note2.getText() != "")
						notesHBox.getChildren().addAll(editNotesArea);

					displayExam.getChildren().add(grade);

					Text Reason = new Text("Reason for change in grade: ");
					Reason.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					Reason.setStyle("-fx-fill: white");

					TextFlow combine = new TextFlow();
					if (ReasonOfGradeChangeArea2.getText() != "") {
						Text combine1 = new Text(Reason.getText());
						combine1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
						combine1.setStyle("-fx-fill: white");
						Text combine2 = new Text(ReasonOfGradeChangeArea2.getText());
						combine2.setFont(Font.font("Century Gothic", 14));
						combine2.setStyle("-fx-fill: white");
						combine.getChildren().addAll(combine1, combine2);
						displayExam.getChildren().add(combine);
					}

					displayExam.getChildren().add(instructionsHBox);
					displayExam.getChildren().add(notesHBox);

					for (int j = 0; j < exams.get(i).getQuestions().size(); j++) {
						int pointsForQuestion = 0;
						VBox questionBox = new VBox(15);
						HBox questionHBox = new HBox(15);
						questionHBox.setAlignment(Pos.CENTER);

						TextFlow questionContent = new TextFlow();
						Text questionContent1 = new Text("" + (j + 1) + ".    ");
						Text questionContent2 = new Text(exams.get(i).getQuestions().get(j).getQuestionContent());
						Text answer1 = new Text("1. " + exams.get(i).getQuestions().get(j).getAnswer().get(0));
						Text answer2 = new Text("2. " + exams.get(i).getQuestions().get(j).getAnswer().get(1));
						Text answer3 = new Text("3. " + exams.get(i).getQuestions().get(j).getAnswer().get(2));
						Text answer4 = new Text("4. " + exams.get(i).getQuestions().get(j).getAnswer().get(3));

						questionContent1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
						questionContent1.setStyle("-fx-fill: white");
						questionContent2.setFont(Font.font("Century Gothic", 14));
						questionContent2.setStyle("-fx-fill: white");
						questionContent.getChildren().addAll(questionContent1, questionContent2);

						answer1.setFont(Font.font("Century Gothic", 14));
						answer1.setStyle("-fx-fill: white");
						answer2.setFont(Font.font("Century Gothic", 14));
						answer2.setStyle("-fx-fill: white");
						answer3.setFont(Font.font("Century Gothic", 14));
						answer3.setStyle("-fx-fill: white");
						answer4.setFont(Font.font("Century Gothic", 14));
						answer4.setStyle("-fx-fill: white");

						answer1.setWrappingWidth(250);
						answer2.setWrappingWidth(250);
						answer3.setWrappingWidth(250);
						answer4.setWrappingWidth(250);

						TextFlow answerOfStudent = new TextFlow();
						Text answerOfStudent1 = new Text("    Your answer is: ");
						answerOfStudent1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
						answerOfStudent1.setStyle("-fx-fill: white");
						Text answerOfStudent2 = new Text("" + examsOfStudent.get(i).getAnswersForExam().get(j));
						answerOfStudent2.setFont(Font.font("Century Gothic", 14));
						answerOfStudent2.setStyle("-fx-fill: white");
						answerOfStudent.getChildren().addAll(answerOfStudent1, answerOfStudent2);

						TextFlow rightAnswer = new TextFlow();
						Text rightAnswer1 = new Text("    The right answer is:    ");
						Text rightAnswer2 = new Text(
								String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
						rightAnswer1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
						rightAnswer1.setStyle("-fx-fill: white");
						rightAnswer2.setFont(Font.font("Century Gothic", 14));
						rightAnswer2.setStyle("-fx-fill: white");
						rightAnswer.getChildren().addAll(rightAnswer1, rightAnswer2);

						if (examsOfStudent.get(i).getAnswersForExam().get(j) == exams.get(i).getQuestions().get(j)
								.getRightAnswer())
							pointsForQuestion = exams.get(i).getQuestionGrade().get(j);

						TextFlow gradeTextField = new TextFlow();
						Text gradeTextField1 = new Text("    Points for question: ");
						gradeTextField1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
						gradeTextField1.setStyle("-fx-fill: white");
						Text gradeTextField2 = new Text(Integer.toString(pointsForQuestion) + "/"
								+ Integer.toString(exams.get(i).getQuestionGrade().get(j)));
						gradeTextField2.setFont(Font.font("Century Gothic", 14));
						gradeTextField2.setStyle("-fx-fill: white");
						gradeTextField.getChildren().addAll(gradeTextField1, gradeTextField2);

						questionBox.getChildren().add(questionContent);
						questionBox.getChildren().add(answer1);
						questionBox.getChildren().add(answer2);
						questionBox.getChildren().add(answer3);
						questionBox.getChildren().add(answer4);
						questionBox.getChildren().add(answerOfStudent);
						questionBox.getChildren().add(rightAnswer);

						HBox gradesHB = new HBox();
						gradesHB.getChildren().add(gradeTextField);
						gradesHB.setSpacing(10);
						questionBox.getChildren().add(gradesHB);
						questionHBox.getChildren().add(questionBox);

						questionBox.setMargin(grade, new Insets(0, 0, 0, 220));
						questionBox.setMargin(combine, new Insets(0, 0, 0, 150));

						questionBox.setMargin(questionContent, new Insets(0, 0, 0, 115));
						questionBox.setMargin(answer1, new Insets(0, 0, 0, 130));
						questionBox.setMargin(answer2, new Insets(0, 0, 0, 130));
						questionBox.setMargin(answer3, new Insets(0, 0, 0, 130));
						questionBox.setMargin(answer4, new Insets(0, 0, 0, 130));

						questionBox.setMargin(rightAnswer, new Insets(0, 0, 0, 90));
						questionBox.setMargin(answerOfStudent, new Insets(0, 0, 0, 90));
						gradesHB.setMargin(gradeTextField, new Insets(0, 0, 20, 90));

						questionBox.setSpacing(15);
						// questionBox.setPrefWidth(250);
						displayExam.getChildren().add(questionHBox);
					}

					TextFlow editTime = new TextFlow();
					Text editTime1 = new Text("Exam completed in: ");
					editTime1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					editTime1.setStyle("-fx-fill: white");
					Text editTime2 = new Text(Integer.toString(examsOfStudent.get(i).getExecTime())
							+ " minute(s) out of " + exams.get(i).getExamTime());
					editTime2.setFont(Font.font("Century Gothic", 14));
					editTime2.setStyle("-fx-fill: white");
					editTime.getChildren().addAll(editTime1, editTime2);

					Line line = new Line();
					line.setEndX(250);
					line.setStyle("-fx-fill: #1E242E");
					line.setStrokeWidth(1);
					line.setStrokeLineCap(StrokeLineCap.ROUND);
					displayExam.getChildren().add(line);

					HBox timeHBox = new HBox(15);
					timeHBox.setAlignment(Pos.TOP_CENTER);
					timeHBox.getChildren().add(editTime);
					displayExam.getChildren().add(timeHBox);
					exams_container.getPanes().add(new TitledPane("Exam #" + exams.get(i).getExamID() + " in "
							+ exams.get(i).getCourse() + ", " + exams.get(i).getSubject(), displayExam));
				}
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
	/*
	 * @Subscribe public void setExamsToPageNew(ArrayList<Exam> exams) { this.exams
	 * = exams; EventBus.getDefault().clearCaches(); Platform.runLater(() -> {
	 * exams_box.setVisible(true);
	 * 
	 * 
	 * });
	 * 
	 * }
	 * 
	 */

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;
			Message msg = new Message();
			msg.setAction("Pull student's exams");
			msg.setUser(user);
			try {
				AppsClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

}
