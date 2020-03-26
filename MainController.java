package application;

import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class MainController {
	@FXML private TextArea result;
	@FXML private TextField fname, lname, credit, funds;
	@FXML private RadioButton instate, outstate, international;
	@FXML private CheckBox funding, tristate, exchange;
	@FXML private Button add, remove, print;
	
	boolean selectedInstate = false;
	boolean selectedOutstate = false;
	boolean selectedInternational = false;
	
	boolean selectedTristate = false;
	boolean selectedExchange = false;
	StudentList classOf2020 = new StudentList();
	
	public void filter(MouseEvent event) { 
		//if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			if(instate.isSelected()) {
				initialize();
				selectedInstate = true;
				tristate.setDisable(true);
				exchange.setDisable(true);
				
			}

			else if(outstate.isSelected()) {
				initialize();
				selectedOutstate = true;
				funding.setDisable(true);
				funds.setDisable(true);
				exchange.setDisable(true);
				
			}
			else if(international.isSelected()) {
				initialize();
				selectedInternational = true;
				funding.setDisable(true);
				funds.setDisable(true);
				tristate.setDisable(true);
			}
		//}
	}
	
	public void checkEvent(ActionEvent event) {
		selectedTristate = false;
    	selectedExchange = false;
		if(funding.isSelected()) {
			funds.setDisable(false);
		}
		if(funding.isSelected() == false) {
			funds.setDisable(true);
		}
		if(tristate.isSelected()) {
			selectedTristate = true;
		}
		if(exchange.isSelected()) {
			selectedExchange = true;
		}
	}

	/**
	 * reset the view such as buttons and etc.
	 */
    public void initialize() {
        instate.setDisable(false);
        outstate.setDisable(false);
        international.setDisable(false);
        funding.setDisable(false);
        tristate.setDisable(false);
        exchange.setDisable(false);
        funds.setDisable(false);
        
        tristate.setSelected(false);
        exchange.setSelected(false);
        funding.setSelected(false);
        
    	selectedInstate = false;
    	selectedOutstate = false;
    	selectedInternational = false;
    	
    }
    
    /**
     * Check if a given string is alphanumeric
     */
    public boolean isAlphanumeric(String name) {
    	char[] chars = name.toCharArray();
    	for(char c : chars) {
    		if(!Character.isLetter(c)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Check if first and last name are valid
     * Display text if they are not 
     */
    public boolean validName() {
    	//Check if first and last name are NOT NULL
    	if(fname.getText() == null || fname.getText().isEmpty()) {
    		result.setText("Please enter a first name.");
    		return false;
    	}
    	if(lname.getText() == null || lname.getText().isEmpty()) {
    		result.setText("Please enter a last name.");
    		return false;
    	}

    	//Check if first and last name are VALID
    	if(!isAlphanumeric(fname.getText())) {
    		result.setText("Invalid first name for student.");
    		return false;
    	}
    	if(!isAlphanumeric(lname.getText())) {
    		result.setText("Invalid last name for student.");
    		return false;
    	}
    	return true;
    }
    
    /**
     * Check if credit amount is valid
     * Display text if it is not
     */
    public boolean validCredit() {
    	int enteredCredit = 0;
    	try {
			enteredCredit = Integer.parseInt(credit.getText());
		} catch (NumberFormatException e) {
			result.setText("Integer expected for number of credits.");
			return false;
		}
		if (enteredCredit <= 0) {
			result.setText("Positive credit amount expected.");
			return false;
		}
    	return true;
    }
    
    /**
     * Check if funding amount is valid
     * Display text if it is not
     */
    public boolean validFunding() {
    	int fundingAmount = 0;
    	try {
			fundingAmount = Integer.parseInt(funds.getText());
		} catch (NumberFormatException e) {
			result.setText("Integer expected for funding amount.");
			return false;
		}
		if (fundingAmount <= 0) {
			result.setText("Positive funding amount expected.");
			return false;
		}
    	return true;
    }
    
    /**
	 * add different types of student to the StudentList.
	 */
	public void addStudent(ActionEvent event) {
		if(!validName()) {
			return;
		}
		if(!validCredit()) {
			return;
		}
		
		//Case 1: QUESTION what is the purpose of the checkbox for "Funding"?
		//I think if checkbox is not selected we don't look at the funding amount???
		if(selectedInstate == true || instate.isSelected()) {
			//funding.isSelected();
			if(!validFunding()) {
				return;
			}
			Instate inStudent = new Instate(fname.getText(), lname.getText(), Integer.parseInt(credit.getText()), Integer.parseInt(funds.getText()));
			if(classOf2020.contains(inStudent)) {
	        	result.setText("Student is already in the list.");	          
	        }
			else {
				classOf2020.add(inStudent);
				result.setText("Student added to the list.");
			}
		}
		
		if(selectedOutstate == true) {
			Outstate outStudent = new Outstate(fname.getText(), lname.getText(), Integer.parseInt(credit.getText()), selectedTristate);
			if(classOf2020.contains(outStudent)) {
	        	result.setText("Student is already in the list.");
	        }
			else {
				classOf2020.add(outStudent);
				result.setText("Student added to the list.");
			}
		}
		
		if(selectedInternational == true) {
			International intStudent = new International(fname.getText(), lname.getText(), Integer.parseInt(credit.getText()), selectedExchange);
			if(classOf2020.contains(intStudent)) {
	        	result.setText("Student is already in the list.");	          
	        }
			else {
				classOf2020.add(intStudent);
				result.setText("Student added to the list.");
			}
		}else {
			result.setText("No student type selected.");
			return;
		}
		
		initialize();
		instate.setSelected(false);
		outstate.setSelected(false);
		international.setSelected(false);
		
		fname.setText(null);
		lname.setText(null);
		credit.setText(null);
		funds.setText(null);	
		
		classOf2020.print();
	}
	
	
	/**
	 * Remove a Student s from the studentList
	 */
	public void remove(ActionEvent event) {
		
		if(classOf2020.getNumStudents() == 0) {
			result.setText("Empty list of Students.");
		}
		
		else if(selectedInstate == true) {		
			Instate student = new Instate(fname.getText(), lname.getText(), 0, 0);
			if(classOf2020.contains(student)) {
				classOf2020.remove(student);	
				result.setText("Student removed");
			}
		}
		
		else {
        	result.setText("Student not found");
		}
		
		initialize();
		instate.setSelected(false);
		outstate.setSelected(false);
		international.setSelected(false);
		
		fname.setText(null);
		lname.setText(null);
		credit.setText(null);
		funds.setText(null);
		
	}
	
	public void print() {
		int num = classOf2020.getNumStudents();
        if (num == 0) {
            result.setText("There are zero students.");
        }
        result.setText(classOf2020.GUItoString());
        System.out.println(classOf2020.GUItoString());
	}
}