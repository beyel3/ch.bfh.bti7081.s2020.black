package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import ch.bfh.bti7081.s2020.black.model.Tag;

public class CreateTemplateViewImplementation extends HorizontalLayout {

	private TextField title;
	private TextArea description;
	private MultiSelectListBox<Tag> tags;
	private Button createTemplate;
//	private CreateTemplatePresenter createTemplatePresenter = new CreateTemplatePresenter();

	public CreateTemplateViewImplementation() {
		setSizeFull();
		setPadding(true);

		// Init
		title = new TextField();
		description = new TextArea();
		tags = new MultiSelectListBox<>();

		title.setWidth("70%");
		title.setClearButtonVisible(true);
		title.setRequiredIndicatorVisible(true);

		description.setWidth("70%");
		description.setMinHeight("100px");
		description.setClearButtonVisible(true);
		description.setRequiredIndicatorVisible(true);

//		tags.setItems(createTemplatePresenter.getTags());

		FormLayout formLayout = new FormLayout();
		formLayout.addFormItem(title, "Titel");
		formLayout.addFormItem(description, "Beschreibung");
		formLayout.addFormItem(tags, "Wähle Tags");

		createTemplate = new Button("Template erstellen");
		createTemplate.getStyle().set("marginRight", "10px");
		createTemplate.addClickListener(event -> {


//			EventTemplate t = createTemplatePresenter.saveEventTemplate(title.getValue(), description.getValue(), tags.getValue());

//			UI.getCurrent().navigate("CreateEventView" + t.getId());


		});

		formLayout.setResponsiveSteps(new ResponsiveStep("40em", 1));
		add(formLayout, createTemplate);
	}
}
