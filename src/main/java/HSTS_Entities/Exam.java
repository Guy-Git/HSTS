package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "exams")
public class Exam implements Serializable
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "exams_id")
	private long id;
	
	@ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Question.class)
	@JoinTable(name = "exams_questions", joinColumns = @JoinColumn(name = "exams_id"), inverseJoinColumns = @JoinColumn(name = "questions_id"))
	private List<Question> questions;
	
	private String notes;
	private String teacherName;
	private int time;
	private ArrayList<Integer> questionGrade;
	
	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(ArrayList<Question> questions, String notes, String teacherName, int time, ArrayList<Integer> questionGrade) 
	{
		super();
		this.questions = new ArrayList<Question>();
		this.notes = notes;
		this.teacherName = teacherName;
		this.time = time;
		this.questionGrade = questionGrade;
	}
  
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
		for(Question question : questions) {
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
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public ArrayList<Integer> getQuestionGrade() {
		return questionGrade;
	}
	public void setQuestionGrade(ArrayList<Integer> questionGrade) {
		this.questionGrade = questionGrade;
		
	}
}
