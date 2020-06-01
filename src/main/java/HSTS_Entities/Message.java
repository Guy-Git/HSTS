package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
	String action;
	Question question;
	Exam exam;
	HstsUser user;
	
	ArrayList<Exam> exams; //For report
	ArrayList<Integer> subjects; //For report
	ArrayList<String> courses; //For report
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public HstsUser getUser() {
		return user;
	}

	public void setUser(HstsUser user) {
		this.user = user;
	}

	public ArrayList<Exam> getExams() {
		return exams;
	}

	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

	public ArrayList<Integer> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Integer> subjects) {
		this.subjects = subjects;
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}
	
	
}