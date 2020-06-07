package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Patient;

public class AddFriendsViewImplementation<T extends EventViewInterface> extends VerticalLayout{

	private static final long serialVersionUID = 1L;

	public AddFriendsViewImplementation(T presenter) {
		Grid<Patient> gridFriends = new Grid<>();
		ListDataProvider<Patient> dataProviderFriends = new ListDataProvider<>(presenter.getAccounts());
		gridFriends.setDataProvider(dataProviderFriends);
		Grid.Column<Patient> firstNameColumn = gridFriends.addColumn(acc -> acc.getFirstName()).setHeader("First Name");
		Grid.Column<Patient> lastNameColumn = gridFriends.addColumn(acc -> acc.getLastName()).setHeader("Last Name");
		gridFriends.getStyle().set("overflowY", "auto");
		gridFriends.setWidth("100%");

		setMaxHeight("800px");
		setMinHeight("500px");
		setMinWidth("500px");
		add(gridFriends);
	}

}
