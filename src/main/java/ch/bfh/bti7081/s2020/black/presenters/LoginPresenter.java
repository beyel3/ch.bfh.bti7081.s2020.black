package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.LoginInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.views.LoginViewImplementation;
import com.vaadin.flow.component.notification.Notification;


public class LoginPresenter extends Presenter implements LoginInterface {

    private LoginViewImplementation loginViewImplementation;

    public LoginPresenter(SuperPresenter superPresenter) {

        super(superPresenter);
        loginViewImplementation = new LoginViewImplementation(this);
        currentView = loginViewImplementation;
        superPresenter.addView(currentView);
    }

    @Override
    public void submit(String email, String password) {
        if (inputIsEmpty(email, password)){
            //Input is empty message is sent to the view
        } else {
            //Input is not empty -> try to log in user
            Account account = new Account(email, password);
            Notification.show(account.toString());
        }
    }

    private boolean inputIsEmpty(String email, String password){
        if (email.isEmpty()){
            loginViewImplementation.dialogMessage("Please enter an email    ");
            return true;
        } else if (password.isEmpty()){
            loginViewImplementation.dialogMessage("Please enter a password    ");
            return true;
        } else {
            return false;
        }
    }
}
