package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.HstsUser;
import HSTS_Entities.Question;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import HSTS_Entities.Message;

public class CreateExamController implements Initializable {

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
	private ChoiceBox<Integer> chooseSubject;

	@FXML
	private ChoiceBox<String> chooseCourse;

	@FXML
	private Button save_btn;

	@FXML
	private GridPane questions_grid;

	@FXML
	private Button show_question_btn;

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

//		if (event.getSource() == exam_execution_btn) 
//		if (event.getSource() == watch_reports_btn) 
//		if (event.getSource() == about_btn) 

	}

	@FXML
	void save(ActionEvent event) {

	}

	@FXML
	void showQuestions(ActionEvent event) {

		Message msg = new Message();
		msg.setSubject(chooseSubject.getValue());
		msg.setCourse(chooseCourse.getValue());
		msg.setAction("Show Questions");

		try {
			AppsClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Subscribe
	public void setQuestionsToPage(ArrayList<Question> questions) {
		Platform.runLater(() -> {
			for (int i = 0; i < questions.size(); i++) {
				VBox questionBox = new VBox();
				questionBox.setPrefWidth(620);
				questionBox.setMinHeight(200);
				Text questionContent = new Text(""+(i+1)+". "+questions.get(i).getQuestionContent());
				Text answer1 = new Text("1. "+questions.get(i).getAnswer().get(0));
				Text answer2 = new Text("2. "+questions.get(i).getAnswer().get(1));
				Text answer3 = new Text("3. "+questions.get(i).getAnswer().get(2));
				Text answer4 = new Text("4. "+questions.get(i).getAnswer().get(3));
				Text rightAnswer = new Text("The right answer is: "+String.valueOf(questions.get(i).getRightAnswer()));
				Text gradeText = new Text("Add grade for chosen question: ");
				TextField questionGrade = new TextField();
				
				questionBox.getChildren().add(questionContent);
				questionBox.getChildren().add(answer1);
				questionBox.getChildren().add(answer2);
				questionBox.getChildren().add(answer3);
				questionBox.getChildren().add(answer4);
				questionBox.getChildren().add(rightAnswer);
				questionBox.getChildren().add(questionGrade);
				questionBox.getChildren().add(gradeText);
				/*questionContent.setLayoutX(50);
				answer1.setLayoutX(100);
				answer1.setLayoutY(30);
				answer2.setLayoutX(100);
				answer2.setLayoutY(60);
				answer3.setLayoutX(100);
				answer3.setLayoutY(90);
				answer4.setLayoutX(100);
				answer4.setLayoutY(120);
				rightAnswer.setLayoutX(200);
				rightAnswer.setLayoutY(150);
				gradeText.setLayoutX(50);
				gradeText.setLayoutY(180);			
				questionGrade.setLayoutX(200);
				questionGrade.setLayoutY(180);*/
				
				questionBox.setSpacing(30);
				
				//answer1.setLayoutY(200);
				//questions_grid.set
				questions_grid.setPadding(new Insets(10));
				questionBox.setStyle("-fx-background-color: #deb887");
			    questions_grid.add(questionBox,i,0,1,1);
			
			}
		});
	}

	@Subscribe
	public void onUserEvent(HstsUser user) {
		Platform.runLater(() -> {
			this.user = user;
			ArrayList<Integer> subjects = new ArrayList<Integer>();
			ArrayList<String> courses = new ArrayList<String>();
			subjects = user.getSubjects();
			courses = user.getCourses();

			if (subjects.get(0) != null && courses.get(0) != "") {
				subjects.add(0, null);
				courses.add(0, "");
			}

			ObservableList<Integer> setToSubjects = FXCollections.observableArrayList(subjects);
			ObservableList<String> setToCourse = FXCollections.observableArrayList(courses);

			chooseSubject.setItems(setToSubjects);
			chooseCourse.setItems(setToCourse);
		});
	}
}
