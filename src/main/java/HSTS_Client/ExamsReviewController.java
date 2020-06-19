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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ExamsReviewController implements Initializable {
	@FXML
	private Accordion review_box;

	private HstsUser user;

	ArrayList<Exam> exams;

	ArrayList<ExecutedExam> executedExams;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
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
				Text editInstructionsArea = new Text("Instructions: " + exams.get(i).getInstructions());
				instructionsHBox.getChildren().add(editInstructionsArea);
				HBox notesHBox = new HBox(10);
				notesHBox.setAlignment(Pos.CENTER);
				Label notes = new Label("Notes:");
				Text editNotesArea = new Text(exams.get(i).getNotes());
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
					Text questionContent = new Text(
							"" + (j + 1) + ". " + exams.get(i).getQuestions().get(j).getQuestionContent());
					Text answer1 = new Text("1. " + exams.get(i).getQuestions().get(j).getAnswer().get(0));
					Text answer2 = new Text("2. " + exams.get(i).getQuestions().get(j).getAnswer().get(1));
					Text answer3 = new Text("3. " + exams.get(i).getQuestions().get(j).getAnswer().get(2));
					Text answer4 = new Text("4. " + exams.get(i).getQuestions().get(j).getAnswer().get(3));
					Text rightAnswer = new Text("The right answer is: "
							+ String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
					// Text gradeText = new Text("points:");
					Text gradeTextField = new Text(
							"  Points: " + Integer.toString(exams.get(i).getQuestionGrade().get(j)));

					questionBox.getChildren().add(questionContent);
					questionBox.getChildren().add(answer1);
					questionBox.getChildren().add(answer2);
					questionBox.getChildren().add(answer3);
					questionBox.getChildren().add(answer4);
					questionBox.getChildren().add(rightAnswer);

					HBox gradesHB = new HBox();
					// gradesHB.getChildren().add(gradeText);
					gradesHB.getChildren().add(gradeTextField);
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

					questionBox.setStyle("-fx-background-color: #ADD8E6");
					questionBox.setPrefWidth(250);
					displayExam.getChildren().add(questionHBox);
				}

				// Text examDuration = new Text("Exam duration in minutes is:");
				Text editTime = new Text();
				editTime.setText("Exam duration in minutes is: " + Integer.toString(exams.get(i).getExamTime()));
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
				
				Text id = new Text("Student's ID");
				tableFirstRow.getChildren().addAll(serialNum, id);
				tableFirstRow.setMargin(serialNum, new Insets(0, 0, 0, 5));
				tableFirstRow.setMargin(id, new Insets(0, 25, 0, 25));

				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) 
				{
					Text questionNum = new Text("Q" + (j + 1));
					tableFirstRow.getChildren().add(questionNum);
				}
				Text tablesNotes = new Text("Notes");
				Text grade = new Text("Grade");
				Text reasonForChange = new Text("Reason For Change");
				Text approve = new Text("Approved");
				tableFirstRow.getChildren().addAll(tablesNotes, grade, reasonForChange, approve);
				tableFirstRow.setMargin(tablesNotes, new Insets(0, 60, 0, 60));
				tableFirstRow.setMargin(grade, new Insets(0, 10, 0, 40));
				tableFirstRow.setMargin(reasonForChange, new Insets(0, 60, 0, 60));
				tableFirstRow.setMargin(approve, new Insets(0, 15, 0, 0));

				tableFirstRow.setStyle("-fx-border-color: black");
				
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
						Text studentsId = new Text(executedExam.getStudentsExecutedExams().get(j).getUserId());
						studentsExam.setMargin(rowsSerialNum, new Insets(0, 0, 0, 30));
						studentsExam.setMargin(studentsId, new Insets(0, 25, 0, 70));
						studentsExam.getChildren().addAll(rowsSerialNum, studentsId);
						
						
						for (int k = 0; k < exams.get(i).getQuestions().size(); k++) {
							Text studentsAnswer = new Text(
									executedExam.getStudentsExecutedExams().get(j).getAnswersForExam().get(k) + "");
							studentsExam.getChildren().add(studentsAnswer);
							studentsExam.setMargin(studentsAnswer, new Insets(0, 0, 0, 7 + k));
						}

						TextArea teachersNotes = new TextArea();
						teachersNotes.setPrefWidth(140);
						teachersNotes.setPrefHeight(70);
						teachersNotes.setWrapText(true);
						
						TextField studentsGrade = new TextField(
								executedExam.getStudentsExecutedExams().get(j).getGrade() + "");
						studentsGrade.setPrefWidth(40);
						
						TextArea teacherReasonForChange = new TextArea();
						teacherReasonForChange.setPrefWidth(140);
						teacherReasonForChange.setPrefHeight(70);
						teacherReasonForChange.setWrapText(true);
						
						CheckBox isApproved = new CheckBox();
						studentsExam.getChildren().addAll(teachersNotes, studentsGrade, teacherReasonForChange,
								isApproved);
						
						studentsExam.setMargin(teachersNotes, new Insets(0, 0, 0, 15));
						studentsExam.setMargin(studentsGrade, new Insets(0, 10, 0, 40));
						studentsExam.setMargin(teacherReasonForChange, new Insets(0, 45, 0, 40));
						studentsExam.setMargin(isApproved, new Insets(0, 0, 0, 10));
						
						studentsExam.setStyle("-fx-border-color: black");
						examReviewBox.setStyle("-fx-border-color: black");
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
			msgToServer.setAction("Pull exam by teacher");
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
