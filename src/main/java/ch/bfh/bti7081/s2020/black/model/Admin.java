package ch.bfh.bti7081.s2020.black.model;

public class Admin extends Account {
    /*
    Notes:  - methods?
     */

    public Admin(int id, String firstName, String lastName, String email){
        super(id, firstName, lastName, email, AccountType.ADMIN);
    }

	public Admin(String firstName, String lastName, String email) {
		 super(firstName, lastName, email, AccountType.ADMIN);
	}
}
