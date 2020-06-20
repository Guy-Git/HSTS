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
import HSTS_Entities.Question;
import HSTS_Entities.StudentsExecutedExam;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrincipalExecutedExamsController implements Initializable {
	
    @FXML
    private ChoiceBox<String> choose_teacher;

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
	
    @FXML
    private Button show_exams_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		
		Message msgToServer = new Message();
		msgToServer.setAction("Pull teachers");
		
		try {
			AppsClient.getClient().sendToServer(msgToServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Subscribe
	public void onTeachersEvent(ArrayList<HstsUser> teachers) {
		Platform.runLater(() -> 
		{
			ArrayList<String> teachersName = new ArrayList<String>();
			for(int i = 0; i < teachers.size(); i++)
			{
				teachersName.add(teachers.get(i).getFullName() + " ID: " + teachers.get(i).getUserId());
			}

			if (teachersName.get(0) != "") {
				teachersName.add(0, "");
			}

			ObservableList<String> setToTeachers = FXCollections.observableArrayList(teachersName);

			choose_teacher.setItems(setToTeachers);

		});
	}
	
    @FXML
    void showExams(ActionEvent event) 
    {
    	boolean badInput = false;

		if (choose_teacher.getSelectionModel().isEmpty()
				|| choose_teacher.getValue().equals("")) {
			choose_teacher.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			choose_teacher.setStyle("-fx-background-color: #1E242E; -fx-background-radius: 10;");
		}

		if (badInput == false) {
			exams_container.getPanes().clear();
			Message msg = new Message();
			
			System.out.println(choose_teacher.getValue().substring(choose_teacher.getValue().length() - 9));
			
			HstsUser sentUser = new HstsUser();
			sentUser.setUserId(choose_teacher.getValue().substring(choose_teacher.getValue().length() - 9));
			msg.setAction("Pull checked exams by teacher");
			msg.setUser(sentUser);
			
			try {
				AppsClient.getClient().sendToServer(msg);
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
//		this.questions = msg.getQuestions();
//		this.finishedExamsOfStudent=msg.getStudentExecutedExamsArrayList();
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			// System.out.println(exams.get);
			exams_box.setVisible(true);

			for (int i = 0; i < exams.size(); i++) {
				VBox displayExam = new VBox(15);

				displayExam.setAlignment(Pos.CENTER);
				HBox instructionsHBox = new HBox();
				instructionsHBox.setSpacing(10);
				instructionsHBox.setAlignment(Pos.CENTER);
				Text editInstructionsArea = new Text("Instructions: " + exams.get(i).getInstructions());
				// Text grade=new Text("Grade: "+examsOfStudent.get(i).getGrade());
				instructionsHBox.getChildren().add(editInstructionsArea);
				HBox notesHBox = new HBox(10);
				notesHBox.setAlignment(Pos.CENTER);
				Label notes = new Label("Notes:");
				Text editNotesArea = new Text(exams.get(i).getNotes());
				if (editNotesArea.getText() != "")
					notesHBox.getChildren().addAll(notes, editNotesArea);
				// displayExam.getChildren().add(grade);

				displayExam.getChildren().add(instructionsHBox);
				displayExam.getChildren().add(notesHBox);

				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) {
					// int pointsForQuestion=0;
					VBox questionBox = new VBox(15);
					HBox questionHBox = new HBox(15);
					questionHBox.setAlignment(Pos.CENTER);
					Text questionContent = new Text(
							"" + (j + 1) + ". " + exams.get(i).getQuestions().get(j).getQuestionContent());
					Text answer1 = new Text("1. " + exams.get(i).getQuestions().get(j).getAnswer().get(0));
					Text answer2 = new Text("2. " + exams.get(i).getQuestions().get(j).getAnswer().get(1));
					Text answer3 = new Text("3. " + exams.get(i).getQuestions().get(j).getAnswer().get(2));
					Text answer4 = new Text("4. " + exams.get(i).getQuestions().get(j).getAnswer().get(3));
					// Text answerOfStudent=new Text(" your answer is:
					// "+examsOfStudent.get(i).getAnswersForExam().get(j));
					Text rightAnswer = new Text("The right answer is: "
							+ String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
					// if(examsOfStudent.get(i).getAnswersForExam().get(j)==exams.get(i).getQuestions().get(j).getRightAnswer())
					// pointsForQuestion=exams.get(i).getQuestionGrade().get(j);
					// Text gradeTextField = new Text(" Points for question:
					// "+Integer.toString(pointsForQuestion)+"/"+Integer.toString(exams.get(i).getQuestionGrade().get(j)));

					questionBox.getChildren().add(questionContent);
					questionBox.getChildren().add(answer1);
					questionBox.getChildren().add(answer2);
					questionBox.getChildren().add(answer3);
					questionBox.getChildren().add(answer4);
//					questionBox.getChildren().add(answerOfStudent);
					questionBox.getChildren().add(rightAnswer);

					HBox gradesHB = new HBox();
//					gradesHB.getChildren().add(gradeTextField);
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

				idAndGrade.getChildren().add(studentIdText);
				idAndGrade.getChildren().add(studentGradeText);
				gradesVBox.getChildren().add(idAndGrade);
				for (int k = 0; k < examsOfTeacher.get(i).getNumOfStudents(); k++) {
					HBox studentsIdAndGrades = new HBox(5);
					studentsIdAndGrades.setAlignment(Pos.CENTER);
					Text studentIdOfExam = new Text(
							examsOfTeacher.get(i).getStudentsExecutedExams().get(k).getUserId());
					
					Text studentGradeOfExam = new Text(
							Integer.toString(examsOfTeacher.get(i).getStudentsExecutedExams().get(k).getGrade()));
					studentsIdAndGrades.setMargin(studentIdOfExam, new Insets(0, 52, 0, 0));
					studentsIdAndGrades.setMargin(studentGradeOfExam, new Insets(0, -10, 0, 0));
					studentsIdAndGrades.getChildren().add(studentIdOfExam);
					studentsIdAndGrades.getChildren().add(studentGradeOfExam);
					gradesVBox.getChildren().add(studentsIdAndGrades);

				}

				gradesVBox.setAlignment(Pos.CENTER);
				// timeHBox.getChildren().add(examDuration);
				// timeHBox.getChildren().add(editTime);
				displayExam.getChildren().add(gradesVBox);
				// Button addQuestionBtn = new Button("Add question");
				// displayExam.getChildren().add(addQuestionBtn);
				// displayExam.getChildren().add(saveBtn);
				exams_container.getPanes().add(new TitledPane("Exam #" + exams.get(i).getExamID(), displayExam));
				// addQuestionBtn.setOnAction(event);

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
		});

	}
}
