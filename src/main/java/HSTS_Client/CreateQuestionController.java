package HSTS_Client;

import java.io.IOException;
import java.util.ArrayList;

import HSTS_Entities.Message;
import HSTS_Entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.stage.Popup;

public class CreateQuestionController {

	@FXML
	private TextArea subjectText;

	@FXML
	private TextArea courseText;

	@FXML
	private TextArea contentText;

	@FXML
	private ToggleGroup right_answer;

	@FXML
	private RadioButton rightAnswer1;

	@FXML
	private RadioButton rightAnswer2;

	@FXML
	private RadioButton rightAnswer3;

	@FXML
	private RadioButton rightAnswer4;

	@FXML
	private TextArea answer1Text;

	@FXML
	private TextArea answer2Text;

	@FXML
	private TextArea answer3Text;

	@FXML
	private TextArea answer4Text;

	@FXML
	private Button saveBtn;

	@FXML
	void onClick(ActionEvent event) {
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(answer1Text.getText());
		answers.add(answer2Text.getText());
		answers.add(answer3Text.getText());
		answers.add(answer4Text.getText());
		Question question;
		int rightAnswer = 0;
		boolean badInput = false;

		if (subjectText.getText().isEmpty()) {
			subjectText.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			subjectText.setStyle("-fx-background-color: #00bfff");
			badInput = false;
		}

		if (courseText.getText().isEmpty()) {
			courseText.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			badInput = false;
		}

		if (contentText.getText().isEmpty()) {
			contentText.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			badInput = false;
		}

		if (answer1Text.getText().isEmpty()) {
			answer1Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			badInput = false;
		}

		if (answer2Text.getText().isEmpty()) {
			answer2Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			badInput = false;
		}

		if (answer3Text.getText().isEmpty()) {
			answer3Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			badInput = false;
		}

		if (answer4Text.getText().isEmpty()) {
			answer4Text.setStyle("-fx-background-color: RED");
			badInput = true;
		} else {
			badInput = false;
		}

		if (badInput == false) {
			if (right_answer.getSelectedToggle() == rightAnswer1) {
				rightAnswer = 1;
			} else if (right_answer.getSelectedToggle() == rightAnswer2) {
				rightAnswer = 2;
			} else if (right_answer.getSelectedToggle() == rightAnswer3) {
				rightAnswer = 3;
			} else if (right_answer.getSelectedToggle() == rightAnswer4) {
				rightAnswer = 4;
			}

			question = new Question(contentText.getText(), answers, rightAnswer, courseText.getText(),
					Integer.parseInt(subjectText.getText()));

			Message msg = new Message();
			msg.setQuestion(question);
			msg.setAction("Create Question");
			
			try {
				AppsClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
