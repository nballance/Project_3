package application;

import javafx.fxml.FXML;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

public class MainController {
	@FXML
	private TextArea result;
	@FXML
	private TextField fname, lname, credit, funds;
	@FXML
	private RadioButton instate, outstate, international;
	@FXML
	private CheckBox funding, tristate, exchange;
	@FXML
	private Button add, remove, print;

	final int FULL_TIME_MIN_CREDITS = 12;
	final int INTERNATIONAL_MIN_CREDITS = 9;
	
	boolean selectedInstate = false;
	boolean selectedOutstate = false;
	boolean selectedInternational = false;

	boolean selectedTristate = false;
	boolean selectedExchange = false;
	StudentList classOf2020 = new StudentList();

	/**
	 * Enables and disables check boxes and buttons based on mouse event 
	 * @param event, mouse event when a radio button is selected 
	 */
	public void filter(MouseEvent event) {

		if (instate.isSelected()) {
			if(!validFirstName() || !validLastName() || !validCredit()) {
				initialize();
				instate.setSelected(false);
				international.setSelected(false);
				outstate.setSelected(false);
				return;
			}
			initialize();
			selectedInstate = true;
			
			if(Integer.parseInt(credit.getText()) >= FULL_TIME_MIN_CREDITS) {
				funding.setDisable(false);
			}
			
			tristate.setDisable(true);
			exchange.setDisable(true);
			
			funds.setDisable(true);
			funds.setText("0");
		}

		else if (outstate.isSelected()) {
			if(!validFirstName() || !validLastName() || !validCredit()) {
				initialize();
				instate.setSelected(false);
				international.setSelected(false);
				outstate.setSelected(false);
				return;
			}
			initialize();
			selectedOutstate = true;
			tristate.setDisable(false);

			funding.setDisable(true);
			funds.setDisable(true);
			funds.setText("");

			exchange.setDisable(true);

		} else if (international.isSelected()) {
			if(!validFirstName() || !validLastName() || !validCredit()) {
				initialize();
				instate.setSelected(false);
				international.setSelected(false);
				outstate.setSelected(false);
				return;
			}
			initialize();
			funds.setText("");
			funding.setDisable(true);
			funds.setDisable(true);
			tristate.setDisable(true);
			if(Integer.parseInt(credit.getText()) < INTERNATIONAL_MIN_CREDITS) {
				result.setText("International student must have 9 or more credits.");
				international.setSelected(false);
				return;
			}
			selectedInternational = true;
			exchange.setDisable(false);
		}
	}
	
	public void checkCredit(ActionEvent event) {
        if(!validCredit()) {
        	initialize();
        	
        	instate.setSelected(false);
        	outstate.setSelected(false);
        	international.setSelected(false);
        	
        	return;
        }
		if(selectedInstate){
            if(Integer.parseInt(credit.getText()) < FULL_TIME_MIN_CREDITS) {
                funding.setDisable(true);
                funding.setSelected(false);
                funds.setDisable(true);
                funds.setText("");
            }
            else {
                funding.setDisable(false);
                funds.setDisable(false);
            }
        }else if(selectedInternational) {
            if(Integer.parseInt(credit.getText()) < INTERNATIONAL_MIN_CREDITS) {
            	result.setText("Please enter credits greater than or equal to 9.");
            	selectedInternational = false;
            	international.setSelected(false);
            	exchange.setSelected(false);
            }
        }

    }

	/**
	 * 
	 * @param event
	 */
	public void checkEvent(ActionEvent event) {
		selectedTristate = false;
		selectedExchange = false;
		if (funding.isSelected()) {
			funds.setDisable(false);
			funds.setText("");
		}
		if (selectedInstate && funding.isSelected() == false) {
			funds.setDisable(true);
			funds.setText("0");
		}
		if (tristate.isSelected()) {
			selectedTristate = true;
		}
		if (exchange.isSelected()) {
			selectedExchange = true;
		}
	}

