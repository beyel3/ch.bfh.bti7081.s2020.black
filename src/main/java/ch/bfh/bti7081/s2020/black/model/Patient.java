package ch.bfh.bti7081.s2020.black.model;

public class Patient extends Account {

    /*
    Notes: - lvl ?
     */

    private String patientInfo;

	public Patient(String firstName, String lastName, String email, String password) {
		 super(firstName, lastName, email, password, AccountType.PATIENT);
	}

	public String getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(String patientInfo) {
        this.patientInfo = patientInfo;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
