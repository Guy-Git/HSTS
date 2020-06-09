package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "exams")
public class Exam implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exams_id")
	private long id;

	@ManyToMany(fetch = FetchType.EAGER, 
			cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
			targetEntity = Question.class)
	@JoinTable(name = "exams_questions", joinColumns = @JoinColumn(name = "exams_id"), inverseJoinColumns = @JoinColumn(name = "questions_id"))
	private List<Question> questions;

	private String examID;
	
	@Column(length = 100000)
	private String instructions;
	
	@Column(length = 100000)
	private String notes;
	
	private String teacherName;
	private int time;
	private boolean manual;
	private ArrayList<Integer> questionGrade;
	String subject;
	String course;

	@Transient
	private static int[] subjectCounter = new int[100];

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(List<Question> questions, String instructions, String notes, String teacherName, int time,
			ArrayList<Integer> questionGrade, String subject, String course) {
		super();
		this.questions = new ArrayList<Question>();
		this.instructions = instructions;
		this.notes = notes;
		this.teacherName = teacherName;
		this.time = time;
		this.questionGrade = questionGrade;
		this.subject = subject;
		this.course = course;

		// Dealing with exam ID:
		int subjectCode = 0;

		if (subject.equals("Math")) {
			subjectCode = 1;
			subjectCounter[subjectCode]++;
			examID = "01";

			if (course.equals("Calculus")) {
				examID += "22";
			}

			if (course.equals("Algebra 101")) {
				examID += "13";
			}

			if (course.equals("Introduction to Probability")) {
				examID += "10";
			}

		}

		if (subject.equals("CS")) {
			subjectCode = 43;
			subjectCounter[subjectCode]++;
			examID = "43";

			if (course.equals("Introduction to CS")) {
				examID += "19";
			}

			if (course.equals("Data structures")) {
				examID += "65";
			}

			if (course.equals("OS")) {
				examID += "03";
			}
		}

		if (subject.equals("Biology")) {
			subjectCode = 78;
			subjectCounter[subjectCode]++;
			examID = "78";

			if (course.equals("Anatomy")) {
				examID += "72";
			}

			if (course.equals("Stem Cells")) {
				examID += "42";
			}

			if (course.equals("Biostructure")) {
				examID += "16";
			}
		}
		
		if (subjectCounter[subjectCode] < 10) {
			examID += "0" + subjectCounter[subjectCode];
		} else {
			examID += subjectCounter[subjectCode];
		}

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

	public boolean isManual() {
		return manual;
	}

	public void setManual(boolean manual) {
		this.manual = manual;
	}
	
	

}
