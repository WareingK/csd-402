/*
 * Name:       Kristian Wareing
 * Course:     CSD-402 Java for Programmers
 * Assignment: Module 10.2 Programming Assignment
 * Date:       10/03/2026
 *
 * Purpose:
 *   Concrete subclass of Division for divisions located outside the
 *   country. Adds fields for the country and the language spoken.
 */

public class InternationalDivision extends Division {

    private String country;
    private String language;

    /**
     * Creates an international division. All fields are required.
     *
     * @param divisionName  the name of the division
     * @param accountNumber the division's account number
     * @param country       the country where the division is located
     * @param language      the language spoken at the division
     * @throws IllegalArgumentException if any field is invalid
     */
    public InternationalDivision(String divisionName, int accountNumber,
                                 String country, String language) {
        super(divisionName, accountNumber);
        this.country = requireText(country, "Country");
        this.language = requireText(language, "Language");
    }

    /**
     * Displays the international division's information.
     */
    @Override
    public void display() {
        displayBaseInfo("International Division");
        System.out.println("  Country:        " + country);
        System.out.println("  Language:       " + language);
        System.out.println();
    }
}
