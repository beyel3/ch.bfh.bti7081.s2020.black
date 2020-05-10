package ch.bfh.bti7081.s2020.black.presenters;

import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.RouterLayout;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.views.EventCreaterViewImplementaion;
import ch.bfh.bti7081.s2020.black.views.EventSearchViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainViewInterface;

public class MainPresenter implements RouterLayout, MainViewInterface.MainViewListener {

	private MainViewImplementation view;
	private EventPresenter eventPresenter;
	private AccountPresenter accountPresenter;

	public MainPresenter(MainModel model, MainViewImplementation view) {
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


	@Override
	public Element getElement() {
		
		return null;
	}

}
