package ch.bfh.bti7081.s2020.black.views;

import org.apache.commons.codec.digest.DigestUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.persistence.Persistence;

//@Route(value = "SignUpView", layout = MainView.class)
public class SignUpViewImplementation extends VerticalLayout{

    private static final long serialVersionUID = 1L;

    private TextField txtFirstName = new TextField("Firstname", "Firstname");
    private TextField txtLastName = new TextField("Lastname", "Lastname");
    private EmailField emailField = new EmailField("e-mail","example@mail.com");
    private PasswordField txtPassword = new PasswordField("Password");
    private PasswordField txtRepeatPassword = new PasswordField("Repeat password");

    public SignUpViewImplementation(){
        Button btnSubmit = new Button("Submit");
        FormLayout form = new FormLayout();

        form.addFormItem(txtFirstName,new Icon(VaadinIcon.USER));
        form.addFormItem(txtLastName,new Icon(VaadinIcon.USER));
        form.addFormItem(emailField,new Icon(VaadinIcon.MAILBOX));
        form.addFormItem(txtPassword,new Icon(VaadinIcon.LOCK));
        form.addFormItem(txtRepeatPassword,new Icon(VaadinIcon.LOCK));
        form.addFormItem(btnSubmit,"");

        VerticalLayout submitLayout = new VerticalLayout(form);

        Label test = new Label("test");

        btnSubmit.addClickListener(e ->{
                if (verifyInput()){
                    saveAccount();
                }
            }
        );

        add(submitLayout);
    }
    private void saveAccount(){
        try {
            Persistence persistence = new Persistence();
            String sha256hex = DigestUtils.sha256Hex(txtPassword.getValue());
            persistence.executeUpdate("INSERT INTO tbl_accounts (first_name, last_name, email, password, accountTypeID) VALUES ('"+txtFirstName.getValue()+"','"+txtLastName.getValue()+"','"+emailField.getValue()+"','"+sha256hex+"',1);");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void checkDBCollision(){

    }
    private boolean verifyInput(){
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        Button closeButton = new Button("Close", event -> {
            dialog.close();
        });

        if (txtFirstName.isEmpty()){
            dialog.add(new Label("Please enter a firstname   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else if (txtLastName.isEmpty()){
            dialog.add(new Label("Please enter a lastname   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else if (emailField.isEmpty()){
            dialog.add(new Label("Please enter an email   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else if (txtPassword.isEmpty()){
            dialog.add(new Label("Please enter a password   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else if (txtRepeatPassword.isEmpty()){
            dialog.add(new Label("Please confirm your password   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else if (!txtPassword.getValue().equals(txtRepeatPassword.getValue())){
            dialog.add(new Label("Please enter matching passwords   "));
            dialog.add(closeButton);
            dialog.open();
            return false;
        } else {
            return true;
        }
    }
}
