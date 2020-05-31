package HSTS_Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Exam implements Serializable
{
	private long id;
	public Exam(long id, ArrayList<Question> questions, String notes) {
		super();
		this.id = id;
		this.questions = questions;
		this.notes = notes;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	private ArrayList<Question> questions;
	private String notes;
	
}
