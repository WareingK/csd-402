/*
 * Name:       Kristian Wareing
 * Course:     CSD-402 Java for Programmers
 * Assignment: Module 9.2 Programming Assignment, Program 2
 * Date:       09/27/2026
 *
 * Purpose:
 *   Creates a file named data.file if it does not already exist. Writes
 *   10 randomly generated integers to the new file, or appends 10 more to
 *   the file if it already exists. Each integer is separated by a space.
 *   The file is closed, reopened, read, and its contents are displayed.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WareingDataFile {

    private static final String FILE_NAME = "data.file";
    private static final int NUMBER_COUNT = 10;
    private static final int MAX_VALUE = 100; // random values range from 0 to 99

    /**
     * Runs the program: creates the file if needed, writes or appends the
     * random numbers, then reads the file back and displays it.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        File dataFile = new File(FILE_NAME);

        if (!createFileIfMissing(dataFile)) {
            return; // stop if the file could not be created
        }
        appendRandomNumbers(dataFile, NUMBER_COUNT);
        displayFile(dataFile);
    }

    /**
     * Creates the file if it does not already exist and reports which
     * case occurred.
     *
     * @param file the file to create
     * @return true if the file exists after this call, false if creation failed
     */
    private static boolean createFileIfMissing(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println(file.getName() + " did not exist. New file created.");
            } else {
                System.out.println(file.getName() + " already exists. Appending new numbers.");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Could not create " + file.getName() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Writes random integers to the end of the file, each followed by a
     * space. Opening the FileWriter in append mode covers both the new file
     * and the existing file cases. The writer is closed automatically when
     * the try-with-resources block ends.
     *
     * @param file  the file to write to
     * @param count how many random integers to write
     */
    private static void appendRandomNumbers(File file, int count) {
        Random random = new Random();

        try (FileWriter writer = new FileWriter(file, true)) {
            System.out.print("Numbers written: ");
            for (int i = 0; i < count; i++) {
                int number = random.nextInt(MAX_VALUE);
                writer.write(number + " ");
                System.out.print(number + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error writing to " + file.getName() + ": " + e.getMessage());
        }
    }

    /**
     * Reopens the file, reads its contents, and displays them. The reader
     * is closed automatically when the try-with-resources block ends.
     *
     * @param file the file to read
     */
    private static void displayFile(File file) {
        System.out.println("\nContents of " + file.getName() + ":");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading " + file.getName() + ": " + e.getMessage());
        }
    }
}
