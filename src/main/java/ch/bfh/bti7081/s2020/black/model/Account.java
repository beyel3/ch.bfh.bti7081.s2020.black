package ch.bfh.bti7081.s2020.black.model;

import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import com.vaadin.flow.component.notification.Notification;
import org.apache.commons.codec.digest.DigestUtils;
import sun.awt.PeerEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AccountType accountType;
    
    public Account(String firstName, String lastName, String email,String password, AccountType accountType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }
    public Account(int id, String firstName, String lastName, String email,String password, AccountType accountType){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    /*
    public Account(String email, String password){
        hashedPassword = DigestUtils.sha256Hex(password);
        ResultSet rs = readAccount(email);
        try {
            if (!rs.isBeforeFirst() ) {
                Notification.show("Resultset was empty");
            } else {
                while (rs.next()){
                    if (rs.getString("password").equals(hashedPassword)){
                        this.id = rs.getInt("accountID");
                        this.firstName = rs.getString("first_name");
                        this.lastName = rs.getString("last_name");
                        this.email = rs.getString("email");
                        this.password = rs.getString("password");
                        this.accountType = AccountType.valueOf(rs.getString("accountTypeID"));
                        return;
                    } else {
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

     */

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
    /*
    //CRUD OPERATIONS -> DB
    public void createAccount(){
        String query = "INSERT INTO tbl_account (first_name, last_name, email, password, accountTypeID) VALUES ('"+this.firstName+"','"+this.lastName+"','"+this.email+"','"+this.hashedPassword+"','"+this.accountType.name()+"');";
        try {
            Persistence persistence = new Persistence();
            persistence.executeQuery(query);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

    }

    private ResultSet readAccount(String email){
        String query = "SELECT * FROM tbl_account WHERE email='"+email+"'";
        try {
            Persistence persistence = new Persistence();
            return persistence.executeQuery(query);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    //update and delete are admin actions
    public void updateAccount(){

    }

    public void deleteAccount(){

    }

     */
}
