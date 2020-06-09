package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import org.apache.commons.codec.digest.DigestUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.interfaces.DialogInterface;
import ch.bfh.bti7081.s2020.black.interfaces.SignUpInterface;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;

public class SignUpViewImplementation<T extends SignUpInterface> extends VerticalLayout implements DialogInterface {

    private static final long serialVersionUID = 1L;

    private T presenter;

    private TextField txtFirstName = new TextField("Firstname", "Firstname");
    private TextField txtLastName = new TextField("Lastname", "Lastname");
    private EmailField emailField = new EmailField("e-mail","example@mail.com");
    private PasswordField txtPassword = new PasswordField("Password");
    private PasswordField txtRepeatPassword = new PasswordField("Repeat password");
    private RadioButtonGroup<String> accountType = new RadioButtonGroup<>();


    public SignUpViewImplementation(T presenter){

        this.presenter = presenter;

        Button btnSubmit = new Button("Submit");
        FormLayout form = new FormLayout();
        form.setWidth("80%");
        Image logo = new Image("./icons/ProjectLogoBlueMedium.png", "");
        logo.setWidth("100%");

        accountType.setLabel("Account Type");
        accountType.setItems("Relative", "Patient");
        accountType.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        accountType.setValue("Relative");

        form.addFormItem(txtFirstName,new Icon(VaadinIcon.USER));
        form.addFormItem(txtLastName,new Icon(VaadinIcon.USER));
        form.addFormItem(emailField,new Icon(VaadinIcon.MAILBOX));
        form.addFormItem(txtPassword,new Icon(VaadinIcon.LOCK));
        form.addFormItem(txtRepeatPassword,new Icon(VaadinIcon.LOCK));
        form.add(accountType);
        form.addFormItem(btnSubmit,"");

        Anchor anchor = new Anchor();
        anchor.setText("Login");

        btnSubmit.addClickListener(e -> presenter.submit(txtFirstName.getValue(),
                txtLastName.getValue(),
                emailField.getValue(),
                txtPassword.getValue(),
                txtRepeatPassword.getValue(),
                accountType.getValue()));
        anchor.getElement().addEventListener("click", event -> presenter.logIn());
        form.getElement().addEventListener("keypress", event -> presenter.submit(txtFirstName.getValue(),
                txtLastName.getValue(),
                emailField.getValue(),
                txtPassword.getValue(),
                txtRepeatPassword.getValue(),
                accountType.getValue())).setFilter("event.key == 'Enter'");


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
