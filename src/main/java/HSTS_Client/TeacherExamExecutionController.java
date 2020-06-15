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
	private Integer minutesLeft;

	private boolean checkedExtentions = false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
	}

	@FXML
	void sendRequest(ActionEvent event) {
		TimeExtension requestedTime = new TimeExtension(exam.getExamID(), exam.getSubject(), exam.getCourse(),
				reasons_text.getText(), Integer.valueOf(enter_time_text.getText()), false, true,
				examForExec.getExamCode());
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
		this.examForExec = examForExec;
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
	public void onExamEvent(Message msg) {
		Platform.runLater(() -> {

			System.out.println(msg.getAction());
			if (checkedExtentions == true) 
			{
				System.out.println("KAKI");
				minutesLeft = (5 + msg.getExtendTime()) % 60;
				startTime += msg.getExtendTime();
				hourTime = minutesLeft / 60;
				if (startTime < 60) {
					minutesTime = startTime;
				} else {
					minutesTime = minutesLeft;
				}
			}

			else {
				this.exam = msg.getExam();
				startTime = exam.getExamTime();
				hourTime = exam.getExamTime() / 60;
				minutesLeft = exam.getExamTime() % 60;

				System.out.println(hourTime);

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
			}

			Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
					+ secondsTime.toString());
			/*if (timeline != null) {
				System.out.println("jjjjjjjjjjj");
				timeline.stop();
			}*/

			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					/*
					 * if (startTime == 1) { secondsTime = 59; minutesTime = 0; }
					 */
					// startTime--;
					if (minutesTime == 5 && secondsTime == 0 && hourTime == 0 && checkedExtentions == false) 
					{
						timeline.stop();
						checkedExtentions = true;
						Message msgToServer = new Message();
						msgToServer.setAction("Check for extension");
						msgToServer.setExamForExec(examForExec);

						try {
							AppsClient.getClient().sendToServer(msgToServer);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
					
					time_text.setText("time left: " + hourTime.toString() + " : " + minutesTime.toString() + " : "
							+ secondsTime.toString());
					if (minutesTime <= 0 && secondsTime <= 0 && hourTime <= 0) {
						timeline.stop();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("time is up!");
						alert.show();
						
						
						
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
		});
	}
}