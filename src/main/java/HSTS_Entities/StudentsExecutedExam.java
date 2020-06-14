package HSTS_Entities;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.fxml.Initializable;

@Entity
@Table(name = "student_exec_exam")
public class StudentsExecutedExam implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean forcedFinish;

	private int execTime;

	HstsUser user;

	private ArrayList<Integer> answersForExam;

	private boolean isManual;

	private File examFile;
	
	private String examID;
	
	private String examCode;
	
	private int grade;
	
	boolean checked;
	
	private ArrayList<Integer> checkedAnswers;


	// public StudentsExecutedExam studentsExecutedExam;

	public StudentsExecutedExam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isForcedFinish() {
		return forcedFinish;
	}

	public void setForcedFinish(boolean forcedFinish) {
		this.forcedFinish = forcedFinish;
	}

	public int getExecTime() {
		return execTime;
	}

	public void setExecTime(int execTime) {
		this.execTime = execTime;
	}

	public HstsUser getUser() {
		return user;
	}

	public void setUser(HstsUser user) {
		this.user = user;
	}

	public ArrayList<Integer> getAnswersForExam() {
		return answersForExam;
	}

	public void setAnswersForExam(ArrayList<Integer> answersForExam) {
		this.answersForExam = answersForExam;
	}

	public boolean isManual() {
		return isManual;
	}

	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}

	public File getExamFile() {
		return examFile;
	}

	public void setExamFile(File examFile) {
		this.examFile = examFile;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public ArrayList<Integer> getCheckedAnswers() {
		return checkedAnswers;
	}

	public void setCheckedAnswers(ArrayList<Integer> checkedAnswers) {
		this.checkedAnswers = checkedAnswers;
	}
	

	
}
