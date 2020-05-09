package ch.bfh.bti7081.s2020.black.presenters;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.views.EventCreaterView;
import ch.bfh.bti7081.s2020.black.views.EventCreaterViewImplementaion;

public class EventPresenter implements EventCreaterView.EventCreaterViewListener{

	private MainViewPresenter mainViewPresenter;
	private HardCoded hardCoded;
	private EventCreaterViewImplementaion eventCreaterView;

	public EventPresenter(MainViewPresenter mainViewPresenter) {
		this.mainViewPresenter = mainViewPresenter;
		this.eventCreaterView = new EventCreaterViewImplementaion();
	
		
		hardCoded = new HardCoded();
		
	}

	public Collection<Tag> getTags() {
		return hardCoded.getTags();
	}

	public List<EventTemplate> getEventTemplates() {
		return hardCoded.getEventTemplates();
	}

	@Override
	public void buttonClick(char operation) {
		// TODO Auto-generated method stub
		
	}
	
}
