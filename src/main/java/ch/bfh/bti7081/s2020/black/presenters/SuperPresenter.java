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

	private StateModel stateModel;
	private Account account;
	
	public SuperPresenter(MainView mainView) {

		this.stateModel = null;
		this.mainView = mainView;

		header = new VerticalLayout();
		page = new VerticalLayout();

		new HeaderPresenter(this);
		new HomeViewPresenter(this);

		mainView.add(header,page);
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

	public Account getLoggedInAccount() {
		return this.account;
	}
}
