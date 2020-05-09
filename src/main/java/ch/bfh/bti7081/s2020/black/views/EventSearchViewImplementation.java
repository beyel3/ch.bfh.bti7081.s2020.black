package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

@Route("EventSearchView")
public class EventSearchViewImplementation extends VerticalLayout{

	
	public EventSearchViewImplementation() {
		
		List<EventTemplate> eventTemplates = new ArrayList<EventTemplate>();
		
		for(EventTemplate t : eventTemplates) {

			HorizontalLayout layout = new HorizontalLayout();
			
			TextField title = new TextField();
			title.setValue(t.getTitle());
			title.setReadOnly(true);
			
			TextArea description = new TextArea();
			description.setValue(t.getDescription());
			description.setReadOnly(true);
			
			MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
			tags.setItems(t.getTags());
			tags.setReadOnly(true);
			
			ProgressBar progressBar = new ProgressBar();
			progressBar.setValue(0.32);
			
			layout.add(title, description, tags, progressBar);
			add(layout);
		}
	}
}
