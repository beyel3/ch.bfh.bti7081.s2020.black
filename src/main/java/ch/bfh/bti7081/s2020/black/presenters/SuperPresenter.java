package ch.bfh.bti7081.s2020.black.presenters;

import com.vaadin.flow.component.Component;

import ch.bfh.bti7081.s2020.black.model.stateModel.StateModel;
import ch.bfh.bti7081.s2020.black.views.HeaderViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainView;

public class SuperPresenter {
	
	protected MainView mainView;
	protected HeaderViewImplementation header;
	protected StateModel stateModel;
	
	public SuperPresenter(MainView mainView) {
		this.header = new HeaderViewImplementation();
		this.stateModel = null;
		this.mainView = mainView;
		mainView.add(header);
		
		new HomeViewPresenter(this);
	}
	
	
	public void addView(Component component) {
		mainView.add(component);
	}


	public void removeView(Component currentView) {
		mainView.remove(currentView);
	}


	public void setState(StateModel stateModel) {
		this.stateModel = stateModel;	
	}
}
