package HSTS_Entities;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.persistence.Entity;
import javax.persistence.Table;

import javafx.fxml.Initializable;

@Entity
@Table(name = "executed_exam")
public class ExecutedExam implements Serializable{

	private int id;
	
	ArrayList<StudentsExecutedExam> studentsExecutedExams;
	
	private int numOfStudents;
	
	private int timeAndDate;
	
	private int numForced;
	
	private int numUnforced;
	
	private int timeOfExec;


}
