/*
 * Name:       Kristian Wareing
 * Course:     CSD-402 Java for Programmers
 * Assignment: Module 10.2 Programming Assignment
 * Date:       10/03/2026
 *
 * Purpose:
 *   Application that creates two InternationalDivision objects and two
 *   DomesticDivision objects, then displays each one. The objects are stored
 *   in a Division array so one loop can call display() on all of them.
 *   It also shows that invalid input is rejected with a clear message.
 */

public class UseDivision {

    /**
     * Creates four divisions and displays their information, then
     * demonstrates the constructor validation with invalid input.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            Division[] divisions = {
                new InternationalDivision("European Sales", 10234, "Germany", "German"),
                new InternationalDivision("Asia Pacific Operations", 10567, "Japan", "Japanese"),
                new DomesticDivision("West Coast Logistics", 20145, "California"),
                new DomesticDivision("Midwest Distribution", 20389, "Nebraska")
            };

            // Each object runs its own version of display()
            for (Division division : divisions) {
                division.display();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Could not create division: " + e.getMessage());
        }

        // Validation demo: these divisions are intentionally invalid
        System.out.println("Validation check:");
        createAndDisplay(() -> new DomesticDivision("Southern Region", -500, "Texas"));
        createAndDisplay(() -> new InternationalDivision("Latin America", 10890, "", "Spanish"));
    }

    /**
     * Tries to create and display a division, printing an error message
     * to the console if the input is invalid.
     *
     * @param creator code that creates the division
     */
    private static void createAndDisplay(java.util.function.Supplier<Division> creator) {
        try {
            creator.get().display();
        } catch (IllegalArgumentException e) {
            System.out.println("  Could not create division: " + e.getMessage());
        }
    }
}
