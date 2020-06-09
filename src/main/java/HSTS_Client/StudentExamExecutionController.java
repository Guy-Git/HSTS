package HSTS_Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.Exam;
import HSTS_Entities.ExamForExec;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

	private HstsUser user;

	private Exam exam;

	//private ExamForExec examForExec;
	
	private Integer startTime;// time for exam in minutes

	private Integer hourTime;

	private Integer minutesTime;

	private Integer secondsTime;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
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

		Message msg = new Message();
	//	ExamForExec.setExamCode(enterExamCode.getText())
   //	msg.setExamForExec(examForExec);
	  
		msg.setAction("Enter code");
		msg.setExecCode(enterExamCode.getText());

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
			this.exam = exam;
			hourTime = (exam.getTime()) / 60;
			startTime = exam.getTime();
			if (startTime < 60) {
				minutesTime = startTime;
				if (minutesTime == 1) {
					minutesTime = 1;
					secondsTime = 0;
				}
			} else {
				minutesTime = 59;
				secondsTime = 59;
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
		submit_btn.setVisible(false);
		downlod_btn.setVisible(false);
		save_exam.setVisible(true);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		Button submitExamBtn = new Button();
		time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : " + secondsTime.toString());
		
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
		run.setText("instructions: " + exam.getInstructions());

		for (int i = 0; i < exam.getQuestions().size(); i++) {
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setText("");
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setText((i + 1) + ". " + exam.getQuestions().get(i).getQuestionContent());
			
			for (int j=0 ; j < 4 ; j++)
			{
				paragraph = document.createParagraph();
				paragraph.setAlignment(ParagraphAlignment.RIGHT);
				run = paragraph.createRun();
				run.setText("  " + (j + 1) + ". " + exam.getQuestions().get(i).getAnswer().get(j));
			}

		}

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setText("Good Luck!");

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
				// TODO Auto-generated method stub
				if (startTime == 1) {
					secondsTime = 59;
					minutesTime = 0;
				}
				startTime--;
				time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
						+ secondsTime.toString());
				if (startTime <= 0 && secondsTime <= 0) {
					timeline.stop();
					submitExamBtn.setVisible(false);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("time is up!");
					alert.show();
				} else {
					secondsTime--;
					if (secondsTime == 0 && minutesTime > 0) {
						secondsTime = 59;
						minutesTime--;
					}
					if (minutesTime == 1 && secondsTime == 0) {
						minutesTime = 0;
						secondsTime = 59;
					}
					if (hourTime > 0 && secondsTime == 0 && minutesTime == 0) {
						hourTime--;
						minutesTime = 59;
						secondsTime = 59;
					}
				}
			}

		});
		timeline.getKeyFrames().add(frame);
		timeline.playFromStart();
	}
	

	@FXML
	void startExam(ActionEvent event) {
		if (enterIdForExam.getText().equals(user.getUserId())) {

			for_multi_line1.setVisible(false);
			enterIdForExam.setVisible(false);
			start_exam_btn.setVisible(false);
			submit_btn.setVisible(false);
			//save_exam.setVisible(true);
			
			Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
					+ secondsTime.toString());

			if (timeline != null) {
				timeline.stop();
			}

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

			for (int j = 0; j < exam.getQuestions().size(); j++) {
				VBox questionBox = new VBox(15);

				Text questionContent = new Text("" + (j + 1) + ". " + exam.getQuestions().get(j).getQuestionContent());
				Text answer1 = new Text("1. " + exam.getQuestions().get(j).getAnswer().get(0));
				Text answer2 = new Text("2. " + exam.getQuestions().get(j).getAnswer().get(1));
				Text answer3 = new Text("3. " + exam.getQuestions().get(j).getAnswer().get(2));
				Text answer4 = new Text("4. " + exam.getQuestions().get(j).getAnswer().get(3));

				questionBox.getChildren().add(questionContent);
				questionBox.getChildren().add(answer1);
				questionBox.getChildren().add(answer2);
				questionBox.getChildren().add(answer3);
				questionBox.getChildren().add(answer4);

				questionBox.setMargin(questionContent, new Insets(0, 0, 0, 5));

				questionBox.setMargin(answer1, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer2, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer3, new Insets(0, 0, 0, 35));
				questionBox.setMargin(answer4, new Insets(0, 0, 0, 35));

				questionBox.setSpacing(15);

				// questionsGrid.setVgap(10);
				questionBox.setStyle("-fx-background-color: #ADD8E6");
				questionsGrid.add(questionBox, 0, 1, 1, 1);
				displayExam.getChildren().add(questionBox);

			}
			Text endTitle = new Text("GOOD LUCK!");
			displayExam.getChildren().add(endTitle);
			displayExam.getChildren().add(save_btn);
			exam_anchor.setLayoutX(205);
			exam_anchor.setLayoutY(120);
			exam_anchor.getChildren().add(displayExam);

			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if (startTime == 1) {
						secondsTime = 59;
						minutesTime = 0;
					}
					startTime--;
					time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
							+ secondsTime.toString());
					if (startTime <= 0 && secondsTime <= 0) {
						timeline.stop();
						exam_anchor.setVisible(false);
						save_exam.setVisible(false);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("time is up!");
						alert.show();
					} else {
						secondsTime--;
						if (secondsTime == 0 && minutesTime > 0) {
							secondsTime = 59;
							minutesTime--;
						}
						if (minutesTime == 1 && secondsTime == 0) {
							minutesTime = 0;
							secondsTime = 59;
						}
						if (hourTime > 0 && secondsTime == 0 && minutesTime == 0) {
							hourTime--;
							minutesTime = 59;
							secondsTime = 59;
						}
					}
				}

			});
			timeline.getKeyFrames().add(frame);
			timeline.playFromStart();

		} else {

		}

	}

	@FXML
	void save(ActionEvent event) {
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
