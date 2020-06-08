package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class CreateTemplateViewImplementation<T extends EventTemplateInterface> extends VerticalLayout {

	private TextField title;
	private TextArea description;
	private MultiSelectListBox<Tag> tags;
	private Button createTemplate;

	public CreateTemplateViewImplementation(T presenter) {
				
		setSizeFull();
		setPadding(true);

		// Init
		title = new TextField();
		description = new TextArea();
		this.tags = new MultiSelectListBox<>();

		title.setWidth("70%");
		title.setClearButtonVisible(true);
		title.setRequiredIndicatorVisible(true);

		description.setWidth("70%");
		description.setMinHeight("100px");
		description.setClearButtonVisible(true);
		description.setRequiredIndicatorVisible(true);

		this.tags.setItems(presenter.getTags());

		FormLayout formLayout = new FormLayout();
		formLayout.addFormItem(title, "Title");
		formLayout.addFormItem(description, "Description");
		formLayout.addFormItem(this.tags, "Choose Tags");

		createTemplate = new Button("Template erstellen");
		createTemplate.getStyle().set("marginRight", "10px");
		
		createTemplate.addClickListener(event -> {
			presenter.submitNewEventTemplate(title.getValue(), description.getValue(), tags.getValue());
		});
		// Layout Steps
		formLayout.setResponsiveSteps(new ResponsiveStep("40em", 1));
		formLayout.setSizeFull();
		add(formLayout, createTemplate);
	}
}
