package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.ietf.jgss.Oid;

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
import javafx.scene.control.DialogEvent;
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

public class EditExamController implements Initializable {

    @FXML
    private Button log_out_btn;

    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button edit_question_btn;

    @FXML
    private Button edit_exam_btn;

    @FXML
    private Button main_page_btn;
	
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
	private VBox exams_box;

	@FXML
	private Accordion exams_container;

	@FXML
	private Button saveBtn;

	private boolean showQuestion = false;

	private HstsUser user;

	private ArrayList<Exam> exams;

	private ArrayList<Question> questions;

	private ArrayList<String> allQuestions;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		saveBtn.setText("Save Exam");
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
		
			if (event.getSource() == edit_question_btn) 
			{
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
			
			if (event.getSource() == main_page_btn) 
			{
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
			
			if (event.getSource() == about_btn) 
			{
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

	@FXML
	void save(ActionEvent event) {

		ArrayList<Question> chosenQuestions = new ArrayList<Question>();
		ArrayList<Integer> questionsGrades = new ArrayList<Integer>();
		TitledPane examToEdit = exams_container.getExpandedPane();
		boolean badInput = false;
		boolean noQuestionsChosen = false;

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

		String instruction = ((TextArea) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(0)).getChildren()
				.get(1)).getText();

		if (instruction.isEmpty()) {
			((TextArea) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(0)).getChildren().get(1))
					.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			((TextArea) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(0)).getChildren().get(1))
					.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
		}

		String notes = ((TextArea) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(1)).getChildren().get(1))
				.getText();

