package HSTS_Client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.HstsUser;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class TeacherExamExecutionController implements Initializable {

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
    private Accordion exams_container;

    @FXML
    private TextField exam_code_text;

    @FXML
    private ToggleGroup isManual;

    @FXML
    private Button saveBtn;
    
	private HstsUser user;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
    	EventBus.getDefault().register(this);
        
    	exams_container.getPanes().add(new TitledPane("Exam #KAKIFAKI", new Text("KAKIZ")));
    }

    @FXML
    void menuClick(ActionEvent event) {

    }

    @FXML
    void pullExams(ActionEvent event) {

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

			chooseSubject.setItems(setToSubjects);
			chooseCourse.setItems(setToCourse);
		});
	}

    
}
