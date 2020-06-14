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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StudentExamExecutionController implements Initializable {

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
	private Text fileName;

	private HstsUser user;

	private Exam exam;

	private StudentsExecutedExam studentsExecutedExam;
	
	private Integer startTime;// time for exam in minutes

	private Integer hourTime;

	private Integer minutesTime;

	private Integer secondsTime;

	private boolean startSave = false;

	private Integer minutesLeft;

	private File file;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		studentsExecutedExam = new StudentsExecutedExam();
	}

	@FXML
	void menuClick(ActionEvent event) {

		if (event.getSource() == exam_execution_btn) {
			Stage stage = (Stage) exam_execution_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/StudentExamExecution.fxml"));
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
	}

	@FXML
	void enterSubmit(ActionEvent event) {

		ExamForExec examForExec = new ExamForExec();
		Message msg = new Message();
		examForExec.setExamCode(enterExamCode.getText());
		studentsExecutedExam.setExamCode(enterExamCode.getText());
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
	public void setExamToPage(Exam exam) {
		Platform.runLater(() -> {
			studentsExecutedExam.setUser(this.user);
			this.exam = exam;
			studentsExecutedExam.setExamID(exam.getExamID());
			System.out.println("num of question " + exam.getQuestions().size());
			hourTime = (exam.getTime()) / 60;
			minutesLeft = exam.getTime() % 60;
			startTime = exam.getTime();
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
				for_multi_line.setVisible(false);
				submit_btn.setDisable(false);
				submit_btn.setVisible(true);

				if (!exam.isManual()) {
					submit_btn.setVisible(false);
					start_exam_btn.setDisable(false);
					for_multi_line1.setVisible(true);
					enterIdForExam.setVisible(true);
					enterIdForExam.setDisable(false);
					start_exam_btn.setVisible(true);
					start_exam_btn.setLayoutY(225);

				} else {
					submit_btn.setVisible(false);
					downlod_btn.setVisible(true);
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
		//studentsExecutedExam.setManual(false);
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
		upload_exam.setLayoutX(270);
		upload_exam.setLayoutY(320);
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
				if (minutesTime <= 0 && secondsTime <= 0 && hourTime <= 0) {
					timeline.stop();
					submitExamBtn.setVisible(false);
					upload_exam.setVisible(false);

					if (startSave == false) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("time is up!");
						alert.show();
						studentsExecutedExam.setExecTime(exam.getTime());

					}
				} else {
					if (secondsTime == 0 && minutesTime > 0) {
						secondsTime = 60;
						minutesTime--;
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

			for_multi_line1.setVisible(false);
			enterIdForExam.setVisible(false);
			start_exam_btn.setVisible(false);
			submit_btn.setVisible(false);
			// save_exam.setVisible(true);

			Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
					+ secondsTime.toString());

			if (timeline != null) {
				timeline.stop();
			}
			System.out.println(exam.getQuestions().get(0).getQuestionContent());
			VBox displayExam = new VBox(15);
			displayExam.setAlignment(Pos.CENTER);
			Text instructions = new Text("Instructions: " + exam.getInstructions());
			displayExam.getChildren().add(instructions);
			Button save_btn = new Button();
			save_btn.setAlignment(Pos.CENTER);
			save_btn.setText("save exam");
			GridPane questionsGrid = new GridPane();
			questionsGrid.setAlignment(Pos.CENTER);
			Text examTitle = new Text("Exam in subject " + exam.getSubject() + " in course " + exam.getCourse());
			displayExam.getChildren().add(examTitle);
			studentsExecutedExam.setManual(false);
			for (int j = 0; j < exam.getQuestions().size(); j++) {
				VBox questionBox = new VBox(15);
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

				Text questionContent = new Text("" + (j + 1) + ". " + exam.getQuestions().get(j).getQuestionContent());
				Text answer1 = new Text("1. " + exam.getQuestions().get(j).getAnswer().get(0));
				Text answer2 = new Text("2. " + exam.getQuestions().get(j).getAnswer().get(1));
				Text answer3 = new Text("3. " + exam.getQuestions().get(j).getAnswer().get(2));
				Text answer4 = new Text("4. " + exam.getQuestions().get(j).getAnswer().get(3));

				answer1HBox.getChildren().add(answer1);
				answer2HBox.getChildren().add(answer2);
				answer3HBox.getChildren().add(answer3);
				answer4HBox.getChildren().add(answer4);

				questionBox.getChildren().add(questionContent);
				questionBox.getChildren().add(answer1HBox);
				questionBox.getChildren().add(answer2HBox);
				questionBox.getChildren().add(answer3HBox);
				questionBox.getChildren().add(answer4HBox);

				questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));

				questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));

				questionBox.setSpacing(15);

				questionsGrid.setVgap(10);
				questionBox.setStyle("-fx-background-color: #ADD8E6");
				questionsGrid.add(questionBox, 0, j, 1, 1);
				displayExam.getChildren().add(questionBox);

			}

			Text endTitle = new Text("GOOD LUCK!");
			displayExam.getChildren().add(endTitle);
			save_exam.setVisible(true);
			save_exam.setDisable(false);
			displayExam.getChildren().add(save_exam);
			exam_anchor.setLayoutX(205);
			exam_anchor.setLayoutY(120);
			time_text.setLayoutY(100);
			exam_anchor.getChildren().add(displayExam);

			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					/*
					 * if (startTime == 1) { secondsTime = 59; minutesTime = 0; }
					 */
					// startTime--;
					time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
							+ secondsTime.toString());
					if (startTime <= 0 && secondsTime <= 0 && hourTime <= 0) {

						timeline.stop();
						exam_anchor.setVisible(false);
						save_exam.setVisible(false);

						if (startSave == false) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("time is up!");
							alert.show();

						}
						startSave = false;
					} else {
						if (secondsTime == 0 && minutesTime > 0) {
							secondsTime = 60;
							minutesTime--;
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
		
		this.studentsExecutedExam.setChecked(false);
		startSave = true;
		ArrayList<Integer> chosenAnswers = new ArrayList<Integer>();
		int sizeOfAnswers = 0;
		this.studentsExecutedExam.setExecTime(exam.getTime() - startTime);
		this.studentsExecutedExam.setForcedFinish(isForced);
		this.studentsExecutedExam.setManual(false);

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

		this.studentsExecutedExam.setExecTime(exam.getTime() - startTime);
		this.studentsExecutedExam.setAnswersForExam(chosenAnswers);
		this.studentsExecutedExam.setUser(this.user);

		save_exam.setVisible(false);
		time_text.setVisible(false);
		exam_anchor.setVisible(false);
		
		Message msg = new Message();
		msg.setStudentsExecutedExam(this.studentsExecutedExam);
		
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
		}
	}

	@FXML
	void save(ActionEvent event) {
		if (!exam.isManual()) {
			saveExamContent(false);
		} else {
			startSave = true;
			studentsExecutedExam.setForcedFinish(false);
			studentsExecutedExam.setExamFile(file);
			studentsExecutedExam.setExecTime(exam.getTime() - startTime);
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
