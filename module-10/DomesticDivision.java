/*
 * Name:       Kristian Wareing
 * Course:     CSD-402 Java for Programmers
 * Assignment: Module 10.2 Programming Assignment
 * Date:       10/03/2026
 *
 * Purpose:
 *   Concrete subclass of Division for divisions located inside the
 *   country. Adds a field for the state.
 */

public class DomesticDivision extends Division {

    private String state;

    /**
     * Creates a domestic division. All fields are required.
     *
     * @param divisionName  the name of the division
     * @param accountNumber the division's account number
     * @param state         the state where the division is located
     * @throws IllegalArgumentException if any field is invalid
     */
    public DomesticDivision(String divisionName, int accountNumber, String state) {
        super(divisionName, accountNumber);
        this.state = requireText(state, "State");
    }

    /**
     * Displays the domestic division's information.
     */
    @Override
    public void display() {
        displayBaseInfo("Domestic Division");
        System.out.println("  State:          " + state);
        System.out.println();
    }
}
