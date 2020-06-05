package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.black.model.Tag;

@Route(value = "Test")
public class MarkEventDoneViewImplementation extends VerticalLayout{

//	private T presenter;
	private TextField title;
	private TextArea description;
	private MultiSelectListBox<Tag> tags;
	private NumberField maxParticipants;
	private NumberField rating;
	private MultiFileMemoryBuffer buffer;
	private Upload upload;

	public MarkEventDoneViewImplementation() {
		setSizeFull();
		VerticalLayout formLayout = new VerticalLayout();
		formLayout.setWidth("100%");
		formLayout.getStyle().set("align-items", "center");
		
		//Init
		title = new TextField();
		description = new TextArea();
		tags = new MultiSelectListBox<>();
		maxParticipants = new NumberField();
		rating = new NumberField();
		buffer = new MultiFileMemoryBuffer();
		upload = new Upload(buffer);
		
		Button done = new Button("MARK EVENT AS DONE");
		done.getStyle().set("marginRight", "10px");
		
		// Event Handler
		done.addClickListener(event -> {
//			presenter.submit(
//					publicEvent.getValue(),
//					(int) Math.round(maxParticipants.getValue()),
//					participants.getSelectedItems());
		});

		upload.addSucceededListener(event -> {
//		    Component component = createComponent(event.getMIMEType(),
//		            event.getFileName(),
//		            buffer.getInputStream(event.getFileName()));
//		    showOutput(event.getFileName(), component, output);
		});
		
		FormLayout form = new FormLayout();		
		title.setWidth("50%");
		title.setReadOnly(true);
		title.setValue("Test");
		
		description.setWidth("50%");
		description.setMinHeight("100px");
		description.setReadOnly(true);
		description.setValue("Test");
		
//		tags.setItems(eventTemplate.getTags());		
		tags.setReadOnly(true);
		
		maxParticipants.setReadOnly(true);
		
		rating.setHasControls(true);
		rating.setStep(1);
		rating.setMin(1);
		rating.setMax(10);
		
		form.addFormItem(title, "Titel");
		form.addFormItem(description, "Description");
		form.addFormItem(tags, "Tags");
		form.addFormItem(maxParticipants, "Maximum participants");
		form.addFormItem(upload, "Upload selfie");
		form.addFormItem(rating, "Rate the event");
		form.addFormItem(done, "");
		form.setResponsiveSteps(new ResponsiveStep("40em", 1));
		formLayout.add(form);
		add(formLayout);
	}
	
}
