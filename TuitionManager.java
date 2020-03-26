package application;
/**
 * Class to handle interactions with the user and the I/O
 * 
 * @author Nathan Ballance, Weihong Chen
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitionManager {
	Scanner stdin = new Scanner(System.in);
	StudentList classOf2020;
	private final int ADD_NUM_COMMANDS = 5;
	private final int REMOVE_NUM_COMMANDS = 3;
	private final int ONE_COMMAND = 1;
	private final int DEF_VAL = 0;

	/**
	 * Method which prompts the user to input the operations to invoke on the
	 * StudentList.
	 */
	public void run() {
		classOf2020 = new StudentList();
		boolean done = false;
		while (!done) {
			String input = stdin.nextLine();
			StringTokenizer token = new StringTokenizer(input, " ");
			int numTokens = token.countTokens();
			String tok = token.nextToken();

			char command = tok.charAt(0);
			if (numTokens == ADD_NUM_COMMANDS) {
				String first = token.nextToken();
				String last = token.nextToken();
				int credit = DEF_VAL;
				try {
					credit = Integer.parseInt(token.nextToken());
				} catch (NumberFormatException e) {
					System.err.println("Integer expected in argument 3");
				}
				if (credit <= 0) {
					System.err.println("Positive credit amount expected.");
				} else {

					switch (command) {

					case 'I':
						int funding;
						try {
							funding = Integer.parseInt(token.nextToken());
						} catch (NumberFormatException e) {
							System.err.println("Integer value expected for funding.");
							break;
						}
						Instate inStudent = new Instate(first, last, credit, funding);
						if (funding < 0) {
							System.err.println("Positive funding expected.");
							break;
						}
						add(inStudent);
						break;
					case 'O':
						boolean tri = false;
						boolean validTri = false;
						// Make sure it is length 1
						String triString = token.nextToken();
						if (triString.length() != 1) {
							System.err.println("Invalid String length for boolean.");
							break;
						}
						char tri_val = triString.charAt(0);
						switch (tri_val) {
						case 'F':
							tri = false;
							validTri = true;
							break;
						case 'T':
							tri = true;
							validTri = true;
							break;
						default:
							System.err.println("Invalid tristate type, 'T' or 'F' expected.");
						}
						if (validTri) {
							Outstate outStudent = new Outstate(first, last, credit, tri);
							add(outStudent);
						}
						break;
					case 'N':
						if (credit < 9) {
							System.err.println("Invalid credit amount for International Student.");
							break;
						}
						boolean exch = false;
						boolean validExch = false;
						char exch_val = token.nextToken().charAt(0);
						switch (exch_val) {
						case 'F':
							exch = false;
							validExch = true;
							break;
						case 'T':
							exch = true;
							validExch = true;
							break;
						default:
							System.err.println("Invalid exchange type, 'T' or 'F' expected.");
							break;
						}
						if (validExch) {
							International intStudent = new International(first, last, credit, exch);
							add(intStudent);
						}
						break;
					default:
						System.err.println("Invalid command: " + command + ". Expected 'I', 'O', or 'N'.");
					}
				}
			} else if (numTokens == REMOVE_NUM_COMMANDS) {
				switch (command) {
				case 'R':
					String first = token.nextToken();
					String last = token.nextToken();
					Instate user = new Instate(first, last, 0, 0);
					remove(user);
					break;
				default:
					System.err.println("Invalid command: " + command + " expected 'R'.");
				}
			} else if (numTokens == ONE_COMMAND) {
				if (tok.length() != 1) {
					System.err.println("Input command: " + tok + " is invalid length. String of length 1 expected");
				} else {

					switch (command) {
					case 'P':
						print();
						break;
					case 'Q':
						done = true;
						System.out.println("Program terminated");
						break;
					default:
						System.err.println("Invalid command: " + command + " expected 'P' or 'Q'");
					}
				}
			} else {
				System.err.println("Invalid number of command line inputs.");
			}
		}
	}

	/**
	 * Add a Student s to the studentList
	 */
	private void add(Student s) {
		boolean unique = !classOf2020.contains(s);
        if (!unique) {
            System.err.println("Duplicate student.");
            return;
        }
		classOf2020.add(s);
	}

	/**
	 * Remove a Student s from the studentList
	 */
	private void remove(Student s) {
		if(classOf2020.getNumStudents() == 0) {
			System.err.println("Empty list of Students.");
		}else if(classOf2020.contains(s)) {
			classOf2020.remove(s);					
		}else {
        	System.err.println("Student not found in the list.");
		}		
	}
		
	

	/**
	 * Display the Students in the studentList
	 */
	private void print() {
		classOf2020.print();
	}
}