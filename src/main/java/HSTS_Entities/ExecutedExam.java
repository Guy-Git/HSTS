package HSTS_Entities;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.fxml.Initializable;

@Entity
@Table(name = "executed_exam")
public class ExecutedExam implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "executed_exam_id")
	private int id;
	
	private int numOfStudents;
	
	private int timeAndDate;
	
	private int numForced;
	
	private int numUnforced;
	
	private int timeOfExec;

	private String examID;
	
	private String examCode;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "executedExam")
	private List<StudentsExecutedExam> studentsExecutedExams;
	
	public ExecutedExam() {
		//super();
		// TODO Auto-generated constructor stub
	}
 
	public ExecutedExam(String examID, String examCode) {
		super();
		this.examID = examID;
		this.examCode = examCode;
		this.studentsExecutedExams = new ArrayList<StudentsExecutedExam>();
	}

	public List<StudentsExecutedExam> getStudentsExecutedExams() {
		return studentsExecutedExams;
	}

	public void setStudentsExecutedExams(List<StudentsExecutedExam> studentsExecutedExams) {
		this.studentsExecutedExams = studentsExecutedExams;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}

	public int getTimeAndDate() {
		return timeAndDate;
	}

	public void setTimeAndDate(int timeAndDate) {
		this.timeAndDate = timeAndDate;
	}

	public int getNumForced() {
		return numForced;
	}

	public void setNumForced(int numForced) {
		this.numForced = numForced;
	}

	public int getNumUnforced() {
		return numUnforced;
	}

	public void setNumUnforced(int numUnforced) {
		this.numUnforced = numUnforced;
	}

	public int getTimeOfExec() {
		return timeOfExec;
	}

	public void setTimeOfExec(int timeOfExec) {
		this.timeOfExec = timeOfExec;
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

	@Override
	public String toString() {
		return "ExecutedExam [id=" + id + ", numOfStudents=" + numOfStudents + ", timeAndDate=" + timeAndDate
				+ ", numForced=" + numForced + ", numUnforced=" + numUnforced + ", timeOfExec=" + timeOfExec
				+ ", examID=" + examID + ", examCode=" + examCode + ", studentsExecutedExams=" + studentsExecutedExams
				+ "]";
	}


}
