package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import HSTS_Entities.Exam;
import HSTS_Entities.ExamForExec;
import HSTS_Entities.HstsUser;
import HSTS_Entities.Message;
import HSTS_Entities.TimeExtension;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TeacherExamExecutionController implements Initializable {

	@FXML
	private Button request_time_btn;

	@FXML
	private TextArea reasons_text;

	@FXML
	private TextField enter_time_text;

	@FXML
	private Text time_text;

	private HstsUser user;

	private ExamForExec examForExec;

	private Exam exam;

	private Integer hourTime;
	private Integer minutesTime;
	private Integer secondsTime;
	private Integer startTime;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}

	@FXML
	void sendRequest(ActionEvent event) {
		TimeExtension requestedTime = new TimeExtension(exam.getExamID(), exam.getSubject(), exam.getCourse(), reasons_text.getText(),
				Integer.valueOf(enter_time_text.getText()), false, true);
		Message msgToServer = new Message();

		msgToServer.setAction("Request time extension");
		msgToServer.setTimeExtension(requestedTime);

		try {
			AppsClient.getClient().sendToServer(msgToServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onUserEvent(HstsUser user) {
		this.user = user;
	}

	@Subscribe
	public void onExamExecEvent(ExamForExec examForExec) {
		Message msgToServer = new Message();
		msgToServer.setAction("Pull exam by examCode");
		System.out.println(examForExec.getExamID());
		msgToServer.setExamForExec(examForExec);

		try {
			AppsClient.getClient().sendToServer(msgToServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onExamEvent(Exam exam) {
		Platform.runLater(() -> {
			this.exam = exam;

			startTime = exam.getTime();
			hourTime = exam.getTime() / 60;

			System.out.println(hourTime);

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

			Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
					+ secondsTime.toString());
			if (timeline != null) {
				timeline.stop();
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
		});
	}
}