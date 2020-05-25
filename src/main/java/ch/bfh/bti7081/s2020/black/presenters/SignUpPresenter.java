package ch.bfh.bti7081.s2020.black.presenters;


import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.SignUpInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.AccountType;
import ch.bfh.bti7081.s2020.black.views.SignUpViewImplementation;



public class SignUpPresenter extends Presenter implements SignUpInterface {

    private SignUpViewImplementation signUpViewImplementation;

    public SignUpPresenter(SuperPresenter superPresenter){

        super(superPresenter);
        signUpViewImplementation = new SignUpViewImplementation(this);
        currentView = signUpViewImplementation;
        superPresenter.addView(currentView);
    }

    @Override
    public void submit(String firstName, String lastName, String email, String password, String confirmPassword, String accountType) {
        if (inputIsEmpty(firstName, lastName, email, password, confirmPassword)){
            //at least 1 input field is empty

        } else {
            //all fields are filled
            if (accountType == "Relative"){
                Account account = new Account(firstName,lastName,email,password, AccountType.RELATIVE);
                account.createAccount();
            } else if (accountType == "Patient"){
                Account account = new Account(firstName,lastName,email,password, AccountType.PATIENT);
                account.createAccount();
            }
        }
    }

    private Boolean inputIsEmpty(String firstName, String lastName, String email, String password, String confirmPassword){
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
