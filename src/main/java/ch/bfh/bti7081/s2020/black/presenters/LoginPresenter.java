package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.interfaces.LoginInterface;
import ch.bfh.bti7081.s2020.black.model.stateModel.Login;
import ch.bfh.bti7081.s2020.black.views.LoginViewImplementation;


public class LoginPresenter extends Presenter implements LoginInterface {

    private LoginViewImplementation loginViewImplementation;
    private Login loginState;

    public LoginPresenter(SuperPresenter superPresenter) {

        super(superPresenter);
        loginViewImplementation = new LoginViewImplementation(this);
        currentView = loginViewImplementation;
        loginState = new Login();
        superPresenter.setState(loginState);
        superPresenter.addPage(currentView);
    }

    @Override
    public void submit(String email, String password) {
        if (!inputIsEmpty(email, password)){
            //Input is not empty -> try to log in user
            switch (loginState.login(email, password)){
                case OK:
                    superPresenter.setLoggedInAccount(loginState.getAccout());
                    superPresenter.removePage(currentView);
                    superPresenter.home();
                    break;
                case SQLERROR:
                    loginViewImplementation.dialogMessage("OOPS, something went wrong please try again    ");
                    break;
                case WRONGPASSWORD:
                    loginViewImplementation.dialogMessage("Password did not match user, please trs again    ");
                     break;
                case NOEMAIL:
                    loginViewImplementation.dialogMessage("There is no user with the given email address, please check your inserts    ");
                    break;
            }
        }
    }

    @Override
    public void signUp() {
        //go to signup
        superPresenter.removePage(currentView);
        new SignUpPresenter(superPresenter);
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
