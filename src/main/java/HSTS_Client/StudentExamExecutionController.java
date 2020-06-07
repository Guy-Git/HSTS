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
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentExamExecutionController implements Initializable {

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

	private HstsUser user;

	private Exam exam;

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

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (event.getSource() == exam_execution_btn) {
			Stage stage = (Stage) exam_execution_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/CreateExam.fxml"));
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
			if (exam == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Exam code is incorrect! \nTry again!");
				alert.setTitle("");
				alert.show();
			} else {

				if (!exam.isManual()) {
					submit_btn.setVisible(false);
					enterExamCode.setVisible(false);
					enterIdForExam.setVisible(true);
					
					/*Message msg = new Message();
					msg.setAction("Check student ID");
					msg.setUserID(enterIdForExam.getText());
					try {
						AppsClient.getClient().sendToServer(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/

				} else {
					downlod_btn.setVisible(true);

				}
			}
		});
	}

	@FXML
	void onDownlodeEvent(ActionEvent event) {
		XWPFDocument document = new XWPFDocument();
		try {
			FileOutputStream out = new FileOutputStream(new File("C:/poiword/alignparagraph.docx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = paragraph.createRun();
		run.setText("Exam in course " + exam.getCourse() + " the subject is " + exam.getSubject());
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setText("instructions: " + exam.getInstructions());

		for (int i = 0; i < exam.getQuestions().size(); i++) {
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			run = paragraph.createRun();
			run.setText((i + 1) + ". " + exam.getQuestions().get(i).getQuestionContent() + "\n 1."
					+ exam.getQuestions().get(i).getAnswer().get(0) + "\n 2."
					+ exam.getQuestions().get(i).getAnswer().get(1) + "\n 3."
					+ exam.getQuestions().get(i).getAnswer().get(2) + "\n 4."
					+ exam.getQuestions().get(i).getAnswer().get(3));
		}

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setText("Good Luck!");
	}
	
    @FXML
    void startExam(ActionEvent event) {
    	
    	if(enterIdForExam.getText().equals(user.getUserId()))
    	{
    		
   		}
    	else {
    		
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

			if (subjects.get(0) != null && courses.get(0) != "") {
				subjects.add(0, null);
				courses.add(0, "");
			}

			ObservableList<String> setToSubjects = FXCollections.observableArrayList(subjects);
			ObservableList<String> setToCourse = FXCollections.observableArrayList(courses);

		});
	}
}