		((TextArea) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(1)).getChildren().get(1))
				.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");

		String chosenExamId = examToEdit.getText().substring(6);
		Exam chosenExam = null;
		Exam newExam = new Exam();

		newExam.setInstructions(instruction);
		newExam.setNotes(notes);
		newExam.setTeacherName(user.getFullName());
		;
		int i = 0;
		for (Exam exam : exams) {
			if (exam.getExamID().equals(chosenExamId)) {
				chosenExam = exam;
			}
		}
	
		ArrayList<Integer> questionsPoints = new ArrayList<Integer>();

		for (i = 0; i < chosenExam.getQuestions().size(); i++) {
		
			String questionGrade = (((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i))
					.getChildren().get(1)).getChildren().get(6)).getChildren().get(1)).getText());
					
			if (((CheckBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i)).getChildren().get(0))
					.isSelected()) {
				chosenQuestions.add(chosenExam.getQuestions().get(i));

				if ((((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i))
						.getChildren().get(1)).getChildren().get(6)).getChildren().get(1)).getText()).isEmpty()||
						!(questionGrade.matches("[0-9]+") && Integer.valueOf(questionGrade) > 0
						&& Integer.valueOf(questionGrade) <= 100)) {

					(((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i))
							.getChildren().get(1)).getChildren().get(6)).getChildren().get(1)))
									.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
					badInput = true;

				} else {
					(((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i))
							.getChildren().get(1)).getChildren().get(6)).getChildren().get(1)))
									.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
					questionsPoints.add(Integer.valueOf(questionGrade));
					questionsGrades.add(Integer.valueOf(
						(((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i))
								.getChildren().get(1)).getChildren().get(6)).getChildren().get(1)).getText())));

				}


			}

		}

		if (((TextField) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i)).getChildren().get(1))
				.getText().isEmpty()) {
			((TextField) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i)).getChildren().get(1))
					.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
			badInput = true;
		} else {
			((TextField) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i)).getChildren().get(1))
					.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
			newExam.setExamTime(Integer.valueOf(
				((TextField) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(2 + i)).getChildren().get(1))
						.getText()));
		}


		if (showQuestion == true) {
			
			for (int j = 0; j < (questions.size() - chosenExam.getQuestions().size()); j++) {
				
				String questionGrade = (((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren()
						.get(4 + i + j)).getChildren().get(1)).getChildren().get(6)).getChildren().get(1))
						.getText());
						
				if (((CheckBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren().get(4 + i + j)).getChildren()
						.get(0)).isSelected()) {
					for (Question question : questions) {
						if (question.getQuestionID().equals(allQuestions.get(j))) {
							chosenQuestions.add(question);

							if ((((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren()
									.get(4 + i + j)).getChildren().get(1)).getChildren().get(6)).getChildren().get(1))
											.getText()).isEmpty()||!(questionGrade.matches("[0-9]+") && Integer.valueOf(questionGrade) > 0
													&& Integer.valueOf(questionGrade) <= 100)) {

								(((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren()
										.get(4 + i + j)).getChildren().get(1)).getChildren().get(6)).getChildren()
												.get(1))).setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
								badInput = true;

							} else {
								(((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent()).getChildren()
										.get(4 + i + j)).getChildren().get(1)).getChildren().get(6)).getChildren()
												.get(1))).setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
								questionsPoints.add(Integer.valueOf(questionGrade));
								questionsGrades.add(Integer
										.valueOf((((TextField) ((HBox) ((VBox) ((HBox) ((VBox) examToEdit.getContent())
												.getChildren().get(4 + i + j)).getChildren().get(1)).getChildren()
													.get(6)).getChildren().get(1)).getText())));
							}
							}
						}
					}
				}

			}
		
		if (chosenQuestions.size() == 0) {
			badInput = true;
			noQuestionsChosen = true;
		}
		
		int sumOfPoints = 0;
		for(int k = 0; k < questionsPoints.size(); k++)
		{
			sumOfPoints += questionsPoints.get(k);
		}
		
		if(sumOfPoints != 100)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Question Points must sum up to 100");
			alert.setTitle("");
			alert.show();
			return;
		}

		if (badInput == false) {
			newExam.setQuestions(chosenQuestions);
			newExam.setCourse(chosenExam.getCourse());
			newExam.setSubject(chosenExam.getSubject());
			newExam.setQuestionGrade(questionsGrades);
			
			showQuestion = false;

			Message msgToServer = new Message();

			msgToServer.setAction("Add Exam");
			msgToServer.setExam(newExam);

			try {
				AppsClient.getClient().sendToServer(msgToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Exam Edited successfully");
			alert.setTitle("");
			// alert.setContentText("The fields marked red must be filled");
			alert.show();
			
			alert.setOnCloseRequest(new EventHandler<DialogEvent>() 
			{
		        @Override
		        public void handle(DialogEvent event) {
		        	Stage stage = (Stage) saveBtn.getScene().getWindow();
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
		    });
			
		} else {
			if (noQuestionsChosen == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("The fields marked red must be filled");
				alert.setTitle("");
				// alert.setContentText("The fields marked red must be filled");
				alert.show();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Questions must be chosen");
				alert.setTitle("");
				// alert.setContentText("The fields marked red must be filled");
				alert.show();
			}
		}
	}

	@Subscribe
	public void setExamsToPage(Message msg) {

		this.exams = msg.getExams();
		this.questions = msg.getQuestions();
		EventBus.getDefault().clearCaches();

		Platform.runLater(() -> {
			if(exams.size() != 0)
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
				
				TextArea editInstructionsArea = new TextArea(exams.get(i).getInstructions());
				editInstructionsArea.setStyle("-fx-text-inner-color: white; -fx-font-size: 14; -fx-text-box-border: transparent;");
				editInstructionsArea.setPrefWidth(300);
				editInstructionsArea.setPrefHeight(100);
				editInstructionsArea.setWrapText(true);

				instructionsHBox.getChildren().addAll(instructions, editInstructionsArea);
				
				HBox notesHBox = new HBox(10);
				notesHBox.setAlignment(Pos.TOP_CENTER);
				Label notes = new Label("Notes: ");
				
				TextArea editNotesArea = new TextArea(exams.get(i).getNotes());
				editNotesArea.setStyle("-fx-text-inner-color: white; -fx-font-size: 14; -fx-text-box-border: transparent;");
				editNotesArea.setPrefWidth(300);
				editNotesArea.setPrefHeight(100);
				editNotesArea.setWrapText(true);
				notesHBox.getChildren().addAll(notes, editNotesArea);
				instructionsHBox.setMargin(editInstructionsArea, new Insets(0, 34, 0, 0));
				
				displayExam.getChildren().add(instructionsHBox);
				displayExam.getChildren().add(notesHBox);

				for (int j = 0; j < exams.get(i).getQuestions().size(); j++) {
					VBox questionBox = new VBox(15);
					HBox questionHBox = new HBox(15);
					questionHBox.setAlignment(Pos.TOP_LEFT);
					questionHBox.setPadding(new Insets(0, 0, 0, 80));
					CheckBox chosenBoxQuestion = new CheckBox();
					chosenBoxQuestion.setSelected(true);
					
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
					
					Text rightAnswer = new Text("   The right answer is: "
							+ String.valueOf(exams.get(i).getQuestions().get(j).getRightAnswer()));
					Text gradeText = new Text("    Grade for chosen question: ");
					TextField gradeTextField = new TextField(Integer.toString(exams.get(i).getQuestionGrade().get(j)));
					
					rightAnswer.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					rightAnswer.setFill(Color.WHITE);
					gradeText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					gradeText.setFill(Color.WHITE);
					gradeTextField.setStyle("-fx-font-family: 'Century Gothic'; -fx-text-inner-color: white; -fx-text-box-border: transparent;");
					
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
					questionHBox.getChildren().add(chosenBoxQuestion);
					questionHBox.getChildren().add(questionBox);

					questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));
					questionBox.setMargin(rightAnswer, new Insets(0, 0, 0, 5));

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
					//displayExam.getChildren().add(line);

				}

				Text examDuration = new Text("Exam time (minutes): ");
				examDuration.setFont(Font.font("Century Gothic", 14));
				examDuration.setFill(Color.WHITE);
				TextField editTime = new TextField();
				editTime.setPrefWidth(60);
				editTime.setText(Integer.toString(exams.get(i).getExamTime()));
				editTime.setStyle("-fx-font-family: 'Century Gothic'; -fx-text-inner-color: white; -fx-text-box-border: transparent;");
				
				HBox timeHBox = new HBox(15);
				timeHBox.setAlignment(Pos.CENTER);
				timeHBox.getChildren().add(examDuration);
				timeHBox.getChildren().add(editTime);
				
				displayExam.getChildren().add(timeHBox);
				Button addQuestionBtn = new Button("Add questions");
				addQuestionBtn.setStyle("-fx-background-radius: 10; -fx-border-radius:10;");
				displayExam.getChildren().add(addQuestionBtn);
				exams_container.getPanes().add(new TitledPane("Exam #" + exams.get(i).getExamID(), displayExam));
				addQuestionBtn.setOnAction(event);

			}
		});
	}

	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			TitledPane chosenExamPane = exams_container.getExpandedPane();
			String chosenExamId = chosenExamPane.getText().substring(6);
			VBox examBox = (VBox) chosenExamPane.getContent();
			int examSize = 0;
			for(int i = 0 ; i < exams.size() ; i++)
			{
				if(exams.get(i).getExamID().equals(chosenExamId))
				{
					examSize = exams.get(i).getQuestions().size();
				}
			}
			
			examSize += 3;
			
		//	examBox.getChildren().size();
			((Button)examBox.getChildren().get(examSize)).setDisable(true);
			setQuestionsToPage(questions);
	
		}
	};

	public void setQuestionsToPage(ArrayList<Question> questions) {

		showQuestion = true;
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
			emptyQuestions.setFont(Font.font("Century Gothic", 14));
			emptyQuestions.setFill(Color.WHITE);
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
					allQuestions.add(questions.get(i).getQuestionID());
					HBox chooseHB = new HBox();
					chooseHB.setAlignment(Pos.TOP_CENTER);
					CheckBox chooseQuestion = new CheckBox();
					chooseHB.getChildren().add(chooseQuestion);

					VBox questionBox = new VBox();
					TextFlow questionContent = new TextFlow();
					Text questionContent1 = new Text("" + (i + 1) + ".    ");
					Text questionContent2 = new Text(questions.get(i).getQuestionContent());
					Text answer1 = new Text("1. " + questions.get(i).getAnswer().get(0));
					Text answer2 = new Text("2. " + questions.get(i).getAnswer().get(1));
					Text answer3 = new Text("3. " + questions.get(i).getAnswer().get(2));
					Text answer4 = new Text("4. " + questions.get(i).getAnswer().get(3));
					
					
					questionContent1.setFont(Font.font("Century Gothic",FontWeight.BOLD, 14));
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
					
					Text rightAnswer = new Text("   The right answer is: "
							+ String.valueOf(questions.get(i).getRightAnswer()));
					Text gradeText = new Text("    Grade for chosen question: ");
					TextField gradeTextField = new TextField();
					
					rightAnswer.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					rightAnswer.setFill(Color.WHITE);
					gradeText.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
					gradeText.setFill(Color.WHITE);
					gradeTextField.setStyle("-fx-font-family: 'Century Gothic'; -fx-text-inner-color: white;");					
					
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

					Line line = new Line();
					line.setEndX(250);
					line.setStroke(Color.web("#1E242E"));
					line.setStrokeWidth(1);
					line.setStrokeLineCap(StrokeLineCap.ROUND);
					
					questionBox.setSpacing(15);
					chooseHB.getChildren().add(questionBox);
					chooseHB.setSpacing(15);
					examBox.getChildren().add(chooseHB);
					//examBox.getChildren().add(line);

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
