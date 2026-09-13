/**
 * Program Name: LocateElements.java
 * Author:       Kristian Wareing
 * Date:         September 12, 2026
 * Course:       CSD-402 Java Programming
 * Assignment:   Module 5.2 Programming Assignment
 *
 * Purpose:
 *   Demonstrates method overloading by providing four public static methods
 *   that search a two-dimensional array and report the location of the
 *   largest or smallest element it contains. Two overloads accept a
 *   double[][] and two accept an int[][]. Each method returns a
 *   one-dimensional int array of length two, where index 0 holds the row
 *   index and index 1 holds the column index of the target element.
 *
 * Design notes:
 *   To avoid repeating the same search loop four times, the two int
 *   overloads widen their argument to a double[][] and delegate to the
 *   shared search method. Every int value converts to a double exactly,
 *   because a double mantissa holds 53 bits of precision and an int holds
 *   32, so no value is lost and no index shifts. The largest and smallest
 *   searches then share that single method by way of a direction flag.
 *
 *   All four methods handle ragged arrays, meaning rows of unequal length,
 *   because the column bound is read from each individual row rather than
 *   assumed to be uniform. When several elements tie for the largest or
 *   smallest value, the first occurrence in row-major order is returned.
 *
 * @author Kristian Wareing
 */

import java.util.Arrays;

public class LocateElements {

    /** Direction flag passed to the shared search method to find a maximum. */
    private static final boolean FIND_LARGEST = true;

    /** Direction flag passed to the shared search method to find a minimum. */
    private static final boolean FIND_SMALLEST = false;

    /**
     * Locates the largest element in a two-dimensional array of doubles.
     *
     * @param arrayParam the two-dimensional double array to search
     * @return a two-element int array holding the row index at position 0
     *         and the column index at position 1 of the largest element
     * @throws IllegalArgumentException if the array is null or holds no elements
     */
    public static int[] locateLargest(double[][] arrayParam) {
        return search(arrayParam, FIND_LARGEST);
    }

    /**
     * Locates the largest element in a two-dimensional array of ints.
     *
     * @param arrayParam the two-dimensional int array to search
     * @return a two-element int array holding the row index at position 0
     *         and the column index at position 1 of the largest element
     * @throws IllegalArgumentException if the array is null or holds no elements
     */
    public static int[] locateLargest(int[][] arrayParam) {
        return search(widen(arrayParam), FIND_LARGEST);
    }

    /**
     * Locates the smallest element in a two-dimensional array of doubles.
     *
     * @param arrayParam the two-dimensional double array to search
     * @return a two-element int array holding the row index at position 0
     *         and the column index at position 1 of the smallest element
     * @throws IllegalArgumentException if the array is null or holds no elements
     */
    public static int[] locateSmallest(double[][] arrayParam) {
        return search(arrayParam, FIND_SMALLEST);
    }

    /**
     * Locates the smallest element in a two-dimensional array of ints.
     *
     * @param arrayParam the two-dimensional int array to search
     * @return a two-element int array holding the row index at position 0
     *         and the column index at position 1 of the smallest element
     * @throws IllegalArgumentException if the array is null or holds no elements
     */
    public static int[] locateSmallest(int[][] arrayParam) {
        return search(widen(arrayParam), FIND_SMALLEST);
    }

    /**
     * Performs the single scan that backs all four public overloads. The
     * first element encountered becomes the running baseline, which avoids
     * seeding the comparison with a sentinel constant such as
     * Double.MIN_VALUE. That constant is the smallest positive double rather
     * than the most negative one, so seeding with it would fail on an array
     * of entirely negative values.
     *
     * @param arrayParam  the two-dimensional double array to scan
     * @param findLargest true to locate the maximum, false for the minimum
     * @return a two-element int array holding the row and column indexes
     * @throws IllegalArgumentException if the array is null or holds no elements
     */
    private static int[] search(double[][] arrayParam, boolean findLargest) {
        if (arrayParam == null) {
            throw new IllegalArgumentException("Array reference cannot be null.");
        }

        int[] location = new int[2];
        boolean baselineSet = false;
        double baseline = 0.0;

        for (int row = 0; row < arrayParam.length; row++) {
            for (int column = 0; column < arrayParam[row].length; column++) {
                double current = arrayParam[row][column];
                boolean replaces = findLargest ? current > baseline : current < baseline;

                if (!baselineSet || replaces) {
                    baseline = current;
                    location[0] = row;
                    location[1] = column;
                    baselineSet = true;
                }
            }
        }

        if (!baselineSet) {
            throw new IllegalArgumentException("Array contains no elements to search.");
        }
        return location;
    }

