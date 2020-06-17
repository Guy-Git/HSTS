package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.Question;
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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

public class CreateQuestionController implements Initializable {

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
	private TextArea contentText;

	@FXML
	private RadioButton rightAnswer1;

	@FXML
	private ToggleGroup right_answer;

	@FXML
	private RadioButton rightAnswer2;

	@FXML
	private RadioButton rightAnswer3;

	@FXML
	private RadioButton rightAnswer4;

	@FXML
	private TextField answer1Text;

	@FXML
	private TextField answer2Text;

	@FXML
	private TextField answer3Text;

	@FXML
	private TextField answer4Text;

	@FXML
	private Button saveBtn;

	@FXML
	private Button clearBtn;

	@FXML
	private ChoiceBox<String> chooseSubject;

	@FXML
	private ChoiceBox<String> chooseCourse;

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

	private HstsUser user;

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
					Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherAboutPage.fxml"));
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
	void save(ActionEvent event) {
		ArrayList<String> answers = new ArrayList<String>();
		Question question;
		int rightAnswer = 0;
		boolean badInput = false;

		if (chooseSubject.getSelectionModel().isEmpty()
				|| chooseSubject.getValue().isEmpty()) {
			chooseSubject.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10;");
			badInput = true;
		} else {
			chooseSubject.setStyle("-fx-background-color: #3F4E63; -fx-background-radius: 10;");
		}

		if (chooseCourse.getSelectionModel().isEmpty()
				|| chooseCourse.getValue().isEmpty()) {
			chooseCourse.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10;");
			badInput = true;
		} else {
			chooseCourse.setStyle("-fx-background-color: #3F4E63; -fx-background-radius: 10;");
		}

		if (contentText.getText().isEmpty()) {
			contentText.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10;");
			badInput = true;
		} else {
			contentText.setStyle("-fx-background-color: #3F4E63; -fx-background-radius: 10;");
		}

		if (answer1Text.getText().isEmpty()) {
			answer1Text.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10;");
			badInput = true;
		} else {
			answer1Text.setStyle("-fx-background-color: #3F4E63; -fx-background-radius: 10;");
		}

		if (answer2Text.getText().isEmpty()) {
			answer2Text.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10;");
			badInput = true;
		} else {
			answer2Text.setStyle("-fx-background-color: #3F4E63; -fx-background-radius: 10;");
		}

		if (answer3Text.getText().isEmpty()) {
			answer3Text.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10;");
			badInput = true;
		} else {
			answer3Text.setStyle("-fx-background-color: #3F4E63; -fx-background-radius: 10;");
		}

		if (answer4Text.getText().isEmpty()) {
			answer4Text.setStyle("-fx-background-color: Trasnparent; -fx-border-color: RED; -fx-border-radius: 10;");
			badInput = true;
		} else {
			answer4Text.setStyle("-fx-background-color: #3F4E63; -fx-background-radius: 10;");
		}

		if (badInput == false) 
		{
			if (right_answer.getSelectedToggle() == rightAnswer1) {
				rightAnswer = 1;
			} else if (right_answer.getSelectedToggle() == rightAnswer2) {
				rightAnswer = 2;
			} else if (right_answer.getSelectedToggle() == rightAnswer3) {
				rightAnswer = 3;
			} else if (right_answer.getSelectedToggle() == rightAnswer4) {
				rightAnswer = 4;
			}

			answers.add(answer1Text.getText());
			answers.add(answer2Text.getText());
			answers.add(answer3Text.getText());
			answers.add(answer4Text.getText());

			question = new Question(contentText.getText(), answers, rightAnswer, chooseCourse.getValue(),
					chooseSubject.getValue());

			Message msg = new Message();
			msg.setQuestion(question);
			msg.setAction("Create Question");

			try {
				AppsClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red must be filled");
			alert.setTitle("");
			// alert.setContentText("The fields marked red must be filled");
			alert.show();
		}
	}

	@FXML
	void clear(ActionEvent event) {
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

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;
			ArrayList<String> subjects = new ArrayList<String>();
			ArrayList<String> courses = new ArrayList<String>();
			subjects = user.getSubjects();
			courses = user.getCourses();

			if (subjects.get(0) != "" && courses.get(0) != "") {
				subjects.add(0, "");
				courses.add(0, "");
			}

			ObservableList<String> setToSubjects = FXCollections.observableArrayList(subjects);
			ObservableList<String> setToCourse = FXCollections.observableArrayList(courses);

			chooseSubject.setItems(setToSubjects);
			chooseCourse.setItems(setToCourse);
		});
	}
}
