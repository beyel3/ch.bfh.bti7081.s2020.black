package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;

public class EventViewImplementation<T extends EventViewInterface> extends VerticalLayout {

	private T presenter;
	
	public EventViewImplementation(T presenter) {
	this.presenter = presenter;
		
	}
	
}
