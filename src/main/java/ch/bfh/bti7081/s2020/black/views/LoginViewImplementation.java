package ch.bfh.bti7081.s2020.black.views;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.LoginInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.View.DialogInterface;

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

public class LoginViewImplementation<T extends LoginInterface> extends VerticalLayout implements DialogInterface{

    private static final long serialVersionUID = 1L;

    private T presenter;

    private EmailField emailField = new EmailField("Email","example@eamil.com");
    private PasswordField txtPassword = new PasswordField("Password");

    public LoginViewImplementation(T presenter){

        this.presenter = presenter;

        Button btnLogin = new Button("Login");
        FormLayout form = new FormLayout();
        form.setWidth("80%");

        form.addFormItem(emailField, new Icon(VaadinIcon.USER));
        form.addFormItem(txtPassword, new Icon(VaadinIcon.LOCK));
        form.addFormItem(btnLogin, "");
        TextArea loginHint = new TextArea("Test credentials",
                "patient\nusername: patient@mail.com\npassword: 123456\n\n" +
                        "relative\nusername: relative@mail.com\npassword: 123456\n\n" +
                        "admin\nusername: admin@mail.com\npassword: 123456", "");
        loginHint.setReadOnly(true);
        loginHint.setWidth("20%");

        btnLogin.addClickListener(e -> presenter.submit(emailField.getValue(), txtPassword.getValue()));

        HorizontalLayout formLayout = new HorizontalLayout(form);
        formLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        formLayout.setWidth("100%");

        HorizontalLayout hintLayout = new HorizontalLayout(loginHint);
        hintLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        hintLayout.setWidth("100%");

        HorizontalLayout wrapper = new HorizontalLayout(formLayout,loginHint);
        wrapper.setJustifyContentMode(JustifyContentMode.CENTER);

        add(wrapper);
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
