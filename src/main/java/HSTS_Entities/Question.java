package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "questions")
public class Question implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String questionContent;
	private ArrayList<String> answer;
	private int rightAnswer;
	
	private String course;
	//private static int questionNumber = 0; //3 digits
	//@Column (columnDefinition = "")
	private int subject;
	private String questionID;
	
	@Transient
	private static int[] subjectCounter = new int[100];
	
	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public Question(String questionContent, ArrayList<String> answer, int rightAnswer, String course, int newSubject) 
	{
		this.questionContent = questionContent;
		this.answer = answer;
		this.rightAnswer = rightAnswer;
		this.course = course;
		this.subject = newSubject;
		
		subjectCounter[subject]++;
		if (subject < 10) {
			questionID = "0" + subject;
		} else {
			questionID = Integer.toString(subject);
		}
		if (subjectCounter[subject] < 10) {
			questionID = questionID + "00" + subjectCounter[subject];
		} else if (subjectCounter[subject] < 100) {
			questionID = questionID + "0" + subjectCounter[subject];
		} else {
			questionID = questionID + subjectCounter[subject];
		}
		
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public void setSubject(int subject) {
		this.subject = subject;
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

	public int getSubject() {
		return subject;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionContent=" + questionContent + ", answer=" + answer + ", rightAnswer="
				+ rightAnswer + ", course=" + course + ", subject=" + subject + ", questionID=" + questionID + "]";
	}
	
	
	 
}
