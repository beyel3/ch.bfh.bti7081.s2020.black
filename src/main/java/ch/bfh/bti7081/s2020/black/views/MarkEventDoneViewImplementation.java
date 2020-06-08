package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.CloseEventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Tag;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;


public class MarkEventDoneViewImplementation<T extends CloseEventViewInterface> extends VerticalLayout {

	private TextField title;
	private TextArea description;
	private MultiSelectListBox<Tag> tags;
	private MultiSelectListBox<Account> participants;
	private NumberField rating;
	private MultiFileMemoryBuffer buffer;
	private Upload upload;
	private byte[] imageBytes;
	private Image preview = new Image();

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

		preview.setMaxWidth("600px");
		preview.setMaxHeight("400px");

		MemoryBuffer memoryBuffer = new MemoryBuffer();
		Upload upload = new Upload(memoryBuffer);
		upload.addFinishedListener(e -> {
			imageBytes = getImageByteArray(memoryBuffer);
			preview = getPreviewImage(imageBytes);
			add(getPreviewImage(imageBytes));
		});

		Button done = new Button("MARK EVENT AS DONE");
		done.getStyle().set("marginRight", "10px");
		done.addClickListener(event -> {
			//presenter.closeEvent(imageBytes, (int) Math.round(rating.getValue()));
			presenter.closeEvent(imageBytes, 5);
		});
		

		form.addFormItem(title, "Titel");
		form.addFormItem(description, "Description");
		form.addFormItem(tags, "Tags");
		form.addFormItem(participants, "Participants");
		form.addFormItem(upload, "Upload selfie");
		//form.add(preview);
		form.addFormItem(rating, "Rate the event");
		form.addFormItem(done, "");
		form.setResponsiveSteps(new ResponsiveStep("40em", 1));
		formLayout.add(form);
		add(formLayout);

	}
	private byte[] getImageByteArray(MemoryBuffer memoryBuffer){
		InputStream inputStream = memoryBuffer.getInputStream();
		try {
			byte[] imageBytes = IOUtils.toByteArray(inputStream);
			return imageBytes;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	private Image getPreviewImage(byte[] imageBytes){
		StreamResource streamResource = new StreamResource("eventImage.png", () -> new ByteArrayInputStream(imageBytes));
		return new Image(streamResource, "event image");
	}
}
