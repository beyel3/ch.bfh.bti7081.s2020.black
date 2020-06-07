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

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.CloseEventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Tag;


public class MarkEventDoneViewImplementation<T extends CloseEventViewInterface> extends VerticalLayout {

	private TextField title;
	private TextArea description;
	private MultiSelectListBox<Tag> tags;
	private MultiSelectListBox<Account> participants;
	private NumberField rating;
	private MultiFileMemoryBuffer buffer;
	private Upload upload;

	public MarkEventDoneViewImplementation(T presenter) {
		
		setSizeFull();
		VerticalLayout formLayout = new VerticalLayout();
		formLayout.setWidth("100%");
		formLayout.getStyle().set("align-items", "center");
		
		FormLayout form = new FormLayout();		
		
		//Init
		title = new TextField();
		title.setWidth("50%");
		title.setReadOnly(true);
		title.setValue(presenter.getEvent().getEventTemplate().getTitle());
		
		description = new TextArea();
		description.setWidth("50%");
		description.setMinHeight("100px");
		description.setReadOnly(true);
		description.setValue(presenter.getEvent().getEventTemplate().getDescription());
		
		tags = new MultiSelectListBox<>();
		tags.setReadOnly(true);
		tags.setItems(presenter.getEvent().getEventTemplate().getTags());
		
		participants = new MultiSelectListBox<>();
		participants.setItems(presenter.getEvent().getParticipants());
		
		rating = new NumberField();
		rating.setHasControls(true);
		rating.setStep(1);
		rating.setMin(1);
		rating.setMax(10);
		
		buffer = new MultiFileMemoryBuffer();
		upload = new Upload(buffer);
		upload.addSucceededListener(event -> {
//		    Component component = createComponent(event.getMIMEType(),
//		            event.getFileName(),
//		            buffer.getInputStream(event.getFileName()));
//		    showOutput(event.getFileName(), component, output);
			
//			File fnew= newFile("/tmp/rose.jpg");
//			BufferedImage originalImage= ImageIO.read(fnew);
//			ByteArrayOutputStream baos= newByteArrayOutputStream();
//			ImageIO.write(originalImage, "jpg", baos);
//			byte[] imageInByte= baos.toByteArray();
		});
		
		
		
		
		byte[] picture = null;
//				StreamResource resource = new StreamResource("dummyImageName.jpg", () -> new ByteArrayInputStream(imageBytes));
//				Image image = new Image(resource, "dummy image");
		
		
		Button done = new Button("MARK EVENT AS DONE");
		done.getStyle().set("marginRight", "10px");
		done.addClickListener(event -> {
			presenter.closeEvent(picture, (int) Math.round(rating.getValue()));
		});
		

		form.addFormItem(title, "Titel");
		form.addFormItem(description, "Description");
		form.addFormItem(tags, "Tags");
		form.addFormItem(participants, "Participants");
		form.addFormItem(upload, "Upload selfie");
		form.addFormItem(rating, "Rate the event");
		form.addFormItem(done, "");
		form.setResponsiveSteps(new ResponsiveStep("40em", 1));
		formLayout.add(form);
		add(formLayout);

	}
	
}
