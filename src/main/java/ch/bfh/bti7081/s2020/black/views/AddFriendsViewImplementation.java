package ch.bfh.bti7081.s2020.black.views;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.interfaces.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.interfaces.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Patient;

public class AddFriendsViewImplementation<T extends EventViewInterface> extends VerticalLayout{

	private static final long serialVersionUID = 1L;
	
	private T presenter;

	public AddFriendsViewImplementation(T presenter) {
		this.presenter = presenter;
		
		Grid<Patient> gridFriends = new Grid<>();
		ListDataProvider<Patient> dataProviderFriends = new ListDataProvider<>(presenter.getAccounts());
		gridFriends.setDataProvider(dataProviderFriends);
		Grid.Column<Patient> firstNameColumn = gridFriends.addColumn(acc -> acc.getFirstName()).setHeader("First Name");
		Grid.Column<Patient> lastNameColumn = gridFriends.addColumn(acc -> acc.getLastName()).setHeader("Last Name");
		gridFriends.addComponentColumn(item -> createAddFriendButton(item)).setHeader("Add Friend");
		
		HeaderRow filterRow = gridFriends.appendHeaderRow();
		// First filter for first name
		TextField firstNameField = new TextField();
		firstNameField.addValueChangeListener(event -> dataProviderFriends.addFilter(
				firstName -> StringUtils.containsIgnoreCase(firstName.getFirstName(), firstNameField.getValue())));

		firstNameField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(firstNameColumn).setComponent(firstNameField);
		firstNameField.setSizeFull();
		firstNameField.setPlaceholder("Filter");
		
		// Second filter for last name
		TextField lastNameField = new TextField();
		lastNameField.addValueChangeListener(event -> dataProviderFriends.addFilter(
				lastName -> StringUtils.containsIgnoreCase(lastName.getLastName(), lastNameField.getValue())));

		lastNameField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(lastNameColumn).setComponent(lastNameField);
		lastNameField.setSizeFull();
		lastNameField.setPlaceholder("Filter");
		
		gridFriends.getStyle().set("overflowY", "auto");
		gridFriends.setWidth("100%");

		setMaxHeight("800px");
		setMinHeight("500px");
		setMinWidth("500px");
		add(gridFriends);
	}

	private Button createAddFriendButton(Patient patient) {
		Button buttonAddFriend = new Button("ADD FRIEND");
		buttonAddFriend.addClickListener(event -> presenter.addFriend(patient));
		return buttonAddFriend;
	}

}
