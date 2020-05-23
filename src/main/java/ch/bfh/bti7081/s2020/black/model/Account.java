package ch.bfh.bti7081.s2020.black.model;

public class Account {

    /*
    Notes:  - friendlist ?
            - account type ? enum or int ?
            - restricted access of the account constructor ??!!!!!
     */

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AccountType accountType;

    public Account(String firstName, String lastName, String email, AccountType accountType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accountType=" + accountType +
                '}';
    }

}
