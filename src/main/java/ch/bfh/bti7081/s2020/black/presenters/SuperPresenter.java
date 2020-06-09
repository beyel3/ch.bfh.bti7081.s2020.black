package ch.bfh.bti7081.s2020.black.presenters;

import com.vaadin.flow.component.Component;

import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.stateModel.StateModel;
import ch.bfh.bti7081.s2020.black.views.HeaderViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SuperPresenter {

	private MainView mainView;
	private VerticalLayout header;
	private VerticalLayout page;

	private Account account;
	private StateModel stateModel;

	public SuperPresenter(MainView mainView) {

		this.stateModel = null;
		this.mainView = mainView;

		header = new VerticalLayout();
		page = new VerticalLayout();

		mainView.add(header, page);

		if (account == null) {
			new LoginPresenter(this);
		} else {
			new HeaderPresenter(this);
			new HomeViewPresenter(this);
		}
	}

	public void addHeader(Component component) {
		header.add(component);
	}

	public void removeHeader(Component currentView) {
		header.remove(currentView);
	}

	public void addPage(Component component) {
		page.add(component);
	}

	public void removePage(Component currentView) {
		page.remove(currentView);
	}

	public void clearView() {
		page.removeAll();
	}

	public void setState(StateModel stateModel) {
		this.stateModel = stateModel;
	}

	public void home() {
		clearView();
		new HeaderPresenter(this);
		new HomeViewPresenter(this);
	}

	public void setLoggedInAccount(Account account) {
		this.account = account;
	}

	public void removeLoggedInAccount() {
		this.account = null;
	}

	public Account getLoggedInAccount() {
		return this.account;
	}

	public String getLoggedInUserName() {
		if (account != null) {
			return account.getFirstName();
		} else {
			return "";
		}
	}
}
