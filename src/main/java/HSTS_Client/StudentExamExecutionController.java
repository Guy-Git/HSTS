package HSTS_Client;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.STTabJcImpl;

import com.sun.glass.ui.Size;

import HSTS_Entities.Exam;
import HSTS_Entities.ExamForExec;
import HSTS_Entities.ExecutedExam;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.StudentsExecutedExam;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StudentExamExecutionController implements Initializable {

    @FXML
    private Button main_page_btn;

    @FXML
    private Button log_out_btn;

    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button start_exam_btn1;

    @FXML
    private Button exam_grades_btn;
	
	@FXML
	private AnchorPane exam_anchor;

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
	private Text for_multi_line;

	@FXML
	private TextField enterExamCode;

	@FXML
	private Text for_multi_line1;

	@FXML
	private Button submit_btn;

	@FXML
	private TextField enterIdForExam;

	@FXML
	private VBox exam_vbox;

	@FXML
	private Button downlod_btn;

	@FXML
	private Button start_exam_btn;

	@FXML
	private Text time_text;

	@FXML
	private Button save_exam;

	@FXML
	private Button upload_exam;
	
	@FXML
	private Text exam_title;

	@FXML
	private Text fileName;

	private HstsUser user;

	private Exam exam;

	private Integer startTime;// time for exam in minutes

	private Integer hourTime;

	private Integer minutesTime;

	private Integer secondsTime;

	private Integer executeTime;

	private boolean startSave = false;

	private Integer minutesLeft;

	private File file;

	private ExecutedExam executedExam;

	private boolean checkedExtentions = false;

	ExamForExec examForExec;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
//		studentsExecutedExam = new StudentsExecutedExam();
	}

	@FXML
	void menuClick(ActionEvent event) {
		if (event.getSource() == main_page_btn) 
		{
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
		
		if (event.getSource() == about_btn) 
		{
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
		
		if (event.getSource() == start_exam_btn) 
		{
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

//			if (event.getSource() == exam_grades_btn) 

	}

	@FXML
	void enterSubmit(ActionEvent event) {

		examForExec = new ExamForExec();
		Message msg = new Message();
		examForExec.setExamCode(enterExamCode.getText());
		msg.setExamForExec(examForExec);
		msg.setAction("Enter code");

		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void setExamToPage(Message msg) {
		
		Platform.runLater(() -> {
			
			boolean submitted = false;

			for (int i = 0; i < msg.getExecutedExam().getStudentsExecutedExams().size(); i++) {
				if (msg.getExecutedExam().getStudentsExecutedExams().get(i).getUserId().equals(user.getUserId())) {
					if(msg.getExecutedExam().getStudentsExecutedExams().get(i).isSubmitted()) {
					submitted = true;
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("This exam has already been submitted! \nTry again!");
					alert.setTitle("");
					alert.show();
					break;
					}
				}
			}
			if (submitted == false) {
				if (checkedExtentions == true) {
					minutesLeft = (5 + msg.getExtendTime()) % 60;
					startTime += msg.getExtendTime();
					hourTime = minutesLeft / 60;
					if (startTime < 60) {
						System.out.println(msg.getExtendTime());
						System.out.println("minutes" + minutesTime + "start" + startTime);
						minutesTime = startTime;
					} else {
						minutesTime = minutesLeft;
					}
				} else {
					this.exam = msg.getExam();
					this.executedExam = msg.getExecutedExam();
					hourTime = (exam.getExamTime()) / 60;
					minutesLeft = exam.getExamTime() % 60;
					startTime = exam.getExamTime();
					if (startTime < 60) {
						minutesTime = startTime;
						secondsTime = 0;
						if (minutesTime == 1) {
							minutesTime = 1;
							secondsTime = 0;
						}
					} else {
						minutesTime = minutesLeft;
						secondsTime = 0;
					}

					if (exam == null) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Exam code is incorrect! \nTry again!");
						alert.setTitle("");
						alert.show();
					}

					else {

						enterExamCode.setVisible(false);
						for_multi_line.setLayoutX(237);
						for_multi_line.setLayoutY(97);
						submit_btn.setDisable(false);
						submit_btn.setVisible(true);

						if (!exam.isManual()) {
							submit_btn.setVisible(false);
							start_exam_btn.setDisable(false);
							for_multi_line.setText("Enter ID:");
							for_multi_line.setVisible(true);
							//for_multi_line1.setVisible(true);
							//for_multi_line1.setLayoutX(237);
							//for_multi_line1.setLayoutY(80);
							enterIdForExam.setVisible(true);
							enterIdForExam.setLayoutX(299);
							enterIdForExam.setLayoutY(80);
							enterIdForExam.setDisable(false);
							start_exam_btn.setVisible(true);
							//start_exam_btn.setLayoutY(225);

						} else {
							for_multi_line.setVisible(false);
							submit_btn.setVisible(false);
							downlod_btn.setVisible(true);
						}
					}
				}
			}
		});
	}

	@FXML
	void onDownlodeEvent(ActionEvent event) {

		upload_exam.setVisible(true);
		upload_exam.setDisable(false);
		submit_btn.setVisible(false);
		downlod_btn.setVisible(false);
		save_exam.setVisible(true);
		save_exam.setDisable(true);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		Button submitExamBtn = new Button();
		time_text.setText(
				"time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : " + secondsTime.toString());

		if (timeline != null) {
			timeline.stop();
		}

		XWPFDocument document = new XWPFDocument();

		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		System.out.println(exam.getCourse());
		run.setText("Exam in course " + exam.getCourse() + " the subject is " + exam.getSubject());
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("Instructions: " + exam.getInstructions());

		for (int i = 0; i < exam.getQuestions().size(); i++) {
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setText("");
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setText((i + 1) + ".  " + exam.getQuestions().get(i).getQuestionContent());

			for (int j = 0; j < 4; j++) {
				paragraph = document.createParagraph();
				paragraph.setAlignment(ParagraphAlignment.RIGHT);
				run = paragraph.createRun();
				run.setText("   " + (j + 1) + ".  " + exam.getQuestions().get(i).getAnswer().get(j));
			}

		}

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("Good Luck!");
		exam_anchor.setDisable(true);
		//upload_exam.setLayoutX(236);
		//upload_exam.setLayoutY(121);
		upload_exam.setDisable(false);
		try {
			FileOutputStream out = new FileOutputStream(new File("C:/Users/opal/Desktop/CoolTest.docx"));
			try {
				document.write(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
						+ secondsTime.toString());

				if (minutesTime == 5 && secondsTime == 0 && hourTime == 0 && checkedExtentions == false) {
					// timeline.stop();
					ExamForExec newExemExamForExec = new ExamForExec(exam.getExamID(), true, examForExec.getExamCode());
					checkedExtentions = true;
					Message msgToServer = new Message();
					msgToServer.setAction("Check for extension");
					examForExec.setExamID(exam.getExamID());
					msgToServer.setExamForExec(newExemExamForExec);

					try {
						AppsClient.getClient().sendToServer(msgToServer);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (minutesTime <= 0 && secondsTime <= 0 && hourTime <= 0) {
					timeline.stop();
					submitExamBtn.setVisible(false);
					upload_exam.setVisible(false);

					if (startSave == false) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("time is up!");
						alert.show();
						timesUp();
					}
				} else {
					if (secondsTime == 0 && minutesTime > 0) {
						secondsTime = 60;
						minutesTime--;
						startTime--;

					}
					if (minutesTime == 1 && secondsTime == 0) {
						minutesTime = 0;
						secondsTime = 59;
						startTime--;
					}
					if (hourTime > 0 && secondsTime == 0 && minutesTime == 0) {
						hourTime--;
						minutesTime = 59;
						secondsTime = 59;
						startTime--;

					} else {
						secondsTime--;
					}

				}
			}

		});
		timeline.getKeyFrames().add(frame);
		timeline.playFromStart();
	}

	@FXML
	void uploadExam(ActionEvent event) {
		// exam_anchor.getChildren().remove(0)

		FileChooser fileChooser1 = new FileChooser();
		fileChooser1.setTitle("Open file");
		file = fileChooser1.showOpenDialog(null);
		save_exam.setDisable(false);

		if (file != null) {
			fileName.setText("");
			// Text fileName = new Text();
			fileName.setText(file.getName());
			fileName.setLayoutX(53);
			exam_anchor.getChildren().add(fileName);

		}
	}

	@FXML
	void startExam(ActionEvent event) {

		if (enterIdForExam.getText().equals(user.getUserId())) {

			for_multi_line.setVisible(false);
			enterIdForExam.setVisible(false);
			start_exam_btn.setVisible(false);
			submit_btn.setVisible(false);
		    save_exam.setVisible(true);

			Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
					+ secondsTime.toString());

			if (timeline != null) {
				timeline.stop();
			}
			System.out.println(exam.getQuestions().get(0).getQuestionContent());
			VBox displayExam = new VBox(10);
			displayExam.setAlignment(Pos.CENTER);
			
			TextFlow instructions = new TextFlow();
			Text instructions1 = new Text("\nInstructions: ");
			Text instructions2 = new Text(exam.getInstructions());
			instructions.getChildren().add(instructions1);
			instructions.getChildren().add(instructions2);
			instructions1.setFont(Font.font("Century Gothic", 14));
			instructions1.setFill(Color.WHITE);
			instructions2.setFont(Font.font("Century Gothic", 14));
			instructions2.setFill(Color.WHITE);
			instructions2.setWrappingWidth(280);
			
			displayExam.getChildren().add(instructions);
			Button save_btn = new Button();
			save_btn.setAlignment(Pos.CENTER);
			save_btn.setText("save exam");
			GridPane questionsGrid = new GridPane();
			questionsGrid.setAlignment(Pos.CENTER);
			Text examTitle = new Text("");
			exam_title.setText("  Exam in " + exam.getSubject() + " \ncourse " + exam.getCourse());
			displayExam.getChildren().add(examTitle);
		
			for (int j = 0; j < exam.getQuestions().size(); j++) {
				VBox questionBox = new VBox(20);
				ToggleGroup answerGroup = new ToggleGroup();
				RadioButton ans1RB = new RadioButton();
				RadioButton ans2RB = new RadioButton();
				RadioButton ans3RB = new RadioButton();
				RadioButton ans4RB = new RadioButton();
			
				ans1RB.setToggleGroup(answerGroup);
				ans2RB.setToggleGroup(answerGroup);
				ans3RB.setToggleGroup(answerGroup);
				ans4RB.setToggleGroup(answerGroup);

				HBox answer1HBox = new HBox(5);
				HBox answer2HBox = new HBox(5);
				HBox answer3HBox = new HBox(5);
				HBox answer4HBox = new HBox(5);

				answer1HBox.getChildren().add(ans1RB);
				answer2HBox.getChildren().add(ans2RB);
				answer3HBox.getChildren().add(ans3RB);
				answer4HBox.getChildren().add(ans4RB);

				TextFlow questionContent = new TextFlow();
				Text questionContent1 = new Text("" + (j + 1) + ".    ");
				Text questionContent2 = new Text(exam.getQuestions().get(j).getQuestionContent());
				Text answer1 = new Text("1. " + exam.getQuestions().get(j).getAnswer().get(0));
				Text answer2 = new Text("2. " + exam.getQuestions().get(j).getAnswer().get(1));
				Text answer3 = new Text("3. " + exam.getQuestions().get(j).getAnswer().get(2));
				Text answer4 = new Text("4. " + exam.getQuestions().get(j).getAnswer().get(3));

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
				
				answer1HBox.getChildren().add(answer1);
				answer2HBox.getChildren().add(answer2);
				answer3HBox.getChildren().add(answer3);
				answer4HBox.getChildren().add(answer4);

				questionBox.getChildren().add(questionContent);
				questionBox.getChildren().add(answer1HBox);
				questionBox.getChildren().add(answer2HBox);
				questionBox.getChildren().add(answer3HBox);
				questionBox.getChildren().add(answer4HBox);

				questionBox.setMargin(questionContent, new Insets(20, 10, 0, 5));

				questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));

				questionBox.setSpacing(15);

				questionsGrid.setVgap(30);
				//questionBox.setStyle("-fx-background-color: #ADD8E6");
				questionsGrid.add(questionBox, 0, j, 1, 1);
				displayExam.getChildren().add(questionBox);

			}

			Text endTitle = new Text("GOOD LUCK!");
			endTitle.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
			endTitle.setFill(Color.WHITE);
			displayExam.getChildren().add(endTitle);
			save_exam.setVisible(true);
			save_exam.setDisable(false);
			displayExam.getChildren().add(save_exam);
			exam_anchor.setLayoutX(200);
			exam_anchor.setLayoutY(120);
			time_text.setLayoutY(110);
			exam_anchor.getChildren().add(displayExam);

			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub

					time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
							+ secondsTime.toString());

					if (minutesTime == 5 && secondsTime == 0 && hourTime == 0 && checkedExtentions == false) {
						// timeline.stop();
						ExamForExec newExemExamForExec = new ExamForExec(exam.getExamID(), true,
								examForExec.getExamCode());
						checkedExtentions = true;
						Message msgToServer = new Message();
						msgToServer.setAction("Check for extension");
						examForExec.setExamID(exam.getExamID());
						msgToServer.setExamForExec(newExemExamForExec);

						try {
							AppsClient.getClient().sendToServer(msgToServer);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (startTime <= 0 && secondsTime <= 0 && hourTime <= 0) {

						timeline.stop();
						exam_anchor.setVisible(false);
						save_exam.setVisible(false);

						if (startSave == false) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("time is up!");
							alert.show();
							timesUp();

						}
						startSave = false;
					} else {
						if (secondsTime == 0 && minutesTime > 0) {
							secondsTime = 60;
							minutesTime--;
							startTime--;
						}
						if (minutesTime == 1 && secondsTime == 0) {
							minutesTime = 0;
							secondsTime = 59;
							startTime--;
						}
						if (hourTime > 0 && secondsTime == 0 && minutesTime == 0) {
							hourTime--;
							minutesTime = 59;
							secondsTime = 59;
							startTime--;
						} else {
							secondsTime--;
						}
					}
				}

			});
			timeline.getKeyFrames().add(frame);
			timeline.playFromStart();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("ID is incorrect! try again");
			alert.show();
		}

	}

	@FXML
	void saveExamContent(boolean isForced) {

		executeTime = exam.getExamTime() - minutesTime;
		startSave = true;
		ArrayList<Integer> chosenAnswers = new ArrayList<Integer>();
		int sizeOfAnswers = 0;

		for (int i = 0; i < exam.getQuestions().size(); i++) {
			VBox questionBox = (VBox) exam_anchor.getChildren().get(0);
			VBox answersBox = (VBox) questionBox.getChildren().get(2 + i);

			for (int j = 0; j < 4; j++) {

				HBox answerBox = (HBox) answersBox.getChildren().get(j + 1);

				if (((RadioButton) answerBox.getChildren().get(0)).isSelected()) {
					chosenAnswers.add(j + 1);
					sizeOfAnswers++;
				}
			}
			if (sizeOfAnswers != (i + 1)) {
				chosenAnswers.add(0);// none of the answers was chosen
			}
		}
		StudentsExecutedExam studentsExecutedExam = new StudentsExecutedExam(isForced, executeTime,
				this.user.getUserId(), chosenAnswers, false, false, this.executedExam);

		studentsExecutedExam.setSubmitted(true);
		save_exam.setVisible(false);
		time_text.setVisible(false);
		exam_anchor.setVisible(false);

		Message msg = new Message();
		msg.setStudentsExecutedExam(studentsExecutedExam);

		msg.setAction("Submit Student Exam");

		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void timesUp() {

		if (!exam.isManual()) {
			saveExamContent(true);
		} else {
			file = null;
			executeTime = exam.getExamTime() - minutesTime;
			StudentsExecutedExam studentsExecutedExam = new StudentsExecutedExam(true, exam.getExamTime(),
					this.user.getUserId(), file, true, this.executedExam);
			saveManualExam(studentsExecutedExam);

		}
	}

	@FXML
	void save(ActionEvent event) {
		if (!exam.isManual()) {
			saveExamContent(false);
		} else {
			startSave = true;
			save_exam.setVisible(false);
			save_exam.setDisable(true);
			time_text.setVisible(false);
			upload_exam.setVisible(false);
			upload_exam.setDisable(true);
			startSave = true;
			StudentsExecutedExam studentsExecutedExam = new StudentsExecutedExam(false,
					exam.getExamTime() - minutesTime, this.user.getUserId(), file, true, this.executedExam);
			studentsExecutedExam.setSubmitted(true);
			saveManualExam(studentsExecutedExam);
		}
	}

	@FXML
	void saveManualExam(StudentsExecutedExam studentsExecutedExam) {

		Message msg = new Message();
		msg.setStudentsExecutedExam(studentsExecutedExam);
		msg.setAction("Submit Student Manual Exam");

		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		});
	}
}
