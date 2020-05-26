package ch.bfh.bti7081.s2020.black.model.stateModel;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.AccountType;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends StateModel{

    private Account account;

    public Login(){}


    public Account getAccout(){
        return account;
    }
    public LoginStatus login(String email, String password){
        ResultSet rs = readAccountsFromDB(email);
        String hashedPassword = DigestUtils.sha256Hex(password);
        try {
            if (!rs.isBeforeFirst()){
                //resultset was empty
                return LoginStatus.NOEMAIL;
            } else {
                while (rs.next()){
                    if (rs.getString("password").equals(hashedPassword)){
                        account = new Account(rs.getInt("accountID"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                rs.getString("password"),
                                AccountType.valueOf(rs.getString("accountTypeID")));
                        return LoginStatus.OK;
                    }
                }
                //password did not match
                return LoginStatus.WRONGPASSWORD;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return LoginStatus.SQLERROR;
        }
    }

    private ResultSet readAccountsFromDB(String email){
        return persistence.executeQuery("SELECT * " +
                "FROM tbl_account " +
                "WHERE email='"+email+"'");
    }
    public enum LoginStatus {
        OK,
        NOEMAIL,
        WRONGPASSWORD,
        SQLERROR
    }
}
