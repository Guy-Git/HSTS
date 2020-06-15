package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import HSTS_Entities.Exam;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "question")
public class Question implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private long id;
	
	@ManyToMany(
			fetch = FetchType.EAGER,
			mappedBy = "questions",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Exam.class)
	private List<Exam> exams;
	
	@Column(length = 100000)
	private String questionContent;
	private ArrayList<String> answer;
	private int rightAnswer;
	
	private String course;
	private String questionsSubject;
	private String questionID;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public Question(String questionContent, ArrayList<String> answer, int rightAnswer, String course, String newSubject) 
	{
		this.questionContent = questionContent;
		this.answer = answer;
		this.rightAnswer = rightAnswer;
		this.course = course;
		this.questionsSubject = newSubject;
		
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
		for(Exam exam : exams) {
			exam.getQuestions().add(this);
		}
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public void setSubject(String subject) {
		this.questionsSubject = subject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public ArrayList<String> getAnswer() {
		return answer;
	}

	public void setAnswers(ArrayList<String> answer) {
		this.answer = answer;
	}
	
	public void setAnswer(String answer, int chosenAnswer) {
		this.answer.set(chosenAnswer-1, chosenAnswer + ". " + answer);
	}
	
	public int getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSubject() {
		return questionsSubject;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionContent=" + questionContent + ", answer=" + answer + ", rightAnswer="
				+ rightAnswer + ", course=" + course + ", subject=" + questionsSubject + ", questionID=" + questionID + "]";
	}
	
	
	 
}
