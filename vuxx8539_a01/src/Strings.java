package cp213;

/**
 * @author Jeff Vu 169058539
 * @version 2024-09-01
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	String cleanedString = string.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
	int length = cleanedString.length();
	for (int i = 0; i < length / 2; i++) {
	        if (cleanedString.charAt(i) != cleanedString.charAt(length - 1 - i)) {
	            return false;
	        }
	    }

	return true;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {
	if (name == null || name.isEmpty() || name.equals("_")) {
	    return false;
	}

	char firstChar = name.charAt(0);
	if (!Character.isLetter(firstChar) && firstChar != '_') {
	    return false;
	}

	for (int i = 1; i < name.length(); i++) {
	    char currentChar = name.charAt(i);
	    if (!Character.isLetter(currentChar) && !Character.isDigit(currentChar) && currentChar != '_') {
		return false;
	    }
	}

	return true;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
	if (word == null || word.isEmpty()) {
	    return word;
	}

	String vowels = "AEIOUaeiou";
	char firstChar = word.charAt(0);
	boolean isFirstCharUpper = Character.isUpperCase(firstChar);

	if (vowels.indexOf(firstChar) != -1) {
	    return word + "way";
	}

	int vowelIndex = -1;
	for (int i = 1; i < word.length(); i++) {
	    if (vowels.indexOf(word.charAt(i)) != -1) {
		vowelIndex = i;
		break;
	    }
	}

	if (vowelIndex == -1) {
	    return word + "ay";
	}

	String consonants = word.substring(0, vowelIndex);
	String restOfWord = word.substring(vowelIndex);
	String pigLatinWord = restOfWord + consonants + "ay";

	if (isFirstCharUpper) {
	    pigLatinWord = Character.toUpperCase(pigLatinWord.charAt(0)) + pigLatinWord.substring(1).toLowerCase();
	} else {
	    pigLatinWord = pigLatinWord.toLowerCase();
	}

	return pigLatinWord;
    }

}
