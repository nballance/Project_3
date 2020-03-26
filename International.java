package application;
/**
 * A subclass of Student which represents an International Student Object
 * 
 * @author Nathan Ballance, Weihong Chen
 */

public class International extends Student {
    private boolean exchange;
    private final int INTERNATIONAL_STUDENT_FEE = 350;
    private final int INTERNATIONAL_CREDIT_PRICE = 945;

    /**
     * Constructor for an Outstate Student object
     * 
     * @param fname    the String for the first name of the Student
     * @param lname    the String for the last name of the Student
     * @param credit   the Integer for the number of credits a Student is enrolled
     *                 in
     * @param exchange the Boolean value for whether an Instate Student is an
     *                 exchange student or not
     */
    public International(String fname, String lname, int credit, boolean exchange) {
        super(fname, lname, credit);
        this.exchange = exchange;
    }

    /**
     * Calculates the tuition owed by an international student: Exchange students
     * only pay an international student fee and full time fee
     * 
     * @return int the tuition the student owes
     */
    public int tuitionDue() {
        int tuition;
        tuition = INTERNATIONAL_STUDENT_FEE;
        if (exchange) {
            tuition += UNI_FEE_FULL_TIME;
            return tuition;
        } else {
            int creditsCharged = credit;
            if (credit < FULL_TIME_MIN_CREDITS) {
                tuition += UNI_FEE_PART_TIME;
            } else {
                tuition += UNI_FEE_FULL_TIME;
                if (credit > MAX_CREDITS_CHARGED) {
                    creditsCharged = MAX_CREDITS_CHARGED;
                }
            }
            return tuition += (creditsCharged * INTERNATIONAL_CREDIT_PRICE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	String output = "International Student: " + super.toString() + " is";
    	if(exchange) {
    		return output += " exchange";
    	}else {
    		return output += " NOT exchange";
    	}
    }

    public static void main(String[] args) {
        International fullTimeExchange = new International("FullTime", "Exchange", 15, true);
        International fullTimeNoExchange = new International("FullTime", "NoExchange", 15, false);
        International partTimeExchange = new International("PartTime", "Exchange", 8, true);
        International partTimeNoExchange = new International("PartTime", "NoExchange", 8, false);

        System.out.println(fullTimeExchange.toString() + " Tuition due: " + fullTimeExchange.tuitionDue());
        System.out.println(fullTimeNoExchange.toString() + " Tuition due: " + fullTimeNoExchange.tuitionDue());
        System.out.println(partTimeExchange.toString() + " Tuition due: " + partTimeExchange.tuitionDue());
        System.out.println(partTimeNoExchange.toString() + " Tuition due: " + partTimeNoExchange.tuitionDue());
    }
}