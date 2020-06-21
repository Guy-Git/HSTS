package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.Exam;
import HSTS_Entities.ExecutedExam;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import javafx.application.Platform;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
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

public class ExamsReviewController implements Initializable {
	
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
	private Button review_btn;

	@FXML
	private Button exam_execution_btn;

	@FXML
	private Button watch_reports_btn;

	@FXML
	private Button main_page_btn;
	
	@FXML
	private Accordion review_box;

	private HstsUser user;

	ArrayList<Exam> exams;

	ArrayList<ExecutedExam> executedExams;

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

//			if (event.getSource() == watch_reports_btn) 
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
		exams = msg.getExams();
		executedExams = msg.getExecutedExams();

		Platform.runLater(() -> {

			for (int i = 0; i < exams.size(); i++) {
				VBox displayExam = new VBox(15);

				displayExam.setAlignment(Pos.CENTER);
				HBox instructionsHBox = new HBox();
				instructionsHBox.setSpacing(10);
				instructionsHBox.setAlignment(Pos.CENTER);
				// Label instructions = new Label("Instructions:");
			
				TextFlow editInstructionsArea = new TextFlow();
				Text instructions = new Text("Instructions:  ");
				instructions.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				instructions.setFill(Color.WHITE);
				Text instructionsArea = new Text(exams.get(i).getInstructions());
				instructionsArea.setFont(Font.font("Century Gothic", 14));
				instructionsArea.setFill(Color.WHITE);
				editInstructionsArea.getChildren().addAll(instructions, instructionsArea);
				instructionsHBox.getChildren().add(editInstructionsArea);
				HBox notesHBox = new HBox(10);
				notesHBox.setAlignment(Pos.CENTER);
				
				TextFlow notes = new TextFlow();
				Text notes1 = new Text("Notes:");
				notes1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
				notes1.setFill(Color.WHITE);
				Text editNotesArea = new Text(exams.get(i).getNotes());
				editNotesArea.setFont(Font.font("Century Gothic", 14));
				editNotesArea.setFill(Color.WHITE);
				notes.getChildren().addAll(notes1, editNotesArea);

				if (editNotesArea.getText() != "")
					notesHBox.getChildren().addAll(notes, editNotesArea);

				displayExam.getChildren().add(instructionsHBox);
				displayExam.getChildren().add(notesHBox);

				// GridPane questionsGrid = new GridPane();
				// questionsGrid.setAlignment(Pos.CENTER);

				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) {
					VBox questionBox = new VBox(15);
					HBox questionHBox = new HBox(15);
					questionHBox.setAlignment(Pos.CENTER);
					//questionHBox.setPadding(new Insets(0, 0, 0, 150));			
					
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

				Text editTime = new Text();
				editTime.setText("Exam duration in minutes is: " + Integer.toString(exams.get(i).getExamTime()));
				editTime.setFont(Font.font("Century Gothic", 14));
				editTime.setFill(Color.WHITE);
				HBox timeHBox = new HBox(15);
				timeHBox.setAlignment(Pos.CENTER);
				// timeHBox.getChildren().add(examDuration);
				timeHBox.getChildren().add(editTime);
				displayExam.getChildren().add(timeHBox);
				// Button addQuestionBtn = new Button("Add question");
				// displayExam.getChildren().add(addQuestionBtn);
				// displayExam.getChildren().add(saveBtn);

				ExecutedExam executedExam = null;
				for (int j = 0; j < executedExams.size(); j++) {
					if (exams.get(i).getExamID().equals(executedExams.get(j).getExamID())) {
						executedExam = executedExams.get(j);
					}
				}

				VBox examReviewBox = new VBox();
				HBox tableFirstRow = new HBox(15);
			
				Text serialNum = new Text("Serial Number");
				serialNum.setFont(Font.font("Century Gothic", 14));
				serialNum.setFill(Color.WHITE);
				
				Text id = new Text("Student's ID");
				id.setFont(Font.font("Century Gothic", 14));
				id.setFill(Color.WHITE);
				
				tableFirstRow.getChildren().addAll(serialNum, id);
				tableFirstRow.setMargin(serialNum, new Insets(0, 0, 0, 5));
				tableFirstRow.setMargin(id, new Insets(0, 25, 0, 25));

				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) 
				{
					Text questionNum = new Text("Q" + (j + 1));
					questionNum.setFont(Font.font("Century Gothic", 14));
					questionNum.setFill(Color.WHITE);
					tableFirstRow.getChildren().add(questionNum);
				}
				Text tablesNotes = new Text("Notes");
				tablesNotes.setFont(Font.font("Century Gothic", 14));
				tablesNotes.setFill(Color.WHITE);
				
				Text grade = new Text("Grade");
				grade.setFont(Font.font("Century Gothic", 14));
				grade.setFill(Color.WHITE);
				
				Text reasonForChange = new Text("Reason For Change");
				reasonForChange.setFont(Font.font("Century Gothic", 14));
				reasonForChange.setFill(Color.WHITE);
				
				Text approve = new Text("Approved");
				approve.setFont(Font.font("Century Gothic", 14));
				approve.setFill(Color.WHITE);
				
				tableFirstRow.getChildren().addAll(tablesNotes, grade, reasonForChange, approve);
				tableFirstRow.setMargin(tablesNotes, new Insets(0, 60, 0, 60));
				tableFirstRow.setMargin(grade, new Insets(0, 10, 0, 40));
				tableFirstRow.setMargin(reasonForChange, new Insets(0, 60, 0, 60));
				tableFirstRow.setMargin(approve, new Insets(0, 15, 0, 0));

				tableFirstRow.setStyle("-fx-border-color: #1E242E;");
				
				examReviewBox.getChildren().add(tableFirstRow);

				int serialCounter = 0;
				
				for (int j = 0; j < executedExam.getNumOfStudents(); j++) 
				{	
					if (executedExam.getStudentsExecutedExams().get(j).isChecked() == false) 
					{
						serialCounter++;
						HBox studentsExam = new HBox(15);
						studentsExam.setPadding(new Insets(5, 0, 5, 0));
						studentsExam.setAlignment(Pos.CENTER_LEFT);
						Text rowsSerialNum = new Text(serialCounter + ".");
						rowsSerialNum.setFont(Font.font("Century Gothic", 14));
						rowsSerialNum.setFill(Color.WHITE);
						
						Text studentsId = new Text(executedExam.getStudentsExecutedExams().get(j).getUserId());
						studentsId.setFont(Font.font("Century Gothic", 14));
						studentsId.setFill(Color.WHITE);
						studentsExam.setMargin(rowsSerialNum, new Insets(0, 0, 0, 30));
						studentsExam.setMargin(studentsId, new Insets(0, 35, 0, 90));
						studentsExam.getChildren().addAll(rowsSerialNum, studentsId);
						
						
						for (int k = 0; k < exams.get(i).getQuestions().size(); k++) {
							Text studentsAnswer = new Text(
									executedExam.getStudentsExecutedExams().get(j).getAnswersForExam().get(k) + "");
							studentsAnswer.setFont(Font.font("Century Gothic", 14));
							studentsAnswer.setFill(Color.WHITE);
							studentsExam.getChildren().add(studentsAnswer);
							studentsExam.setMargin(studentsAnswer, new Insets(0, 0, 0, 7 + k));
						}

						TextArea teachersNotes = new TextArea();
						teachersNotes.setPrefWidth(140);
						teachersNotes.setPrefHeight(70);
						teachersNotes.setWrapText(true);
						teachersNotes.setStyle("-fx-text-inner-color: white;");

						TextField studentsGrade = new TextField(
								executedExam.getStudentsExecutedExams().get(j).getGrade() + "");
						studentsGrade.setPrefWidth(60);
						studentsGrade.setStyle("-fx-text-inner-color: white;");
						
						TextArea teacherReasonForChange = new TextArea();
						teacherReasonForChange.setPrefWidth(140);
						teacherReasonForChange.setPrefHeight(70);
						teacherReasonForChange.setWrapText(true);
						teacherReasonForChange.setStyle("-fx-text-inner-color: white;");
						
						CheckBox isApproved = new CheckBox();
						studentsExam.getChildren().addAll(teachersNotes, studentsGrade, teacherReasonForChange,
								isApproved);
						
						studentsExam.setMargin(teachersNotes, new Insets(0, 0, 0, 15));
						studentsExam.setMargin(studentsGrade, new Insets(0, 10, 0, 45));
						studentsExam.setMargin(teacherReasonForChange, new Insets(0, 45, 0, 45));
						studentsExam.setMargin(isApproved, new Insets(0, 0, 0, 40));
						
						studentsExam.setStyle("-fx-border-color: #1E242E;");
						examReviewBox.setStyle("-fx-border-color: #1E242E;");
						examReviewBox.getChildren().add(studentsExam);
					}
					
					Button saveBtn = new Button("Save");
					saveBtn.setOnAction(saveEvent);
					
					displayExam.getChildren().addAll(examReviewBox, saveBtn);
					review_box.getPanes().add(new TitledPane("Exam #" + exams.get(i).getExamID(), displayExam));
				}
			}
		});
	}
	
	EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			save();
		}
	};
	
	public void save()
	{
		TitledPane chosenExam = review_box.getExpandedPane();
		String checkedExamID = chosenExam.getText().substring(6);
		ExecutedExam checkedExam = null;
		boolean badInput = false;
		
		for(int i = 0; i < executedExams.size(); i++)
		{
			if(executedExams.get(i).getExamID().equals(checkedExamID))
			{
				checkedExam = executedExams.get(i);
			}
		}
		
		VBox displayBox = (VBox) chosenExam.getContent();
		VBox studentsTable = (VBox) displayBox.getChildren().get(displayBox.getChildren().size() - 2);
		
		for(int i = 1; i < studentsTable.getChildren().size(); i++)
		{
			if(((CheckBox)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size()-1)).isSelected())
			{
				for(int j = 0; j < checkedExam.getStudentsExecutedExams().size(); j++)
				{
					if((((Text)((HBox)studentsTable.getChildren().get(i)).getChildren().get(1)).getText()).equals(checkedExam.getStudentsExecutedExams().get(j).getUserId()))
					{
						int currentGrade = checkedExam.getStudentsExecutedExams().get(j).getExamGrade();
						checkedExam.getStudentsExecutedExams().get(j).setChecked(true);
						checkedExam.getStudentsExecutedExams().get(j).setReasonOfGradeChange(((TextArea)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 2)).getText());
						
						if(((TextField)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 3)).getText().matches("[0-9]+") &&
								Integer.valueOf(((TextField)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 3)).getText()) >= 0 &&
								Integer.valueOf(((TextField)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 3)).getText()) <= 100)
						{
							checkedExam.getStudentsExecutedExams().get(j).setGrade(Integer.valueOf(((TextField)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 3)).getText()));
						}
						else 
						{
							badInput = true;
							((TextField)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 3)).setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10");
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText("The fields marked red" + "\n" + "are empty or illegal");
							alert.setTitle("");
							alert.show();
						}
						
						checkedExam.getStudentsExecutedExams().get(j).setNotes(((TextArea)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 4)).getText());
						if(currentGrade != checkedExam.getStudentsExecutedExams().get(j).getExamGrade())
						{
							if(checkedExam.getStudentsExecutedExams().get(j).getReasonOfGradeChange().isEmpty())
							{
								badInput = true;
								((TextArea)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 2)).setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10");
								Alert alert = new Alert(AlertType.ERROR);
								alert.setHeaderText("Must enter reason for changing exam grade!");
								alert.setTitle("");
								alert.show();
							}
							else {
								((TextArea)((HBox)studentsTable.getChildren().get(i)).getChildren().get(((HBox)studentsTable.getChildren().get(i)).getChildren().size() - 2)).setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
							}
						}
					}
				}
			}
		}
		
		int numOfChecked = 0;
		for(int i = 0; i < checkedExam.getStudentsExecutedExams().size(); i++)
		{
			if(checkedExam.getStudentsExecutedExams().get(i).isChecked() == true)
			{
				numOfChecked++;
			}
		}
		
		if(numOfChecked == checkedExam.getStudentsExecutedExams().size())
			checkedExam.setChecked(true);
		else
			checkedExam.setChecked(false);
		
		if(badInput == false)
		{
			Message msgToServer = new Message();
			msgToServer.setAction("Review Executed Exam");
			msgToServer.setExecutedExam(checkedExam);
			
			try {
				AppsClient.getClient().sendToServer(msgToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;

			Message msgToServer = new Message();
			msgToServer.setAction("Pull unchecked exams by teacher");
			msgToServer.setUser(user);

			try {
				AppsClient.getClient().sendToServer(msgToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
