package ch.bfh.bti7081.s2020.black.model.stateModel;

import ch.bfh.bti7081.s2020.black.model.AccountType;
import org.apache.commons.codec.digest.DigestUtils;

public class SignUp extends StateModel{

    public SignUp(){}

    public void createAccount(String firstName, String lastName, String email, String password, AccountType accountType){
        String hashedPassword = DigestUtils.sha256Hex(password);
        saveAccountToDB(firstName, lastName, email, hashedPassword, accountType);
    }

    private void saveAccountToDB(String firstName, String lastName, String email, String password, AccountType accountType){
        persistence.executeUpdate("INSERT INTO tbl_account (first_name, last_name, email, password, patientInfo, level, accountType)" +
                " VALUES ('"+firstName+"','"+lastName+"','"+email+"','"+password+"','Ich bin ein "+accountType.name()+"',0,'"+accountType.name()+"');");
    }
}
