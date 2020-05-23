package ch.bfh.bti7081.s2020.black.views;

import java.sql.ResultSet;
import java.sql.SQLException;

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

@Route(value = "LoginView", layout = MainView.class)
public class LoginViewImplementation extends VerticalLayout{

    private static final long serialVersionUID = 1L;

    private EmailField emailField = new EmailField("Email","example@eamil.com");
    private PasswordField txtPassword = new PasswordField("Password");
    private Label user = new Label("not logged in");

    public LoginViewImplementation(){
    	setSizeFull();
        Button btnLogin = new Button("Login");
        FormLayout form = new FormLayout();

        form.addFormItem(emailField, new Icon(VaadinIcon.USER));
        form.addFormItem(txtPassword, new Icon(VaadinIcon.LOCK));
        form.addFormItem(btnLogin, "");
        form.setResponsiveSteps(new ResponsiveStep("30em", 1));
        form.setWidth("40%");
//        form.getStyle().set("border","1px solid #2f6f91");
        
        TextArea loginHint = new TextArea("Test credentials",
                "patient\nusername: patient\npassword: 123456\n\n" +
                        "relative\nusername: relative\npassword: 123456\n\n" +
                        "admin\nusername: admin\npassword: 123456", "");
        loginHint.setReadOnly(true);
        form.add(loginHint, user);

        btnLogin.addClickListener(e -> {
            if (verifyInput()){
                loadAccount();
            }
            if (VaadinSession.getCurrent().getAttribute("user")==null){
                user.setText("not logged in");
            } else{
                user.setText((String) VaadinSession.getCurrent().getAttribute("user"));
            }
        });
        //form.getElement().addEventListener("keypress", event -> verifyLogin()).setFilter("event.key == 'Enter'");

        HorizontalLayout main = new HorizontalLayout(form);

        main.setSizeFull();
        main.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        main.setAlignItems(Alignment.CENTER);
        add(main);

    }
    private void loadAccount(){
        try {
            Persistence persistence = new Persistence();
            String sha256hex = DigestUtils.sha256Hex(txtPassword.getValue());
            ResultSet resultSet = persistence.executeQuery("SELECT * FROM tbl_accounts WHERE email ='"+emailField.getValue()+"'");
            if (resultSet.getString("password").equals(sha256hex)){
                VaadinSession.getCurrent().setAttribute("user",resultSet.getString("first_name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private boolean verifyInput() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        Button closeButton = new Button("Close", event -> {
            dialog.close();
        });

        if (emailField.isEmpty()) {
            dialog.add(new Label("Please enter an email   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else if (txtPassword.isEmpty()) {
            dialog.add(new Label("Please enter a password   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else {
            return true;
        }
    }

}
