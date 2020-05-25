package ch.bfh.bti7081.s2020.black.views;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.ButtonInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.LoginInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.View.DialogInterface;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import org.apache.commons.codec.digest.DigestUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import ch.bfh.bti7081.s2020.black.persistence.Persistence;

public class LoginViewImplementation<T extends LoginInterface> extends VerticalLayout implements DialogInterface{

    private static final long serialVersionUID = 1L;

    private T presenter;

    private EmailField emailField = new EmailField("Email","example@eamil.com");
    private PasswordField txtPassword = new PasswordField("Password");

    public LoginViewImplementation(T presenter){

        this.presenter = presenter;

        Button btnLogin = new Button("Login");
        FormLayout form = new FormLayout();

        form.addFormItem(emailField, new Icon(VaadinIcon.USER));
        form.addFormItem(txtPassword, new Icon(VaadinIcon.LOCK));
        form.addFormItem(btnLogin, "");

        btnLogin.addClickListener(e -> presenter.submit(emailField.getValue(), txtPassword.getValue()));

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
}
