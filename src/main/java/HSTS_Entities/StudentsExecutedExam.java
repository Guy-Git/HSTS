package HSTS_Entities;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javafx.fxml.Initializable;

@Entity
@Table(name = "students_exam")
public class StudentsExecutedExam implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "student_exec_id")
	private int id;

	private boolean forcedFinish;

	private int execTime;

	private String userId;

	private ArrayList<Integer> answersForExam;

	private boolean isManual;

	private File examFile;

	private int examGrade;

	private boolean isChecked;

	private String notes;
		
	boolean submitted = false;
	
	private String reasonOfGradeChange="";

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "executed_exam_id")
	private ExecutedExam executedExam;

	public StudentsExecutedExam(boolean forcedFinish, int execTime, String userId, ArrayList<Integer> answersForExam,
			boolean isManual, boolean checked, ExecutedExam executedExam) {
		super();
		this.forcedFinish = forcedFinish;
		this.execTime = execTime;
		this.userId = userId;
		this.answersForExam = answersForExam;
		this.isManual = isManual;
		this.isChecked = checked;
		setExecutedExam(executedExam);
	}

	public StudentsExecutedExam(boolean forcedFinish, int execTime, String userId, File examFile, boolean isManual,
			ExecutedExam executedExam) {
		super();
		this.forcedFinish = forcedFinish;
		this.execTime = execTime;
		this.userId = userId;
		this.examFile = examFile;
		this.isManual = isManual;
		setExecutedExam(executedExam);
	}

	public StudentsExecutedExam() {
		// super();
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

	public int getGrade() {
		return examGrade;
	}

	public void setGrade(int examGrade) {
		this.examGrade = examGrade;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		this.isChecked = checked;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ExecutedExam getExecutedExam() {
		return executedExam;
	}

	public void setExecutedExam(ExecutedExam executedExam) {
		this.executedExam = executedExam;
		executedExam.getStudentsExecutedExams().add(this);
		}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submited) {
		this.submitted = submited;
	}
	

	public int getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(int examGrade) {
		this.examGrade = examGrade;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getReasonOfGradeChange() {
		return reasonOfGradeChange;
	}

	public void setReasonOfGradeChange(String reasonOfGradeChange) {
		this.reasonOfGradeChange = reasonOfGradeChange;
	}
}
