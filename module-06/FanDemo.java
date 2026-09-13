/**
 * Program Name: FanDemo.java
 * Author:       Kristian Wareing
 * Date:         September 12, 2026
 * Course:       CSD-402 Java Programming
 * Assignment:   Module 6 Programming Assignment
 *
 * Purpose:
 *   Test driver for the Fan class. Creates one Fan with the no-argument
 *   constructor and a second with the argument constructor, prints the
 *   state of each, exercises every setter and getter, and demonstrates that
 *   the validation rules reject invalid input rather than storing it.
 *
 * @author Kristian Wareing
 */

public class FanDemo {

    /**
     * Prints a labeled description of a fan along with the value returned by
     * each getter, so the accessor methods are demonstrated alongside
     * toString rather than duplicated at every call site.
     *
     * @param label a short heading identifying the fan being described
     * @param fan   the fan to describe
     */
    private static void describe(String label, Fan fan) {
        System.out.println(label);
        System.out.println("  toString: " + fan);
        System.out.println("  getSpeed:  " + fan.getSpeed());
        System.out.println("  isOn:      " + fan.isOn());
        System.out.println("  getRadius: " + fan.getRadius());
        System.out.println("  getColor:  " + fan.getColor());
        System.out.println();
    }

    /**
     * Attempts an operation that is expected to fail and reports the caught
     * exception to the console. Keeping the try and catch in one helper
     * avoids repeating the same block for every invalid-input check.
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
     * Runs the demonstration.
     *
     * @param args command line arguments, not used by this program
     */
    public static void main(String[] args) {

        System.out.println("CSD-402 Module 6 - Fan Class");
        System.out.println("Kristian Wareing");
        System.out.println();

        Fan defaultFan = new Fan();
        Fan customFan = new Fan(Fan.FAST, true, 10.5, "brushed nickel");

        describe("Fan one, built with the no-argument constructor:", defaultFan);
        describe("Fan two, built with the argument constructor:", customFan);

        System.out.println("Changing the state of fan one through its setters:");
        defaultFan.setOn(true);
        defaultFan.setSpeed(Fan.MEDIUM);
        defaultFan.setRadius(8.25);
        defaultFan.setColor("black");
        System.out.println("  " + defaultFan);
        System.out.println();

        System.out.println("Switching fan two off:");
        customFan.setOn(false);
        System.out.println("  " + customFan);
        System.out.println();

        System.out.println("Validation checks:");
        expectRejection("Speed of 7", () -> defaultFan.setSpeed(7));
        expectRejection("Speed of -1", () -> defaultFan.setSpeed(-1));
        expectRejection("Radius of 0", () -> defaultFan.setRadius(0));
        expectRejection("Radius of -3.5", () -> defaultFan.setRadius(-3.5));
        expectRejection("Blank color", () -> defaultFan.setColor("   "));
        expectRejection("Null color", () -> defaultFan.setColor(null));
        expectRejection("Invalid constructor argument", () -> new Fan(9, true, 6.0, "red"));
        System.out.println();

        System.out.println("Fan one is unchanged after the rejected assignments:");
        System.out.println("  " + defaultFan);
    }
}
