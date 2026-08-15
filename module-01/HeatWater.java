/*
 * Kristian Wareing
 * August 15, 2026
 * CSD 402 - Assignment 1
 *
 * Calculates the energy in joules required to heat a mass of water
 * from an initial temperature to a final temperature.
 *
 * Q = waterMass * (finalTemperature - initialTemperature) * 4184
 */

import java.util.Scanner;

public class HeatWater {

    // Specific heat capacity of water in joules per kilogram per degree Celsius
    public static final double SPECIFIC_HEAT = 4184;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the amount of water in kilograms: ");
        double waterMass = input.nextDouble();

        System.out.print("Enter the initial temperature in Celsius: ");
        double initialTemperature = input.nextDouble();

        System.out.print("Enter the final temperature in Celsius: ");
        double finalTemperature = input.nextDouble();

        double energy = waterMass
                * (finalTemperature - initialTemperature)
                * SPECIFIC_HEAT;

        System.out.printf("%nThe energy needed is %.1f joules.%n", energy);

        input.close();
    }
}