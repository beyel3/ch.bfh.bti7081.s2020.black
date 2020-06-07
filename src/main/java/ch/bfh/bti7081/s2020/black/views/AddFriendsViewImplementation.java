package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.AddFriendsViewInterface;
import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.model.Account;

public class AddFriendsViewImplementation<T extends AddFriendsViewInterface> extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;

	public AddFriendsViewImplementation() {
//		Grid<Account> gridFriends = new Grid<>();
//		ListDataProvider<Account> dataProviderFriends = new ListDataProvider<>(presenter.getMyFriends());
//		gridFriends.setDataProvider(dataProviderFriends);
//		Grid.Column<Account> firstNameColumn = gridFriends.addColumn(acc -> acc.getFirstName()).setHeader("First Name");
//		Grid.Column<Account> lastNameColumn = gridFriends.addColumn(acc -> acc.getLastName()).setHeader("Last Name");
//		gridFriends.getStyle().set("overflowY", "auto");
//		gridFriends.setWidth("50%");
	}

}
