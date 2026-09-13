/**
 * Program Name: UseFans.java
 * Author:       Kristian Wareing
 * Date:         September 13, 2026
 * Course:       CSD-402 Java Programming
 * Assignment:   Module 7.2 Programming Assignment
 *
 * Purpose:
 *   Builds and works with a collection of Fan instances. The class provides
 *   one method that displays a single Fan and a second that displays an
 *   entire collection of them. Neither display method calls toString on a
 *   Fan; both read the object through its accessor methods and assemble the
 *   output themselves.
 *
 * Design notes:
 *   The collection method delegates to the single-object method rather than
 *   repeating the field-by-field printing, so the display format is defined
 *   once. Every Fan reaching the output is validated first, and any fan that
 *   cannot be displayed is reported to the console without stopping the rest
 *   of the collection from printing.
 *
 * @author Kristian Wareing
 */

import java.util.ArrayList;
import java.util.List;

public class UseFans {

    /** Width reserved for the label column so the output lines up. */
    private static final String FIELD_FORMAT = "    %-8s %s%n";

    /**
     * Displays a single Fan without calling its toString method. Each field is
     * read through its accessor and printed on its own labeled line.
     *
     * @param label a short heading identifying this fan in the output
     * @param fan   the fan to display
     * @throws IllegalArgumentException if the fan reference is null
     */
    public static void displayFan(String label, Fan fan) {
        if (fan == null) {
            throw new IllegalArgumentException("Cannot display a null Fan reference.");
        }

        System.out.println(label);
        System.out.printf(FIELD_FORMAT, "Speed", fan.getSpeed() + " (" + fan.getSpeedLabel() + ")");
        System.out.printf(FIELD_FORMAT, "Power", fan.isOn() ? "on" : "off");
        System.out.printf(FIELD_FORMAT, "Radius", String.valueOf(fan.getRadius()));
        System.out.printf(FIELD_FORMAT, "Color", fan.getColor());
        System.out.println();
    }

    /**
     * Displays every Fan in a collection without calling toString on any of
     * them. The work of printing one fan is delegated to displayFan so the
     * output format exists in a single place. A null entry inside the
     * collection is reported and skipped rather than ending the loop.
     *
     * @param heading a heading printed above the collection
     * @param fans    the collection of fans to display
     * @throws IllegalArgumentException if the collection reference is null
     */
    public static void displayFans(String heading, List<Fan> fans) {
        if (fans == null) {
            throw new IllegalArgumentException("Cannot display a null collection.");
        }

        System.out.println(heading + " (" + fans.size() + " fans)");
        System.out.println();

        int position = 1;
        for (Fan fan : fans) {
            try {
                displayFan("  Fan " + position + ":", fan);
            } catch (IllegalArgumentException error) {
                System.out.println("  Fan " + position + ": skipped - " + error.getMessage());
                System.out.println();
            }
            position++;
        }
    }

    /**
     * Attempts an operation that is expected to fail and reports the caught
     * exception to the console. Keeping the try and catch in one helper avoids
     * repeating the same block for every invalid-input check.
     *
     * @param label       a short description of the value being rejected
     * @param invalidCall the operation expected to throw
     */
    private static void expectRejection(String label, Runnable invalidCall) {
        try {
            invalidCall.run();
            System.out.println("  " + label + " was accepted, which is incorrect.");
        } catch (IllegalArgumentException error) {
            System.out.println("  " + label + " rejected: " + error.getMessage());
        }
    }

    /**
     * Builds a collection of Fan instances and exercises the display methods
     * against it, a single fan on its own, and several invalid inputs.
     *
     * @param args command line arguments, not used by this program
     */
    public static void main(String[] args) {

        System.out.println("CSD-402 Module 7.2 - A Collection of Fan Instances");
        System.out.println("Kristian Wareing");
        System.out.println();

        // Build the collection. The first entry uses the no-argument
        // constructor and the rest use the argument constructor.
        List<Fan> fans = new ArrayList<>();
        fans.add(new Fan());
        fans.add(new Fan(Fan.SLOW, true, 8.0, "black"));
        fans.add(new Fan(Fan.MEDIUM, true, 10.5, "brushed nickel"));
        fans.add(new Fan(Fan.FAST, true, 12.25, "matte bronze"));
        fans.add(new Fan(Fan.FAST, false, 9.75, "oak"));

        displayFans("Collection as built:", fans);

        // Display one member of the collection on its own.
        System.out.println("Single fan display:");
        System.out.println();
        displayFan("  Fan 3 pulled from the collection:", fans.get(2));

        // Change the state of two members through their setters.
        System.out.println("Changing the state of two fans:");
        System.out.println();
        fans.get(0).setOn(true);
        fans.get(0).setSpeed(Fan.MEDIUM);
        fans.get(0).setColor("slate");
        fans.get(3).setOn(false);

        displayFans("Collection after changes:", fans);

        // A null entry is reported and skipped rather than ending the loop.
        fans.add(null);
        displayFans("Collection containing a null entry:", fans);
        fans.remove(fans.size() - 1);

        System.out.println("Validation checks:");
        expectRejection("Speed of 7", () -> fans.get(1).setSpeed(7));
        expectRejection("Radius of -3.5", () -> fans.get(1).setRadius(-3.5));
        expectRejection("Blank color", () -> fans.get(1).setColor("   "));
        expectRejection("Invalid constructor argument", () -> new Fan(9, true, 6.0, "red"));
        expectRejection("Null fan passed to displayFan", () -> displayFan("  Null:", null));
        expectRejection("Null collection passed to displayFans", () -> displayFans("Null:", null));
        System.out.println();

        System.out.println("Fan 2 is unchanged after the rejected assignments:");
        System.out.println();
        displayFan("  Fan 2:", fans.get(1));
    }
}
