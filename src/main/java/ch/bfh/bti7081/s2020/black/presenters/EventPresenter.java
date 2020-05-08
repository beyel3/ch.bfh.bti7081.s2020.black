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

public class EventPresenter {

	private HardCoded hardCoded;
	private EventCreaterView eventCreaterView;

	public EventPresenter() {
		hardCoded = new HardCoded();
	}

	public Collection<Tag> getTags() {
		return hardCoded.getTags();
	}

	public List<EventTemplate> getEventTemplates() {
		return hardCoded.getEventTemplates();
	}
	
}
