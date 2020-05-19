package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.presenters.CreateTemplatePresenter;

public class CreateTemplateViewImplementation extends HorizontalLayout{

		private TextField title;
		private TextArea description;
		private MultiSelectListBox<Tag> tags;
		private Button createTemplate;
		private CreateTemplatePresenter createTemplatePresenter = new CreateTemplatePresenter();
		
		public CreateTemplateViewImplementation() {
					
			//Init
			title = new TextField();
			description = new TextArea();
			tags = new MultiSelectListBox<>();
			

			title.setWidth("50%");
			title.setClearButtonVisible(true);
			title.setRequiredIndicatorVisible(true);
			
			description.setWidth("50%");
			description.setMinHeight("100px");
			description.setClearButtonVisible(true);
			description.setRequiredIndicatorVisible(true);
			
			tags.setItems(createTemplatePresenter.getTags());
			
			FormLayout formLayout = new FormLayout();
			formLayout.addFormItem(title, "Titel");
			formLayout.addFormItem(description, "Beschreibung");
			formLayout.addFormItem(tags, "Wähle Tags");
			
			createTemplate = new Button("Template erstellen");
			createTemplate.getStyle().set("marginRight", "10px");
			createTemplate.addClickListener(event -> {
				createTemplatePresenter.saveEventTemplate(title.getValue(),description.getValue(), tags.getValue());
				
				EventTemplate t = createTemplatePresenter.getSavedTemplate();
				UI.getCurrent().navigate("CreateEventView" + t.getId());

			});

			formLayout.setResponsiveSteps(new ResponsiveStep("40em", 1));
			
			add(formLayout, createTemplate);
		}
	}
