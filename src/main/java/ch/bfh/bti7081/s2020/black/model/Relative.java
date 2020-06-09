package ch.bfh.bti7081.s2020.black.model;

public class Relative extends Account {

    //Constructor for APP
    public Relative(int id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email, null, AccountType.PATIENT);
    }

    //Constructor SignUp
    public Relative(int id, String firstName, String lastName, String email, String password) {
    	 super(id, firstName, lastName, email, password, AccountType.RELATIVE);
	}
    
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
