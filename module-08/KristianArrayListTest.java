/*
 * Name:        Kristian Wareing
 * Course:      CSD-402 Java Programming
 * Assignment:  Module 8.2 - Programming Assignment
 * Date:        September 20, 2026
 *
 * Description: Collects Integer values from user input and stores each one in
 *              an ArrayList<Integer>. Input continues until the user enters 0,
 *              and that 0 is also stored in the ArrayList. The populated list
 *              is then passed to the max() method, which returns the largest
 *              value in the list. If the list is empty, max() returns 0. The
 *              returned value is displayed to the user. A set of automated
 *              tests exercises max() against several list conditions.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class KristianArrayListTest {

    /**
     * Entry point. Gathers user input into an ArrayList, sends the list to
     * max(), and displays the largest value returned.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("=== Integer ArrayList Max ===");
        System.out.println("Enter whole numbers one at a time.");
        System.out.println("Enter 0 when you are finished.");
        System.out.println();

        boolean collecting = true;

        while (collecting && input.hasNextLine()) {
            System.out.print("Enter an integer: ");
            String entry = input.nextLine().trim();

            try {
                int value = Integer.parseInt(entry);
                numbers.add(value);

                // A 0 is stored in the list and also ends input collection.
                if (value == 0) {
                    collecting = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + entry + "\" is not a whole number. Please try again.");
            }
        }

        System.out.println();
        System.out.println("Values stored in the ArrayList: " + numbers);
        System.out.println("The largest value in the ArrayList is: " + max(numbers));
        System.out.println();

        runTests();

        input.close();
    }

    /**
     * Returns the largest value held in the supplied ArrayList.
     *
     * @param list an ArrayList populated with Integer values
     * @return the largest Integer in the list, or 0 if the list is empty
     */
    public static Integer max(ArrayList list) {
        // An empty (or missing) list has no largest value, so return 0.
        if (list == null || list.isEmpty()) {
            return 0;
        }

        // Start with the first element and keep the larger value on each pass.
        Integer largest = (Integer) list.get(0);

        for (int i = 1; i < list.size(); i++) {
            Integer current = (Integer) list.get(i);

            if (current > largest) {
                largest = current;
            }
        }

        return largest;
    }

    /**
     * Runs max() against several list conditions and reports pass or fail for
     * each case so the method can be verified without manual input.
     */
    public static void runTests() {
        System.out.println("=== Testing the max() method ===");

        check("Empty list", new ArrayList<Integer>(), 0);
        check("Single value", buildList(7), 7);
        check("Largest value first", buildList(42, 13, 8, 0), 42);
        check("Largest value last", buildList(3, 19, 56, 0), 56);
        check("Duplicate largest values", buildList(12, 31, 31, 5, 0), 31);
        check("All negative values with terminating 0", buildList(-8, -44, -2, 0), 0);
        check("All negative values, no 0", buildList(-8, -44, -2), -2);

        System.out.println("=== Testing complete ===");
    }

    /**
     * Compares the value returned by max() to the expected value and prints
     * the outcome of the test case.
     *
     * @param label    description of the test case
     * @param list     the ArrayList passed to max()
     * @param expected the value max() should return
     */
    private static void check(String label, ArrayList<Integer> list, Integer expected) {
        Integer actual = max(list);
        String result = actual.equals(expected) ? "PASS" : "FAIL";

        System.out.println(result + " - " + label
                + " | list: " + list
                + " | expected: " + expected
                + " | returned: " + actual);
    }

    /**
     * Builds an ArrayList of Integer values from the supplied numbers.
     *
     * @param values the values to place in the list
     * @return an ArrayList containing the supplied values in order
     */
    private static ArrayList<Integer> buildList(int... values) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int value : values) {
            list.add(value);
        }

        return list;
    }
}
