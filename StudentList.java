package application;
/**
 * An array-based growable list of Student objects
 * 
 * @author Nathan Ballance, Weihong Chen
 */

public class StudentList {
	private final int GROW_SIZE = 4;
	private final int NOT_FOUND = -1;
	private int numStudents;
	private Student[] studentList;

	/**
	 * Constructor for the StudentList
	 */
	public StudentList() {
		studentList = new Student[GROW_SIZE];
		numStudents = 0;
	}

	/**
	 * Search the student list for a given Student object, s
	 * 
	 * @return boolean true if the Student s is contained in the StudentList, false
	 *         otherwise
	 */
	public boolean contains(Student s) {
		for (int i = 0; i < numStudents; i++) {
			if (studentList[i].compareTo(s) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to return the number of students in the student list.
	 * 
	 * @return int the number of students
	 */
	public int getNumStudents() {
		return numStudents;
	}

	/**
	 * Insert a Student s into the StudentList if they are unique
	 * 
	 * @param s Student to be added to the StudentList
	 */
	public void add(Student s) {
		if ((numStudents + 1) <= studentList.length) {
			studentList[numStudents] = s;
		} else {
			this.grow();
			studentList[numStudents] = s;
		}
		numStudents++;
	}

	/**
	 * Search through the StudentList for a given Student s
	 * 
	 * @return int index in StudentList array where student s is located, -1 if not
	 *         found
	 */
	private int find(Student s) {
		for (int i = 0; i < numStudents; i++) {
			if (studentList[i].compareTo(s) == 0) {
				return i;
			}
		}
		return NOT_FOUND;
	}

	/**
	 * Remove a Student from the StudentList
	 * 
	 * @param s Student to be removed from the StudentList
	 * @return boolean true if the Student s has been successfully removed, false
	 *         otherwise
	 */
	public boolean remove(Student s) {
		boolean found = this.contains(s);
		if (found) {
			int index = this.find(s);
			studentList[index] = studentList[numStudents - 1];
			studentList[numStudents - 1] = null;
			numStudents--;
		}
		return found;
	}

	/**
	 * Grow the size of the StudentList array by a factor of GROW_SIZE
	 */
	private void grow() {
		int newSize = studentList.length + GROW_SIZE;
		Student[] newStudentList = new Student[newSize];
		System.arraycopy(studentList, 0, newStudentList, 0, studentList.length);

		studentList = newStudentList;
	}

	/**
	 * Display the list of Students in the studentList array
	 */
	public void print() {
		if (numStudents == 0) {
			System.err.println("There are zero students.");
		}
		for (int i = 0; i < numStudents; i++) {
			System.out.println(studentList[i].toString() + " tuition due: $" + studentList[i].tuitionDue());
		}
	}

	public String GUItoString() {
		String statement = ""; 
		if (getNumStudents() == 0) {
			return "There are zero students.";
		}
		for (int i = 0; i < numStudents; i++) {
			statement += studentList[i].toString() + " tuition due: $" + studentList[i].tuitionDue() + "\n";
		}        
		return statement;
	}
}