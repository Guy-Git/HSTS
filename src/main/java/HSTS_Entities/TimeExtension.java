package HSTS_Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "time_extensions")
public class TimeExtension implements Serializable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String subject;
	
	private String course;
	
	private String examID;
	
	@Column(length = 100000)
	private String reason;
	
	private int requestedTime;
	
	private boolean approved;
	
	private boolean status;

	public TimeExtension(String examID, String subject, String course, String reason, int requestedTime, boolean approved, boolean status) {
		super();
		this.examID = examID;
		this.subject = subject;
		this.course = course;
		this.reason = reason;
		this.requestedTime = requestedTime;
		this.approved = approved;
		this.status = status;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(int requestedTime) {
		this.requestedTime = requestedTime;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
