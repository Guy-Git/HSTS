package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "exam")
public class Exam implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_id")
	private long id;

	@ManyToMany(fetch = FetchType.EAGER, 
			cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
			targetEntity = Question.class)
	@JoinTable(name = "exam_question", joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questions;

	private String examID;
	
	@Column(length = 100000)
	private String instructions;
	
	@Column(length = 100000)
	private String notes;
	
	private String teacherName;
	private int examTime;
	private boolean manual;
	private ArrayList<Integer> questionGrade;
	String examSubject;
	String course;

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(List<Question> questions, String instructions, String notes, String teacherName, int examTime,
			ArrayList<Integer> questionGrade, String subject, String course) {
		super();
		this.questions = new ArrayList<Question>();
		this.instructions = instructions;
		this.notes = notes;
		this.teacherName = teacherName;
		this.examTime = examTime;
		this.questionGrade = questionGrade;
		this.examSubject = subject;
		this.course = course;

	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
		for (Question question : questions) {
			question.getExams().add(this);
		}
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getExamTime() {
		return examTime;
	}

	public void setExamTime(int examTime) {
		this.examTime = examTime;
	}

	public ArrayList<Integer> getQuestionGrade() {
		return questionGrade;
	}

	public void setQuestionGrade(ArrayList<Integer> questionGrade) {
		this.questionGrade = questionGrade;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getSubject() {
		return examSubject;
	}

	public void setSubject(String subject) {
		this.examSubject = subject;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public boolean isManual() {
		return manual;
	}

	public void setManual(boolean manual) {
		this.manual = manual;
	}
	
	

}
