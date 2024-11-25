package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author
 * @version 2022-02-25
 */
public class CAS extends Professor {

    protected String term = null;

    public CAS(final String lastName, final String firstName, final String department, final String term) {
        super(lastName, firstName, department);
        if (isValidTerm(term)) {
            this.term = term;
        } else {
            this.term = "000000";
        }
    }

    private boolean isValidTerm(String term) {
        if (term.length() != 6) return false;
        
        String yearPart = term.substring(0, 4);
        for (char c : yearPart.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        String termPart = term.substring(4);
        return termPart.equals("01") || termPart.equals("05") || termPart.equals("09");
    }

    public String getTerm() {
        return this.term;
    }

    @Override
    public String toString() {
        return (super.toString() + "\nTerm: " + this.term);
    }
}
