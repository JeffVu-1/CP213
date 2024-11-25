package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author Jeff Vu
 * @version 2023-09-22
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {
        this.birthDate = birthDate;
        this.forename = forename;
        this.id = id;
        this.surname = surname;
    }
   
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "";
	
	 string += String.format("Name:      %s, %s\n", surname, forename);
	 string += String.format("ID:        %d\n", id);
	 string += String.format("Birthdate: %s\n", birthDate);

	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	    int result = 0;  // Initialize result
	    
	    result = this.surname.compareTo(target.surname);
	    
	    if (result == 0) {
	        result = this.forename.compareTo(target.forename);
	    }

	    if (result == 0) {
	        if (this.id < target.id) {
	            result = -1;
	        } else if (this.id > target.id) {
	            result = 1;
	        }
	    }

	    return result; 
	}


    // getters and setters defined here
    /**
     * Gets the student ID.
     *
     * @return the student ID number
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the student ID.
     *
     * @param id the student ID number to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the student surname.
     *
     * @return the student surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the student surname.
     *
     * @param surname the student surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the student forename.
     *
     * @return the student forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the student forename.
     *
     * @param forename the student forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the student's birth date.
     *
     * @return the student's birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the student's birth date.
     *
     * @param birthDate the student's birth date to set
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
