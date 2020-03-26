package application;
/**
 * A subclass of Student which represents an Instate Student object
 * 
 * @author Nathan Ballance, Weihong Chen
 */

public class Instate extends Student {
   private int funds;
   private final int INSTATE_CREDIT_PRICE = 433;

   /**
    * Constructor for an Instate Student object
    *
    * @param fname  the String for the first name of the Student
    * @param lname  the String for the last name of the Student
    * @param credit the Integer for the number of credits a Student is enrolled in
    * @param funds  the Integer for the amount of funds awarded to a Full-Time
    *               Student
    */
   public Instate(String fname, String lname, int credit, int funds) {
      super(fname, lname, credit);
      this.funds = funds;
   }

   /**
    * Calculates the tuition owed by an instate student: if a student is both full
    * time and has funds their tuition will be reduced by the fund amount
    * 
    * @return int the tuition the student owes
    */
   public int tuitionDue() {
      int tuition;
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
      if (fullTimeStudent) {
         tuition -= funds;
      }
      return tuition += (creditsCharged * INSTATE_CREDIT_PRICE);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String toString() {
	   String output = "Instate Student: " + super.toString() + " has $" + funds + " funds";
	   return output;
   }

   public static void main(String[] args) {
      Instate fullTimeNoFunds = new Instate("FullTime", "NoFunds", 15, 0);
      Instate fullTimeWithFunds = new Instate("FullTime", "WithFunds", 15, 1000);
      Instate partTimeNoFunds = new Instate("PartTime", "NoFunds", 8, 0);
      Instate partTimeWithFunds = new Instate("PartTime", "WithFunds", 8, 1000);

      System.out.println(fullTimeNoFunds.toString() + " Tuition due: " + fullTimeNoFunds.tuitionDue());
      System.out.println(fullTimeWithFunds.toString() + " Tuition due: " + fullTimeWithFunds.tuitionDue());
      System.out.println(partTimeNoFunds.toString() + " Tuition due: " + partTimeNoFunds.tuitionDue());
      System.out.println(partTimeWithFunds.toString() + " Tuition due: " + partTimeWithFunds.tuitionDue());

   }
}