    /**
     * Copies a two-dimensional int array into an equivalent double array so
     * the int overloads can reuse the shared search method. Row lengths are
     * preserved, so a ragged array stays ragged and every index is unchanged.
     *
     * @param arrayParam the two-dimensional int array to widen
     * @return a two-dimensional double array holding the same values
     * @throws IllegalArgumentException if the array is null
     */
    private static double[][] widen(int[][] arrayParam) {
        if (arrayParam == null) {
            throw new IllegalArgumentException("Array reference cannot be null.");
        }

        double[][] widened = new double[arrayParam.length][];
        for (int row = 0; row < arrayParam.length; row++) {
            widened[row] = new double[arrayParam[row].length];
            for (int column = 0; column < arrayParam[row].length; column++) {
                widened[row][column] = arrayParam[row][column];
            }
        }
        return widened;
    }

    /**
     * Prints each row of a two-dimensional double array on its own line.
     *
     * @param label      a short heading printed above the array
     * @param arrayParam the array to display
     */
    private static void display(String label, double[][] arrayParam) {
        System.out.println(label);
        for (double[] row : arrayParam) {
            System.out.println("  " + Arrays.toString(row));
        }
    }

    /**
     * Prints each row of a two-dimensional int array on its own line.
     *
     * @param label      a short heading printed above the array
     * @param arrayParam the array to display
     */
    private static void display(String label, int[][] arrayParam) {
        System.out.println(label);
        for (int[] row : arrayParam) {
            System.out.println("  " + Arrays.toString(row));
        }
    }

    /**
     * Runs both searches against a double array and prints the results.
     *
     * @param arrayParam the array to report on
     */
    private static void report(double[][] arrayParam) {
        int[] largest = locateLargest(arrayParam);
        int[] smallest = locateSmallest(arrayParam);

        System.out.printf("  Largest value  %.2f at row %d, column %d%n",
                arrayParam[largest[0]][largest[1]], largest[0], largest[1]);
        System.out.printf("  Smallest value %.2f at row %d, column %d%n",
                arrayParam[smallest[0]][smallest[1]], smallest[0], smallest[1]);
    }

    /**
     * Runs both searches against an int array and prints the results.
     *
     * @param arrayParam the array to report on
     */
    private static void report(int[][] arrayParam) {
        int[] largest = locateLargest(arrayParam);
        int[] smallest = locateSmallest(arrayParam);

        System.out.printf("  Largest value  %d at row %d, column %d%n",
                arrayParam[largest[0]][largest[1]], largest[0], largest[1]);
        System.out.printf("  Smallest value %d at row %d, column %d%n",
                arrayParam[smallest[0]][smallest[1]], smallest[0], smallest[1]);
    }

    /**
     * Exercises all four overloaded methods against sample data, including a
     * ragged array and two invalid arrays that trigger the guard clauses.
     * Every call is guarded so an invalid argument is reported to the console
     * rather than terminating the program.
     *
     * @param args command line arguments, not used by this program
     */
    public static void main(String[] args) {

        double[][] doubleArray = {
            { 12.5,  3.75, 88.10,  6.20 },
            { -4.90, 51.33, 17.05, 99.99 },
            { 22.00, 8.15, -13.60, 40.45 }
        };

        int[][] intArray = {
            {  14,  7, 63,  9 },
            {  85, 21,  2, 48 },
            { -17, 30, 56, 11 }
        };

        int[][] raggedArray = {
            { 5, 91 },
            { 33 },
            { 8, 1, 77, 64 }
        };

        int[][] emptyArray = new int[0][0];

        System.out.println("CSD-402 Module 5.2 - Locating Elements in a Two-Dimensional Array");
        System.out.println("Kristian Wareing");
        System.out.println();

        try {
            display("Double array:", doubleArray);
            report(doubleArray);
            System.out.println();

            display("Integer array:", intArray);
            report(intArray);
            System.out.println();

            display("Ragged integer array:", raggedArray);
            report(raggedArray);
            System.out.println();
        } catch (IllegalArgumentException error) {
            System.err.println("Search failed: " + error.getMessage());
        }

        System.out.println("Error handling checks:");

        try {
            locateLargest(emptyArray);
            System.out.println("  Empty array was accepted, which is incorrect.");
        } catch (IllegalArgumentException error) {
            System.out.println("  Empty array rejected as expected: " + error.getMessage());
        }

        try {
            locateSmallest((double[][]) null);
            System.out.println("  Null array was accepted, which is incorrect.");
        } catch (IllegalArgumentException error) {
            System.out.println("  Null array rejected as expected: " + error.getMessage());
        }
    }
}
