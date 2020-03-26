package application;
/**
 * Class to define a Student object
 * 
 * @author Nathan Ballance, Weihong Chen
 */

public abstract class Student implements Comparable<Object> {
    private String fname;
    private String lname;
    protected int credit;

    protected final int MAX_CREDITS_CHARGED = 15;
    protected final int FULL_TIME_MIN_CREDITS = 12;
    protected final int UNI_FEE_PART_TIME = 846;
    protected final int UNI_FEE_FULL_TIME = 1441;

    /**
     * Constructor for a Student object
     * 
     * @param fname  the String for the first name of the Student
     * @param lname  the String for the last name of the Student
     * @param credit the Integer for the number of credits the Student is enrolled
     *               in
     */
    public Student(String fname, String lname, int credit) {
        this.fname = fname;
        this.lname = lname;
        this.credit = credit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Object obj) {
        int compRes = this.fname.compareToIgnoreCase(((Student) obj).fname);
        if (compRes == 0) {
            return this.lname.compareToIgnoreCase(((Student) obj).lname);
        } else {
            return compRes;
        }
    }

    /**
     * @return String representation of the Student object with first and last name
     *         followed by credits
     */
    public String toString() {
        return fname + " " + lname + " has " + credit + " credits";
    }

    public abstract int tuitionDue();
}