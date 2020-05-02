package HSTS_Server;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String questionContent;
	private ArrayList<String> answer;
	private int rightAnswer;
	
	private String course;
	//private static int questionNumber = 0; //3 digits
	private int [] subject = new int [100];
	
	public Question(String questionContent, ArrayList<String> answer, int rightAnswer, String course, int newSubject) 
	{
		this.questionContent = questionContent;
		this.answer = answer;
		this.rightAnswer = rightAnswer;
		this.course = course;
		this.subject[newSubject]++;
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

	public void setAnswer(ArrayList<String> answer) {
		this.answer = answer;
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

	public int [] getSubject() {
		return subject;
	}
	
	 
}