	/**
	 * reset the view such as buttons and etc.
	 */
	public void initialize() {
		
		
		
		result.setEditable(false);
		result.setMouseTransparent(true);
		result.setFocusTraversable(false);

		instate.setDisable(false);
		outstate.setDisable(false);
		international.setDisable(false);
		funds.setDisable(true);
		funding.setDisable(true);
		tristate.setDisable(true);
		exchange.setDisable(true);

		funds.setText("");
		
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
		for (char c : chars) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the first name is valid Display text if it is not
	 */
	public boolean validFirstName() {
		if (fname.getText() == null || fname.getText().isEmpty()) {
			result.setText("Please enter a first name.");
			return false;
		}
		if (!isAlphanumeric(fname.getText())) {
			result.setText("Invalid first name for student.");
			return false;
		}
		return true;
	}

	/**
	 * Check if the last name is valid Display text if it is not
	 */
	public boolean validLastName() {
		if (lname.getText() == null || lname.getText().isEmpty()) {
			result.setText("Please enter a last name.");
			return false;
		}
		if (!isAlphanumeric(lname.getText())) {
			result.setText("Invalid last name for student.");
			return false;
		}
		return true;
	}

	/**
	 * Check if credit amount is valid Display text if it is not
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
	 * Check if funding amount is valid Display text if it is not
	 */
	public boolean validFunding() {
		int fundingAmount = 0;
		try {
			fundingAmount = Integer.parseInt(funds.getText());
		} catch (NumberFormatException e) {
			result.setText("Integer expected for funding amount.");
			return false;
		}
		if (fundingAmount < 0) {
			result.setText("Positive funding amount expected.");
			return false;
		}
		return true;
	}

	/**
	 * add different types of student to the StudentList.
	 */
	public void addStudent(ActionEvent event) {
		if (!validFirstName()) {
			return;
		}
		if (!validLastName()) {
			return;
		}
		if (!validCredit()) {
			return;
		}

		// Case 1: QUESTION what is the purpose of the checkbox for "Funding"?
		// I think if checkbox is not selected we don't look at the funding amount???
		if (selectedInstate == true) {
			if (funding.isSelected()) {
				if (!validFunding()) {
					return;
				}
			}

			Instate inStudent = new Instate(fname.getText(), lname.getText(), Integer.parseInt(credit.getText()),
					Integer.parseInt(funds.getText()));
			if (classOf2020.contains(inStudent)) {
				result.setText("Student is already in the list.");
			} else {
				classOf2020.add(inStudent);
				result.setText("Student added to the list.");
			}
		}

		else if (selectedOutstate == true) {
			Outstate outStudent = new Outstate(fname.getText(), lname.getText(), Integer.parseInt(credit.getText()),
					selectedTristate);
			if (classOf2020.contains(outStudent)) {
				result.setText("Student is already in the list.");
			} else {
				classOf2020.add(outStudent);
				result.setText("Student added to the list.");
			}
		}

		else if (selectedInternational == true) {
			International intStudent = new International(fname.getText(), lname.getText(),
					Integer.parseInt(credit.getText()), selectedExchange);
			if (classOf2020.contains(intStudent)) {
				result.setText("Student is already in the list.");
			} else {
				classOf2020.add(intStudent);
				result.setText("Student added to the list.");
			}
		} else {
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

	}

	/**
	 * Remove a Student s from the studentList
	 */
	public void remove(ActionEvent event) {
		if (!validFirstName()) {
			return;
		}
		if (!validLastName()) {
			return;
		}

		if (classOf2020.getNumStudents() == 0) {
			result.setText("Empty list of Students.");
			return;
		}

		Instate student = new Instate(fname.getText(), lname.getText(), 0, 0);
		if (classOf2020.contains(student)) {
			classOf2020.remove(student);
			result.setText("Student removed");
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
	}
}

