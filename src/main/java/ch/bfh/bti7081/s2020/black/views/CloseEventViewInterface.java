package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface.EventAction;
import ch.bfh.bti7081.s2020.black.model.Event;

public interface CloseEventViewInterface {
	public Event getEvent();
	public void buttonClick(EventAction openchat, ClickEvent<Button> event);
	public enum CloseAction{
		UPLOAD,
		CLOSE
	}

}
