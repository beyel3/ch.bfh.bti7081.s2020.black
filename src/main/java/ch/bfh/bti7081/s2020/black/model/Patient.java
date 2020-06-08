package ch.bfh.bti7081.s2020.black.model;

public class Patient extends Account {

    /*
    Notes: - lvl ?
     */

    private String patientInfo;

	public Patient( String firstName, String lastName, String email, String password) {
		 super(firstName, lastName, email, password, AccountType.PATIENT);
	}
	public Patient(int id, String firstName, String lastName, String email) {
		 super(id, firstName, lastName, email, null, AccountType.PATIENT);
	}

	public String getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(String patientInfo) {
        this.patientInfo = patientInfo;
    }

    @Override
    public String toString() {
        return "Patient: " + getFirstName() + " " + getLastName();
    }
}
