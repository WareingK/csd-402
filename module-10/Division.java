/*
 * Name:       Kristian Wareing
 * Course:     CSD-402 Java for Programmers
 * Assignment: Module 10.2 Programming Assignment
 * Date:       10/03/2026
 *
 * Purpose:
 *   Abstract superclass for a company division. Stores the division name
 *   and account number shared by every division, validates constructor
 *   input, and declares an abstract display() method that each subclass
 *   must define.
 */

public abstract class Division {

    protected String divisionName;
    protected int accountNumber;

    /**
     * Creates a division. Both fields are required.
     *
     * @param divisionName  the name of the division
     * @param accountNumber the division's account number
     * @throws IllegalArgumentException if the name is blank or the account
     *                                  number is not positive
     */
    public Division(String divisionName, int accountNumber) {
        this.divisionName = requireText(divisionName, "Division name");
        if (accountNumber <= 0) {
            throw new IllegalArgumentException(
                    "Account number must be a positive whole number.");
        }
        this.accountNumber = accountNumber;
    }

    /**
     * Checks that a required text field is not null or blank. Subclasses
     * use this to validate their own fields.
     *
     * @param value     the value to check
     * @param fieldName the field's name, used in the error message
     * @return the value with leading and trailing spaces removed
     * @throws IllegalArgumentException if the value is null or blank
     */
    protected static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return value.trim();
    }

    /**
     * Prints the division type, name, and account number. Both subclasses
     * call this from display() so the shared lines are written only once.
     *
     * @param divisionType the label printed above the division's details
     */
    protected void displayBaseInfo(String divisionType) {
        System.out.println(divisionType);
        System.out.println("  Name:           " + divisionName);
        System.out.println("  Account Number: " + accountNumber);
    }

    /**
     * Displays the division's information. Each subclass defines what
     * gets displayed.
     */
    public abstract void display();
}
