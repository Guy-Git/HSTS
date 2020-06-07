package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
	String action;
	Question question;
	Exam exam;
	HstsUser user;
	String course;
	String subject;
	String execCode;
	String userID;
	
	ArrayList<Question> questions;

	ArrayList<Exam> exams; //For report
	ArrayList<String> subjects; //For report
	ArrayList<String> courses; //For report
	ExamForExec examForExec;
	
	public ExamForExec getExamForExec() {
		return examForExec;
	}

	public void setExamForExec(ExamForExec examForExec) {
		this.examForExec = examForExec;
	}

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
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public ArrayList<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

	public String getExecCode() {
		return execCode;
	}

	public void setExecCode(String execCode) {
		this.execCode = execCode;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	
}
