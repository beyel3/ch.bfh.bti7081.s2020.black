package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import static ch.bfh.bti7081.s2020.black.views.MainView.eventPresenter;

@Route("EventSearchView")
public class EventSearchView extends HorizontalLayout{

	
	public EventSearchView() {
		
		
		EventTemplate eventTemplate = eventPresenter.getEventTemplate();
		VerticalLayout layout = new VerticalLayout();
		TextField title = new TextField(eventTemplate.getTitle());
		TextArea description = new TextArea(eventTemplate.getDescription());
		MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
		tags.setItems(eventTemplate.getTags());
		layout.add(title, description, tags);
		
		add(layout);
	}
	
}
