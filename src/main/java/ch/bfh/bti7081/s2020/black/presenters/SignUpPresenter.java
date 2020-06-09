package ch.bfh.bti7081.s2020.black.presenters;


import ch.bfh.bti7081.s2020.black.interfaces.SignUpInterface;
import ch.bfh.bti7081.s2020.black.model.AccountType;
import ch.bfh.bti7081.s2020.black.model.stateModel.SignUp;
import ch.bfh.bti7081.s2020.black.views.SignUpViewImplementation;



public class SignUpPresenter extends Presenter implements SignUpInterface {

    private SignUpViewImplementation signUpViewImplementation;
    private SignUp signUpState;

    public SignUpPresenter(SuperPresenter superPresenter){

        super(superPresenter);
        signUpViewImplementation = new SignUpViewImplementation(this);
        currentView = signUpViewImplementation;
        signUpState = new SignUp();
        superPresenter.setState(signUpState);
        superPresenter.addPage(currentView);
    }

    @Override
    public void submit(String firstName, String lastName, String email, String password, String confirmPassword, String accountType) {
        if (checkInput(firstName, lastName, email, password, confirmPassword)){
            //at least 1 input field is empty

        } else {
            if (accountType == "Relative"){
                signUpState.createAccount(firstName, lastName, email, password, AccountType.RELATIVE);
                signUpViewImplementation.dialogMessage("Hello "+firstName+", your Account has been created. Please Login with your newly created account.  ");
                superPresenter.removePage(currentView);
                new LoginPresenter(superPresenter);
            } else if (accountType == "Patient"){
                signUpState.createAccount(firstName, lastName, email, password, AccountType.PATIENT);
                signUpViewImplementation.dialogMessage("Hello "+firstName+", your Account has been created. Please Login with your newly created account.  ");
                superPresenter.removePage(currentView);
                new LoginPresenter(superPresenter);
            }
        }
    }

    @Override
    public void logIn() {
        //go to loginpage
        superPresenter.removePage(currentView);
        new LoginPresenter(superPresenter);
    }

    private Boolean checkInput(String firstName, String lastName, String email, String password, String confirmPassword){
        if (firstName.isEmpty()){
            signUpViewImplementation.dialogMessage("Please enter a firstname    ");
            return true;
        } else if(lastName.isEmpty()){
            signUpViewImplementation.dialogMessage("Please enter a lastname    ");
            return true;
        } else if (email.isEmpty()){
            signUpViewImplementation.dialogMessage("Please enter an email    ");
            return true;
        } else if (password.isEmpty()){
            signUpViewImplementation.dialogMessage("Please enter a password    ");
            return true;
        } else if (confirmPassword.isEmpty()){
            signUpViewImplementation.dialogMessage("Please repeat the password    ");
            return true;
        } else {
            return false;
        }
    }
}
