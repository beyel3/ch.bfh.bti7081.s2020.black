package ch.bfh.bti7081.s2020.black.views;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.EventTemplatePresenter;

@Route(value = "BrowseEventTemplates", layout = MainView.class)
public class BrowseEventTemplatesViewImplementation extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private EventTemplatePresenter eventTemplatePresenter = new EventTemplatePresenter();

	
	public BrowseEventTemplatesViewImplementation() {
		setSizeFull();
		
		List<EventTemplate> eventTemplates = eventTemplatePresenter.getEventTemplates();
		FormLayout templates = new FormLayout();
		templates.setResponsiveSteps(
		        new ResponsiveStep("5em", 1),
		        new ResponsiveStep("20em", 2),
		        new ResponsiveStep("35em", 3));
		templates.setSizeFull();
//		templates.getStyle().set("border", "1px solid black");
		templates.getStyle().set("overflow", "auto");
		
		for(EventTemplate t : eventTemplates) {

			VerticalLayout layout = new VerticalLayout();
			layout.getStyle().set("border", "1px solid #2f6f91");
			layout.getStyle().set("margin", "2px");


			TextField title = new TextField();
			title.setSizeFull();
			title.setValue(t.getTitle());
			title.setReadOnly(true);
			
			TextArea description = new TextArea();
			description.setSizeFull();
			description.setValue(t.getDescription());
			description.setReadOnly(true);
			
			MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
			tags.setItems(t.getTags());
			tags.setReadOnly(true);
			
			ProgressBar progressBar = new ProgressBar();
			progressBar.setValue(t.getAvgRating()/10);
			
			Button button = new Button("USE AS TEMPLATE");
			button.addClickListener(event -> 
			getUI().ifPresent(ui -> ui.navigate("Event/" + t.getTemplateIDforURL())));
			
			layout.add(title, description, tags, progressBar, button);
			templates.add(layout);
		}
		
		add(templates);
	}
}
