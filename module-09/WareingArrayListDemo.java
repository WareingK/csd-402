/*
 * Name:       Kristian Wareing
 * Course:     CSD-402 Java for Programmers
 * Assignment: Module 9.2 Programming Assignment, Program 1
 * Date:       09/27/2026
 *
 * Purpose:
 *   Fills an ArrayList with 10 Strings and prints it with a for-each loop.
 *   Asks the user which element they want to see again, then tries to
 *   print that element inside a try/catch block. An invalid index throws
 *   an exception and the program displays "Out of Bounds".
 *
 *   Autoboxing:    the user's String input is parsed to an int primitive
 *                  and assigned to an Integer object (int -> Integer).
 *   Auto-unboxing: that Integer object is passed to list.get(int), which
 *                  expects a primitive, so Java unboxes it (Integer -> int).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WareingArrayListDemo {

    /**
     * Runs the program: builds the list, prints it, and looks up one
     * element chosen by the user.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ArrayList<String> languages = buildList();
        printList(languages);

        // try-with-resources closes the Scanner automatically
        try (Scanner input = new Scanner(System.in)) {
            String userInput = promptForIndex(input, languages.size());
            displayElement(languages, userInput);
        }
    }

    /**
     * Creates the ArrayList and fills it with 10 Strings.
     *
     * @return an ArrayList containing 10 programming language names
     */
    private static ArrayList<String> buildList() {
        return new ArrayList<>(Arrays.asList(
                "Java", "Python", "JavaScript", "C", "C++",
                "C#", "Go", "Rust", "Kotlin", "SQL"));
    }

    /**
     * Prints every element of the list using a for-each loop, with a
     * counter so the user can see each element's index.
     *
     * @param list the list to print
     */
    private static void printList(ArrayList<String> list) {
        System.out.println("ArrayList contents:");
        int position = 0;
        for (String item : list) {
            System.out.println("  [" + position + "] " + item);
            position++;
        }
    }

    /**
     * Asks the user which element they would like to see again.
     *
     * @param input the Scanner reading from the console
     * @param size  the number of elements in the list
     * @return the user's input as a trimmed String
     */
    private static String promptForIndex(Scanner input, int size) {
        System.out.print("\nWhich element would you like to see again? "
                + "Enter an index (0-" + (size - 1) + "): ");
        return input.nextLine().trim();
    }

    /**
     * Converts the user's String input to an index and prints the matching
     * element. Invalid input is caught and reported to the console.
     *
     * @param list      the list to read from
     * @param userInput the index entered by the user, as a String
     */
    private static void displayElement(ArrayList<String> list, String userInput) {
        try {
            // Autoboxing: parseInt returns an int, stored as an Integer object
            Integer index = Integer.parseInt(userInput);

            // Auto-unboxing: get() takes an int, so the Integer is unboxed
            String element = list.get(index);

            System.out.println("Element at index " + index + ": " + element);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("An Exception has been thrown: Out of Bounds");
        } catch (NumberFormatException e) {
            System.out.println("An Exception has been thrown: \"" + userInput
                    + "\" is not a valid whole number.");
        }
    }
}
