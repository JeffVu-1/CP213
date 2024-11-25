package cp213;

import java.util.Scanner;

/**
 * Class to demonstrate the use of Scanner with keyboard and file objects.
 * 
 * 
 * @author Jeff Vu
 * @version 2024-15-09
 */
public class ScannerTest {
    
    /**
     * Default constructor for the ScannerTest class.
     * Initializes a new instance of the class.
     * Added this to remove the warning...
     */
    public ScannerTest() {
        // Default constructor
    }

    /**
     * Counts the number of lines in the scanned input source.
     *
     * 
     * @param source the Scanner object to process	
     * @return the number of lines in the scanned input.
     */
    public static int countLines(final Scanner source) {
        int count = 0;
        while (source.hasNextLine()) {
            source.nextLine();
            count++;
        }
        return count;
    }

    /**
     * Counts the number of tokens in the scanned input source.
     *
     * 
     * @param source the Scanner object to process
     * @return the number of tokens in the scanned input
     */
    public static int countTokens(final Scanner source) {
        int tokens = 0;
        while (source.hasNext()) {
            source.next();
            tokens++;
        }
        return tokens;
    }

    /**
     * Reads and totals positive integers from the keyboard.
     *
     * 
     * @param keyboard the Scanner object associated with keyboard input
     * @return the total sum of positive integers entered
     */
    public static int readNumbers(final Scanner keyboard) {
        int total = 0;

        while (true) {
            if (keyboard.hasNextInt()) {
                int num = keyboard.nextInt();
                if (num > 0) {
                    total += num;
                }
            } else {
                String input = keyboard.next();
                if (input.equalsIgnoreCase("q")) {
                    break;
                } else {
                    System.out.println("'" + input + "' is not an integer or 'q'.");
                }
            }
        }

        return total;
    }
}
