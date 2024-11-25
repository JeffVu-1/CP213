package cp213;

/**
 * Sample string methods.
 *
 * @author Jeff Vu, 169058539, vuxx8539@mylaurier.ca
 * @version 2024-09-16
 */
public class StringMethods {
    // Constants
    /**
     * String of vowels.
     */
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Counts the number of vowels in a string. Does not include 'y'.
     *
     * @param string A string.
     * @return Number of vowels in string.
     */
    public static int vowelCount(final String string) {
	int count = 0;

	for (int i = 0; i < string.length(); i++) {
	    char ch = string.charAt(i);
	    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
		count++;
	    }	
	}
	return count;
    }

    /**
     * Counts the number of digits in a string.
     *
     * @param string A string.
     * @return Number of digits in string.
     */
    public static int digitCount(final String string) {
	int count = 0;

	for (int i = 0; i < string.length(); i++) {
	    char ch = string.charAt(i);
	    if (ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' || ch == '0') {
		count++;
	    }	
	}
	return count;
    }

    /**
     * Totals the individual digits in a string.
     *
     * @param string A string.
     * @return The integer total of all individuals digits in string.
     */
    public static int totalDigits(final String string) {
	int total = 0;
	
	for (int i = 0; i < string.length(); i++){
	    if (Character.isDigit(string.charAt(i))) {
		total += string.charAt(i) - '0';
	    }
	}
	
	return total;
    }

    /**
     * Compares string1 and string2 and returns a comma-delimited concatenated
     * string consisting of the string that is first lexically followed by the
     * string that is second lexically. If the strings are equal, then only string1
     * is returned.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return A concatenation of string1 and string2 in order.
     */
    public static String pairs(String string1, String string2) {
	String line = null;
        int comparison = string1.compareTo(string2);
        if (comparison < 0) {
            line = string1 + "," + string2;
        } else if (comparison > 0) {
            line =  string2 + "," + string1;
        } else {
            line = string1;
        }
        
	return line;
    }

    /**
     * Finds the distance between the s1 and s2. The distance between two strings of
     * the same length is the number of positions in the strings at which their
     * characters are different. If two strings are not the same length, the
     * distance is unknown (-1). If the distance is zero, then two strings are
     * equal.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return The distance between string1 and string2.
     */
    public static int stringDistance(String string1, String string2) {
	int distance = 0;
	if (string1.length() != string2.length()) {
            return -1;  
        }
	
	for (int i = 0; i < string1.length(); i++) {
	   if (string1.charAt(i) != string2.charAt(i)) {
	      distance++;}
	  }
	return distance;
    }
}
