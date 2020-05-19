package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.Collections;
import java.util.List;

@Route(value = "LoginView", layout = MainView.class)
public class LoginViewImplementation extends VerticalLayout{

    private static final long serialVersionUID = 1L;

    private TextField txtUsername = new TextField("Username", "Username");
    private PasswordField txtPassword = new PasswordField("Password");

    public LoginViewImplementation(){
        Button btnLogin = new Button("Login");
        FormLayout form = new FormLayout();

        form.addFormItem(txtUsername, new Icon(VaadinIcon.USER));
        form.addFormItem(txtPassword, new Icon(VaadinIcon.LOCK));
        form.addFormItem(btnLogin, "");
        TextArea loginHint = new TextArea("Test credentials",
                "student\nusername: pateg\npassword: 123456\n\n" +
                        "professor\nusername: due\npassword: 123456\n\n" +
                        "studAdmin\nusername: sa_mam\npassword: 123456", "");
        loginHint.setReadOnly(true);

        btnLogin.addClickListener(e -> loginHint.setLabel("TRALALA"));
        //btnLogin.addClickListener(e -> verifyLogin());
        //form.getElement().addEventListener("keypress", event -> verifyLogin()).setFilter("event.key == 'Enter'");

        HorizontalLayout main = new HorizontalLayout(form, loginHint);

        main.setHeight("100vh");
        main.setAlignItems(Alignment.CENTER);
        add(main);

        setAlignItems(Alignment.CENTER);
        setHeight("100vh");
    }
    private void verifyLogin() {
        /*
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        Button closeButton = new Button("Close", event -> {
            dialog.close();
        });

        List<User> users = getService().checkIfUserExists(txtUsername.getValue().toLowerCase());

        if (txtUsername.isEmpty()) {
            dialog.add(new Label("Please enter a username   "));
            dialog.add(closeButton);
            dialog.open();
        } else if (txtPassword.isEmpty()) {
            dialog.add(new Label("Please enter a password   "));
            dialog.add(closeButton);
            dialog.open();
        } else if (!users.isEmpty()) {
            User user = users.get(0);
            String encryptedEnteredPassword = DigestUtils.sha256Hex(txtPassword.getValue());
            if (!user.getPassword().equals(encryptedEnteredPassword)) {
                dialog.add(new Label("Incorrect password. Hint: 123456   "));
                dialog.add(closeButton);
                dialog.open();
            } else {
                VaadinSession.getCurrent().setAttribute(User.class, user);
                this.getUI().ifPresent(ui -> ui.navigate(ModulesView.class));
            }
        } else {
            dialog.add(new Label("User not existing   "));
            dialog.add(closeButton);
            dialog.open();
        }

         */
    }

}
