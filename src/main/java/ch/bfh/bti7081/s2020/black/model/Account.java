package ch.bfh.bti7081.s2020.black.model;

import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import com.vaadin.flow.component.notification.Notification;
import org.apache.commons.codec.digest.DigestUtils;
//import sun.awt.PeerEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AccountType accountType;

    public Account(int id, String firstName, String lastName, String email,String password, AccountType accountType){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Account) {
			Account account = (Account)obj;
			return account.getId() == this.id;
		} else {
			return false;
		}
	}
    
}
