/*
 * Kristian Wareing
 * August 23, 2026
 * CSD 402 - Assignment 2.2 - Rock-Paper-Scissors
 *
 * Description: Simulates a single round of Rock-Paper-Scissors. The program
 * randomly generates a value of 1 (Rock), 2 (Paper), or 3 (Scissors) for the
 * computer, prompts the user to enter a value of 1, 2, or 3, then reports both
 * selections by name along with the outcome of the round.
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    // Constants for the three possible throws, used instead of loose numbers
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Generate the computer's throw: nextInt(3) returns 0-2, so add 1 for 1-3
        int computerChoice = random.nextInt(3) + 1;

        // Prompt the user and reject anything that is not 1, 2, or 3
        int userChoice = 0;
        boolean validEntry = false;

        while (!validEntry) {
            System.out.println("Rock-Paper-Scissors");
            System.out.println("1 = Rock, 2 = Paper, 3 = Scissors");
            System.out.print("Enter your selection (1, 2, or 3): ");

            try {
                userChoice = input.nextInt();

                if (userChoice >= ROCK && userChoice <= SCISSORS) {
                    validEntry = true;
                } else {
                    System.out.println("That is not a valid selection. Please enter 1, 2, or 3.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Numbers only. Please enter 1, 2, or 3.\n");
                input.nextLine(); // clear the bad token out of the Scanner buffer
            }
        }

        // Display both selections by name so the output reads clearly
        System.out.println();
        System.out.println("The computer chose: " + getChoiceName(computerChoice));
        System.out.println("You chose: " + getChoiceName(userChoice));
        System.out.println(determineResult(userChoice, computerChoice));

        input.close();
    }

    /*
     * Converts a numeric selection into its display name.
     */
    public static String getChoiceName(int choice) {
        switch (choice) {
            case ROCK:
                return "Rock";
            case PAPER:
                return "Paper";
            case SCISSORS:
                return "Scissors";
            default:
                return "Unknown";
        }
    }

    /*
     * Compares the two selections and returns a message describing the outcome.
     * A win is only possible in three combinations, so those are checked
     * directly and everything else is a loss.
     */
    public static String determineResult(int userChoice, int computerChoice) {
        if (userChoice == computerChoice) {
            return "It is a tie. Both players chose " + getChoiceName(userChoice) + ".";
        }

        boolean userWins = (userChoice == ROCK && computerChoice == SCISSORS)
                || (userChoice == PAPER && computerChoice == ROCK)
                || (userChoice == SCISSORS && computerChoice == PAPER);

        if (userWins) {
            return "You win. " + getChoiceName(userChoice) + " beats "
                    + getChoiceName(computerChoice) + ".";
        }

        return "You lose. " + getChoiceName(computerChoice) + " beats "
                + getChoiceName(userChoice) + ".";
    }
}
