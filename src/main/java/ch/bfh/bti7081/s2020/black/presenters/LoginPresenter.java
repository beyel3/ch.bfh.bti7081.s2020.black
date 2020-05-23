package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.LoginInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.views.LoginView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPresenter implements LoginInterface.LoginPresenterInterface {

    private LoginInterface.LoginViewInterface loginView;

    public LoginPresenter(LoginInterface.LoginViewInterface loginView){
        this.loginView = loginView;
    }


    @Override
    public void submit(String email, String password) {
        if (inputIsEmpty(email, password)){
            //Input is empty message is sent to the view
        } else {
            //Input is not empty -> try to log in user
            Account account = login(email, password);
            if (account != null){
                VaadinSession.getCurrent().setAttribute("user", account);
                loginView.route();
            }
        }
    }

    private boolean inputIsEmpty(String email, String password){
        if (email.isEmpty()){
            loginView.dialogMessage("Please enter an email    ");
            return true;
        } else if (password.isEmpty()){
            loginView.dialogMessage("Please enter a password    ");
            return true;
        } else {
            return false;
        }
    }

    //this method is not following the mvp pattern yet
    //logic regarding the accounts needs to be shifted to the account model
    private Account login(String email, String password){
        try {
            Persistence persistence = new Persistence();
            ResultSet resultSet = persistence.executeQuery("SELECT * FROM tbl_accounts WHERE email ='"+email+"'");
            if (resultSet.next()){
                if (resultSet.getString("password").equals(DigestUtils.sha256Hex(password))){
                    Patient patient = new Patient(resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"));
                    return patient;
                } else {
                    loginView.dialogMessage("password did not match email");
                    return null;
                }
            } else {
              return null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
