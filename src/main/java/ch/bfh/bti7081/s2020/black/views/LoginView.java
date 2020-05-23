package ch.bfh.bti7081.s2020.black.views;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.LoginInterface;
import ch.bfh.bti7081.s2020.black.presenters.LoginPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;

//@Route(value = "LoginView", layout = MainView.class)
public class LoginView extends VerticalLayout implements LoginInterface.LoginViewInterface {


    private static final long serialVersionUID = 1L;

    private LoginPresenter loginPresenter;

    private EmailField emailField = new EmailField("Email","example@eamil.com");
    private PasswordField txtPassword = new PasswordField("Password");


    public LoginView(){
        loginPresenter = new LoginPresenter(this);
        Button btnLogin = new Button("Login");
        FormLayout form = new FormLayout();

        form.addFormItem(emailField, new Icon(VaadinIcon.USER));
        form.addFormItem(txtPassword, new Icon(VaadinIcon.LOCK));
        form.addFormItem(btnLogin, "");

        btnLogin.addClickListener(e -> loginPresenter.submit(emailField.getValue(),txtPassword.getValue()));

        VerticalLayout submitLayout = new VerticalLayout(form);
        add(form);
    }


    @Override
    public void dialogMessage(String message) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        Button closeButton = new Button("Close", event ->{
            dialog.close();
        });
        dialog.add(new Label(message));
        dialog.add(closeButton);
        dialog.open();
    }

    @Override
    public void route() {
        this.getUI().ifPresent(ui -> ui.navigate(""));
    }
}
