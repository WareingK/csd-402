/*
 * Kristian Wareing
 * August 23, 2026
 * CSD 402 - Assignment 3.2 - Nested For Loop Pyramid
 *
 * Description: Uses nested for loops to display a seven row pyramid of powers
 * of two. Each row counts up from 1 to 2^(row-1) and then back down to 1, and
 * every row is padded so the trailing @ symbols line up in a single column.
 */

public class Pyramid {

    // Number of rows in the pyramid
    private static final int ROWS = 7;

    public static void main(String[] args) {
        // The widest row is the last one, so it sets the width every other
        // row gets padded out to
        int maxLength = lineLength(ROWS);

        // Outer loop controls the rows
        for (int row = 1; row <= ROWS; row++) {
            int currentLength = lineLength(row);
            int leading = (maxLength - currentLength) / 2;

            // Leading spaces that give the pyramid its shape
            for (int space = 0; space < leading; space++) {
                System.out.print(" ");
            }

            // Ascending half: 1, 2, 4, ... up to 2^(row-1)
            for (int exponent = 0; exponent < row; exponent++) {
                System.out.print(powerOfTwo(exponent));
                System.out.print(" ");
            }

            // Descending half: back down from 2^(row-2) to 1
            for (int exponent = row - 2; exponent >= 0; exponent--) {
                System.out.print(powerOfTwo(exponent));
                System.out.print(" ");
            }

            // Trailing spaces so every @ lands in the same column
            for (int space = 0; space < maxLength - currentLength - leading; space++) {
                System.out.print(" ");
            }

            System.out.println("@");
        }
    }

    /*
     * Returns 2 raised to the given exponent using a loop rather than
     * Math.pow, which keeps the result an int and avoids floating point.
     */
    public static int powerOfTwo(int exponent) {
        int result = 1;

        for (int i = 0; i < exponent; i++) {
            result = result * 2;
        }

        return result;
    }

    /*
     * Returns the printed width of a row, counting the digits in every number
     * plus the single space that follows each one. Used to work out how much
     * padding each row needs.
     */
    public static int lineLength(int row) {
        int length = 0;

        // Ascending half
        for (int exponent = 0; exponent < row; exponent++) {
            length = length + String.valueOf(powerOfTwo(exponent)).length() + 1;
        }

        // Descending half
        for (int exponent = row - 2; exponent >= 0; exponent--) {
            length = length + String.valueOf(powerOfTwo(exponent)).length() + 1;
        }

        return length;
    }
}
