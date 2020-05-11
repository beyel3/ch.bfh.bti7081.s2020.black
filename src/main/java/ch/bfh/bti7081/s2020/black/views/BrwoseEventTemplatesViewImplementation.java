package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.EventTemplatePresenter;

@Route(value = "BrowseEventTemplates", layout = MainView.class)
public class BrwoseEventTemplatesViewImplementation extends VerticalLayout {

	private EventTemplatePresenter eventTemplatePresenter = new EventTemplatePresenter();
	
	
	public BrwoseEventTemplatesViewImplementation() {
		
		List<EventTemplate> eventTemplates = eventTemplatePresenter.getEventTemplates();
		HorizontalLayout templates = new HorizontalLayout();
		
		for(EventTemplate t : eventTemplates) {

			VerticalLayout layout = new VerticalLayout();
			
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
			
			Button button = new Button("USE AS TEMPLATE");
			button.addClickListener(event -> 
			getUI().ifPresent(ui -> ui.navigate("Event/" + t.getTemplateIDforURL())));
			
			layout.add(title, description, tags, button, progressBar);
			templates.add(layout);
		}
		
		add(templates);
	}
}
