/**
 * Program Name: Fan.java
 * Author:       Kristian Wareing
 * Date:         September 13, 2026
 * Course:       CSD-402 Java Programming
 * Assignment:   Module 7.2 Programming Assignment
 *
 * Purpose:
 *   Models a fan as a class object. The class defines four named speed
 *   constants, private fields for speed, power state, radius, and color,
 *   accessor and mutator methods for every mutable field, a no-argument
 *   constructor that applies defaults, an argument constructor, and a
 *   toString method that describes the current state of the object.
 *
 * Design notes:
 *   The this reference is used throughout every instance method and
 *   constructor in this class. In the setters it resolves the shadowing
 *   between a parameter and the field it assigns, which is the case where
 *   omitting it would silently assign the parameter to itself. Elsewhere it
 *   is written explicitly to make field access unambiguous at a glance. The
 *   one place it cannot appear is the static constant block, because this
 *   refers to an instance and static members belong to the class.
 *
 *   The no-argument constructor delegates to the argument constructor with
 *   this(...), and the argument constructor assigns through the setters, so
 *   the validation rules live in exactly one place per field.
 *
 * @author Kristian Wareing
 */

public class Fan {

    /** Speed constant indicating the fan is not turning. */
    public static final int STOPPED = 0;

    /** Speed constant indicating the lowest turning speed. */
    public static final int SLOW = 1;

    /** Speed constant indicating the intermediate turning speed. */
    public static final int MEDIUM = 2;

    /** Speed constant indicating the highest turning speed. */
    public static final int FAST = 3;

    /** Default radius applied by the no-argument constructor. */
    private static final double DEFAULT_RADIUS = 6.0;

    /** Default color applied by the no-argument constructor. */
    private static final String DEFAULT_COLOR = "white";

    /** Current speed, always one of the four speed constants. */
    private int speed;

    /** True when the fan is running, false when it is switched off. */
    private boolean on;

    /** Radius of the fan in inches, always greater than zero. */
    private double radius;

    /** Color of the fan. */
    private String color;

    /**
     * Constructs a fan using the default state: stopped, switched off, a
     * radius of 6, and the color white. The call to this(...) forwards to the
     * argument constructor rather than repeating the assignments here.
     */
    public Fan() {
        this(STOPPED, false, DEFAULT_RADIUS, DEFAULT_COLOR);
    }

    /**
     * Constructs a fan from caller-supplied values. Each argument is routed
     * through its setter so the same validation rules apply here as they do
     * after the object exists.
     *
     * @param speed  the starting speed, which must be one of the four constants
     * @param on     true to create the fan in the running state
     * @param radius the radius of the fan, which must be greater than zero
     * @param color  the color of the fan, which cannot be null or blank
     * @throws IllegalArgumentException if any argument falls outside its allowed range
     */
    public Fan(int speed, boolean on, double radius, String color) {
        this.setSpeed(speed);
        this.setOn(on);
        this.setRadius(radius);
        this.setColor(color);
    }

    /**
     * Returns the current speed of the fan.
     *
     * @return the speed, which matches one of the four speed constants
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed of the fan.
     *
     * @param speed the new speed, which must be STOPPED, SLOW, MEDIUM, or FAST
     * @throws IllegalArgumentException if the value is not one of the four constants
     */
    public void setSpeed(int speed) {
        if (speed < STOPPED || speed > FAST) {
            throw new IllegalArgumentException(
                    "Speed must be between " + STOPPED + " and " + FAST + ", but was " + speed + ".");
        }
        this.speed = speed;
    }

    /**
     * Reports whether the fan is currently running.
     *
     * @return true if the fan is on, false if it is off
     */
    public boolean isOn() {
        return this.on;
    }

    /**
     * Switches the fan on or off.
     *
     * @param on true to switch the fan on, false to switch it off
     */
    public void setOn(boolean on) {
        this.on = on;
    }

    /**
     * Returns the radius of the fan.
     *
     * @return the radius in inches
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Sets the radius of the fan.
     *
     * @param radius the new radius, which must be greater than zero
     * @throws IllegalArgumentException if the radius is zero or negative
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(
                    "Radius must be greater than zero, but was " + radius + ".");
        }
        this.radius = radius;
    }

    /**
     * Returns the color of the fan.
     *
     * @return the color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Sets the color of the fan.
     *
     * @param color the new color, which cannot be null or blank
     * @throws IllegalArgumentException if the color is null or contains only whitespace
     */
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or blank.");
        }
        this.color = color.trim();
    }

    /**
     * Converts the numeric speed into the matching constant name so that any
     * caller printing this fan can show a readable speed instead of a bare
     * integer. Exposing it here rather than duplicating the mapping in the
     * display class keeps the labels defined in one place.
     *
     * @return the label for the current speed
     */
    public String getSpeedLabel() {
        switch (this.speed) {
            case SLOW:
                return "SLOW";
            case MEDIUM:
                return "MEDIUM";
            case FAST:
                return "FAST";
            default:
                return "STOPPED";
        }
    }

    /**
     * Describes the current state of the fan. A running fan reports its
     * speed along with its physical properties. A fan that is switched off
     * reports that state instead, since its speed setting is not in effect.
     *
     * @return a description of the fan
     */
    @Override
    public String toString() {
        String properties = "radius " + this.radius + ", color " + this.color;

        if (this.on) {
            return "Fan is on at speed " + this.getSpeedLabel() + ", " + properties + ".";
        }
        return "Fan is off, " + properties + ".";
    }
}
