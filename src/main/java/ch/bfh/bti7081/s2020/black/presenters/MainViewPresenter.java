package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.views.EventCreaterViewImplementaion;
import ch.bfh.bti7081.s2020.black.views.EventSearchViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainViewInterface;

public class MainViewPresenter implements MainViewInterface.MainViewListener {

	private MainViewImplementation view;
	private EventPresenter eventPresenter;

	public MainViewPresenter(MainModel model, MainViewImplementation view) {
		this.view = view;
		view.addListener(this);
		
		this.eventPresenter = new EventPresenter(this);
	}

	
	@Override
	public void buttonClick(String buttonText) {

		switch (buttonText) {

		case "CREATE EVENT":
			view.getUI().ifPresent(ui -> ui.navigate(EventCreaterViewImplementaion.class));
			break;

		case "BROWSE EVENT TEMPLATES":
			view.getUI().ifPresent(ui -> ui.navigate(EventSearchViewImplementation.class));
			break;
			
		}
	}
}
