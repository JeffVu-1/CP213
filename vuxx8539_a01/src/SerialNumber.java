package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Jeff Vu 169058539
 * @version 2024-09-01
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {
	if (str == null || str.isEmpty()) {
	    return false; 
	}

	for (char c : str.toCharArray()) {
	    if (!Character.isDigit(c)) {
		return false;
	    }}
	return true;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {
	    if (!sn.startsWith("SN/")) {
	        return false;
	    }

	    String remaining = sn.substring(3);
	    String[] parts = remaining.split("-");
	    
	    if (parts.length != 2) {
	        return false;
	    }

	    if (parts[0].length() != 4) {
	        return false;
	    }
	    for (char c : parts[0].toCharArray()) {
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    if (parts[1].length() != 3) {
	        return false;
	    }
	    for (char c : parts[1].toCharArray()) {
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    return true;
	}


    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	while (fileIn.hasNextLine()) {
	    String serialNumber = fileIn.nextLine().trim(); 

	    if (validSn(serialNumber)) {
		goodSns.println(serialNumber); 
	    } else {
		badSns.println(serialNumber); 
	    }
	}

	return;
    }

}
