package ch.bfh.bti7081.s2020.black.model;

public class Patient extends Account implements Coreuser{

    /*
    Notes: - lvl ?
     */

    private String patientInfo;

    public Patient(String firstName, String lastName, String email){
        super(firstName, lastName, email, AccountType.PATIENT);
    }

    public String getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(String patientInfo) {
        this.patientInfo = patientInfo;
    }

    @Override
    public String toString() {
        //super toString
        return "Patient{" +
                "patientInfo='" + patientInfo + '\'' +
                '}';
    }
}
