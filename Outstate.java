package application;
/**
 * A subclass of Student which represents an out of state student Object
 * 
 * @author Nathan Ballance, Weihong Chen
 */

public class Outstate extends Student {
    private boolean tristate;
    private final int TRISTATE_DISCOUNT = 200;
    private final int OUTSTATE_CREDIT_PRICE = 756;

    /**
     * Constructor for an Outstate Student object
     * 
     * @param fname    the String for the first name of the Student
     * @param lname    the String for the last name of the Student
     * @param credit   the Integer for the number of credits a Student is enrolled
     *                 in
     * @param tristate the Boolean value for whether an Outstate Student is in the
     *                 tristate area
     */
    public Outstate(String fname, String lname, int credit, boolean tristate) {
        super(fname, lname, credit);
        this.tristate = tristate;
    }

    /**
     * Calculates the tuition owed by an outstate student: if a student is both full
     * time and tristate a discount is issued
     * 
     * @return int the tuition the student owes
     */
    public int tuitionDue() {
        int tuition;
        int ppCredit = OUTSTATE_CREDIT_PRICE;
        boolean fullTimeStudent;
        int creditsCharged = credit;
        if (credit < FULL_TIME_MIN_CREDITS) {
            tuition = UNI_FEE_PART_TIME;
            fullTimeStudent = false;
        } else {
            tuition = UNI_FEE_FULL_TIME;
            if (credit > MAX_CREDITS_CHARGED) {
                creditsCharged = MAX_CREDITS_CHARGED;
            }
            fullTimeStudent = true;
        }
        if (tristate && fullTimeStudent) {
            ppCredit -= TRISTATE_DISCOUNT;
        }
        return tuition += (creditsCharged * ppCredit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	String output = "Outstate Student: " + super.toString() + " is";
    	if(tristate) {
    		return output += " tristate";
    	}else {
    		return output += " NOT tristate";
    	}
    }

    public static void main(String[] args) {
        Outstate fullTimeTristate = new Outstate("FullTime", "Tristate", 15, true);
        Outstate fullTimeNoTristate = new Outstate("FullTime", "NoTristate", 15, false);
        Outstate partTimeTristate = new Outstate("PartTime", "Tristate", 8, true);
        Outstate partTimeNoTristate = new Outstate("PartTime", "NoExchange", 8, false);

        System.out.println(fullTimeTristate.toString() + " Tuition due: " + fullTimeTristate.tuitionDue());
        System.out.println(fullTimeNoTristate.toString() + " Tuition due: " + fullTimeNoTristate.tuitionDue());
        System.out.println(partTimeTristate.toString() + " Tuition due: " + partTimeTristate.tuitionDue());
        System.out.println(partTimeNoTristate.toString() + " Tuition due: " + partTimeNoTristate.tuitionDue());
    }
}