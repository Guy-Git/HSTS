package HSTS_Client;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TeacherExamExecutionController implements Initializable {

	@FXML
    private Button about_btn;

	@FXML
	private Button review_btn;

    @FXML
    private Button log_out_btn;

    @FXML
    private AnchorPane logo;

    @FXML
    private Text logo_text;

    @FXML
    private Button create_question_btn;

    @FXML
    private Button edit_question_btn;

    @FXML
    private Button create_exam_btn;

    @FXML
    private Button edit_exam_btn;

    @FXML
    private Button exam_execution_btn;

    @FXML
    private Button watch_reports_btn;

    @FXML
    private Button main_page_btn;

    @FXML
    private Text message_text;
	
	@FXML
	private Button request_time_btn;

	@FXML
    private TextArea enter_reasons_text;

	 @FXML
	 private Accordion accordion;
	
    @FXML
    private Text reasons_text;

    @FXML
    private Text asked_time_text;

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
		
		if (event.getSource() == review_btn) {
			Stage stage = (Stage) review_btn.getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/ExamsReview.fxml"));
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
		
			if (event.getSource() == edit_question_btn) {
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
			
			if (event.getSource() == main_page_btn) {
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
			
			if (event.getSource() == about_btn) {
				Stage stage = (Stage) about_btn.getScene().getWindow();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/HSTS_Client/TeacherAbout.fxml"));
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
			

	}

	
	@FXML
	void sendRequest(ActionEvent event) {
		boolean badInput = false;

		try {
			if (enter_reasons_text.getText().isEmpty()) {
				enter_reasons_text.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
				badInput = true;
			} else {
				enter_reasons_text.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
			}

			if (enter_time_text.getText().isEmpty() || !enter_time_text.getText().matches("[0-9]+")) {
				enter_time_text.setStyle("-fx-border-color: RED; -fx-border-radius: 10; -fx-background-color: transparent;");
				badInput = true;
			} else {
				enter_time_text.setStyle("-fx-background-color: #1E242E; -fx-text-inner-color: white; -fx-background-radius: 10;");
			}
		}

		catch (ClassCastException cce) {
		}

		if (badInput == false) {
			TimeExtension requestedTime = new TimeExtension(exam.getExamID(), exam.getSubject(), exam.getCourse(),
					enter_reasons_text.getText(), Integer.valueOf(enter_time_text.getText()), false, true,
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

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("The fields marked red" + "\n" + "are empty or illegal");
			alert.setTitle("");
			alert.show();
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

			//System.out.println(msg.getAction());
			if (checkedExtentions == true) {
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

				//System.out.println(hourTime);

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
			time_text.setText(hourTime.toString() + " : " + minutesTime.toString() + " : "
					+ secondsTime.toString());
			

			KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					/*
					 * if (startTime == 1) { secondsTime = 59; minutesTime = 0; }
					 */
					// startTime--;
					if(minutesTime < 5 && hourTime == 0)
					{
						request_time_btn.setDisable(true);
						reasons_text.setDisable(true);
						enter_time_text.setDisable(true);
						enter_reasons_text.setDisable(true);
						asked_time_text.setDisable(true);
					}
					
					if (minutesTime == 5 && secondsTime == 0 && hourTime == 0 && checkedExtentions == false) {
						request_time_btn.setDisable(true);
						reasons_text.setDisable(true);
						enter_time_text.setDisable(true);
						enter_reasons_text.setDisable(true);
						asked_time_text.setDisable(true);
						
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

					time_text.setText(hourTime.toString() + " : " + minutesTime.toString() + " : "
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