package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import ch.bfh.bti7081.s2020.black.interfaces.DialogInterface;
import ch.bfh.bti7081.s2020.black.interfaces.LoginInterface;

public class LoginViewImplementation<T extends LoginInterface> extends VerticalLayout implements DialogInterface{

    private static final long serialVersionUID = 1L;

    private T presenter;

    private EmailField emailField = new EmailField("Email","example@email.com");
    private PasswordField txtPassword = new PasswordField("Password");
    

    public LoginViewImplementation(T presenter){

        this.presenter = presenter;
        setSizeFull();
        Button btnLogin = new Button("Login");
        FormLayout form = new FormLayout();
        form.setWidth("80%");
        Image logo = new Image("./icons/ProjectLogoBlueMedium.png", "");
        logo.setWidth("100%");
        form.addFormItem(emailField, new Icon(VaadinIcon.USER));
        form.addFormItem(txtPassword, new Icon(VaadinIcon.LOCK));
        form.addFormItem(btnLogin, "");

        Anchor anchor = new Anchor();
        anchor.setText("Sign Up");

        form.getElement().addEventListener("keypress", event -> presenter.submit(emailField.getValue(), txtPassword.getValue())).setFilter("event.key == 'Enter'");
        btnLogin.addClickListener(e -> presenter.submit(emailField.getValue(), txtPassword.getValue()));
        anchor.getElement().addEventListener("click", event -> presenter.signUp());

        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setWidth("25%");
        formLayout.add(logo,form);
        formLayout.getStyle().set("align-items", "center");
        formLayout.getStyle().set("border", "2px solid #2f6f91");
        getStyle().set("align-items", "center");

        add(formLayout, anchor);
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
