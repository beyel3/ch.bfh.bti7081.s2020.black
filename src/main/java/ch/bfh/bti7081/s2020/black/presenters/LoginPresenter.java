package ch.bfh.bti7081.s2020.black.views;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.LoginInterface;
import ch.bfh.bti7081.s2020.black.views.LoginView;
import ch.bfh.bti7081.s2020.black.views.MainView;
import com.vaadin.flow.router.Route;

@Route("LoginPresenter")
public class LoginPresenter implements LoginInterface.LoginPresenterInterface {

    private LoginInterface.LoginViewInterface loginView;

    public LoginPresenter(){
        this.loginView = new LoginView(this);
    }

}
