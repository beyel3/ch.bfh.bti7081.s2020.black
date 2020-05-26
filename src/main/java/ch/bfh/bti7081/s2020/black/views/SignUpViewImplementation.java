package ch.bfh.bti7081.s2020.black.views;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.SignUpInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.View.DialogInterface;
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

        form.getStyle().set("border","1px solid black");
        form.setResponsiveSteps(new ResponsiveStep("30em", 1));
        form.setWidth("40%");

        btnSubmit.addClickListener(e -> presenter.submit(txtFirstName.getValue(),
                txtLastName.getValue(),
                emailField.getValue(),
                txtPassword.getValue(),
                txtRepeatPassword.getValue(),
                accountType.getValue()));

        
        HorizontalLayout submitLayout = new HorizontalLayout(form);
        submitLayout.setSizeFull();
        submitLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(submitLayout);
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
