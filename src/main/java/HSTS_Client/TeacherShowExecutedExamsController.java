package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.persistence.Table;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class TeacherShowExecutedExamsController implements Initializable {

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
    private Button review_btn;

    @FXML
    private Button executed_exams_btn;

    @FXML
    private Button main_page_btn;
	
	@FXML
	private VBox exams_box;

	@FXML
	private Accordion exams_container;

	@FXML
	private RadioButton com_exam;

	private HstsUser user;

	private ArrayList<Exam> exams;

	private ArrayList<StudentsExecutedExam> examsOfStudent;

	private ArrayList<ExecutedExam> examsOfTeacher;

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

	@Subscribe
	public void setExamsToPage(Message msg) {

		this.exams = msg.getExams();
		this.examsOfTeacher = msg.getExecutedExams();

		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			exams_box.setVisible(true);
			exams_container.getPanes().clear();

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
				instructionsArea.setStyle("-fx-fill: white");
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
				notesArea.setStyle("-fx-fill: white");
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
					questionContent1.setStyle("-fx-fill: white");
					questionContent2.setFont(Font.font("Century Gothic", 14));
					questionContent2.setStyle("-fx-fill: white");
					questionContent.getChildren().add(questionContent1);
					questionContent.getChildren().add(questionContent2);
					answer1.setFont(Font.font("Century Gothic", 14));
					answer1.setStyle("-fx-fill: white");
					answer2.setFont(Font.font("Century Gothic", 14));
					answer2.setStyle("-fx-fill: white");
					answer3.setFont(Font.font("Century Gothic", 14));
					answer3.setStyle("-fx-fill: white");
					answer4.setFont(Font.font("Century Gothic", 14));
					answer4.setStyle("-fx-fill: white");
					
					TextFlow rightAnswer = new TextFlow();
					Text rightAnswer1 = new Text("   The right answer is:    ");
					Text rightAnswer2 = new Text(String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
					rightAnswer.getChildren().add(rightAnswer1);
					rightAnswer.getChildren().add(rightAnswer2);
					rightAnswer1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					rightAnswer1.setStyle("-fx-fill: white");
					rightAnswer2.setFont(Font.font("Century Gothic", 14));
					rightAnswer2.setStyle("-fx-fill: white");
					
					TextFlow grade = new TextFlow();
					Text gradeText = new Text("    Grade for this question:    ");
					Text gradeTextField = new Text(Integer.toString(exams.get(i).getQuestionGrade().get(j)));
					grade.getChildren().add(gradeText);
					grade.getChildren().add(gradeTextField);
					gradeText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					gradeText.setStyle("-fx-fill: white");
					gradeTextField.setFont(Font.font("Century Gothic", 14));
					gradeTextField.setStyle("-fx-fill: white");
					
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
					//line.setStroke(Color.web("#1E242E"));
					line.setStrokeWidth(1);
					line.setStrokeLineCap(StrokeLineCap.ROUND);
					displayExam.getChildren().add(line);
				}

				Text studentId = new Text();
				Text studentGrade = new Text();
				TableView<String> grades;

				// editTime.setText("Exam completed in:
				// "+Integer.toString(examsOfStudent.get(i).getExecTime())+" minute(s) out of
				// "+exams.get(i).getExamTime());
				VBox gradesVBox = new VBox(5);
				HBox idAndGrade = new HBox(50);
				gradesVBox.setAlignment(Pos.CENTER);
				idAndGrade.setAlignment(Pos.CENTER);
				Text studentIdText = new Text("Student ID");
				Text studentGradeText = new Text("Grade");
				studentIdText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				studentIdText.setStyle("-fx-fill: white");
				studentGradeText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				studentGradeText.setStyle("-fx-fill: white");
				
				idAndGrade.getChildren().add(studentIdText);
				idAndGrade.getChildren().add(studentGradeText);
				gradesVBox.getChildren().add(idAndGrade);
				for (int k = 0; k < examsOfTeacher.get(i).getNumOfStudents(); k++) {
					HBox studentsIdAndGrades = new HBox(5);
					studentsIdAndGrades.setAlignment(Pos.CENTER);
					Text studentIdOfExam = new Text(
							examsOfTeacher.get(i).getStudentsExecutedExams().get(k).getUserId());
					studentIdOfExam.setFont(Font.font("Century Gothic", 14));
					studentIdOfExam.setStyle("-fx-fill: white");
					
					Text studentGradeOfExam = new Text(
							Integer.toString(examsOfTeacher.get(i).getStudentsExecutedExams().get(k).getGrade()));
					studentGradeOfExam.setFont(Font.font("Century Gothic", 14));
					studentGradeOfExam.setStyle("-fx-fill: white");
					
					studentsIdAndGrades.setMargin(studentIdOfExam, new Insets(0, 70, 0, 0));
					studentsIdAndGrades.setMargin(studentGradeOfExam, new Insets(0, 0, 0, 0));
					studentsIdAndGrades.getChildren().add(studentIdOfExam);
					studentsIdAndGrades.getChildren().add(studentGradeOfExam);
					gradesVBox.getChildren().add(studentsIdAndGrades);

				}

				gradesVBox.setAlignment(Pos.CENTER);
				displayExam.getChildren().add(gradesVBox);
				exams_container.getPanes().add(new TitledPane("Exam #" + exams.get(i).getExamID(), displayExam));

			}
		});
	}

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;
			Message msg = new Message();
			msg.setAction("Pull checked exams by teacher");
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
