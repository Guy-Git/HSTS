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
@Table(name = "questions")
public class Question implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "questions_id")
	private long id;
	
	@ManyToMany(
			fetch = FetchType.EAGER,
			mappedBy = "questions",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			targetEntity = Exam.class)
	private List<Exam> exams;
	
	private String questionContent;
	private ArrayList<String> answer;
	private int rightAnswer;
	
	private String course;
	private String subject;
	private String questionID;
	
	@Transient
	private static int[] subjectCounter = new int[100];
	
	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public Question(String questionContent, ArrayList<String> answer, int rightAnswer, String course, String newSubject) 
	{
		this.questionContent = questionContent;
		this.answer = answer;
		this.rightAnswer = rightAnswer;
		this.course = course;
		this.subject = newSubject;
		
		int subjectCode = 0;
		
		if(subject.equals("Math"))
		{
			subjectCode = 1;
			subjectCounter[subjectCode]++;
			questionID = "01";
		}
		
		if(subject.equals("CS"))
		{
			subjectCode = 43;
			subjectCounter[subjectCode]++;
			questionID = "43";
		}
		
		if(subject.equals("Biology"))
		{
			subjectCode = 78;
			subjectCounter[subjectCode]++;
			questionID = "78";
		}
		
		if (subjectCounter[subjectCode] < 10) {
			questionID = questionID + "00" + subjectCounter[subjectCode];
		} else if (subjectCounter[subjectCode] < 100) {
			questionID = questionID + "0" + subjectCounter[subjectCode];
		} else {
			questionID = questionID + subjectCounter[subjectCode];
		}
		
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

	public String getSubject() {
		return subject;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionContent=" + questionContent + ", answer=" + answer + ", rightAnswer="
				+ rightAnswer + ", course=" + course + ", subject=" + subject + ", questionID=" + questionID + "]";
	}
	
	
	 
}
