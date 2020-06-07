package HSTS_Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exec_exams")
public class ExamForExec implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String examID;
	private boolean isManual;
	private String examCode;
	
	public ExamForExec() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamForExec(String examID, boolean isManual, String examCode) {
		super();
		this.examID = examID;
		this.isManual = isManual;
		this.examCode = examCode;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public boolean isManual() {
		return isManual;
	}

	public void setManual(boolean isManual) {
		this.isManual = isManual;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	
	
	
}
