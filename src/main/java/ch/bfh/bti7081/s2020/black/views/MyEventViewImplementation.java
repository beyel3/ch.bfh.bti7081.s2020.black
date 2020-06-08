package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;
import ch.bfh.bti7081.s2020.black.model.Account;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.Tag;

import javax.swing.tree.ExpandVetoException;

public class MyEventViewImplementation<T extends EventViewInterface> extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private Dialog dialogShowEventDetails;
	private VerticalLayout eventLayout = new VerticalLayout();

	public MyEventViewImplementation(T presenter) {
		setSizeFull();
		getStyle().set("align-items", "center");
		getStyle().set("overflowX", "hidden");
		Label labelMyEvents = new Label("My Events: ");
		labelMyEvents.getStyle().set("font-size", "24px");
		labelMyEvents.getStyle().set("font-weight", "bold");
		Label labelMyPatients = new Label("My Patients: ");
		labelMyPatients.getStyle().set("font-size", "24px");
		labelMyPatients.getStyle().set("font-weight", "bold");
		eventLayout.setWidth("80%");
		eventLayout.setMaxHeight("60vh");
		eventLayout.getStyle().set("overflowY", "auto");
		eventLayout.getStyle().set("display", "block");
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setWidth("100%");
		ArrayList<Event> myOpenEvents = presenter.getMyOpenEvents();

		for (Event e :myOpenEvents) {

			//System.out.println(e.getEventTemplate().getId());

			String participantsHelper = new String(e.getParticipants().toString());
			participantsHelper = participantsHelper.substring(1, participantsHelper.length() - 1);
			participantsHelper = participantsHelper.replaceAll(", ", "\n");
			VerticalLayout layout = new VerticalLayout();
			layout.getStyle().set("border", "2px solid #2f6f91");

			TextField title = new TextField();
			title.setSizeFull();
			title.setValue(e.getEventTemplate().getTitle());
			title.setReadOnly(true);

			TextArea description = new TextArea();
			description.setSizeFull();
			description.setValue(e.getEventTemplate().getDescription());
			description.setReadOnly(true);

			MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
			tags.setItems(e.getEventTemplate().getTags());
			tags.setReadOnly(true);

			ProgressBar progressBar = new ProgressBar();
			progressBar.setValue(e.getEventTemplate().getAvgRating() / 10);

			Button buttonChat = new Button("CHAT");
			buttonChat.addClickListener(event -> presenter.buttonClick(EventViewInterface.EventAction.OPENCHAT, e));
			buttonChat.setWidth("100%");

//			Button buttonDetails = new Button("DETAILS");
//			buttonDetails.addClickListener(event -> 
//			presenter.buttonClick(EventViewInterface.EventAction.DETAILS, e));

			Button buttonMarkDone = new Button("EVENT DONE");
			buttonMarkDone.addClickListener(event -> presenter.buttonClick(EventViewInterface.EventAction.MARKDONE, e));
			buttonMarkDone.setWidth("100%");

			TextArea participants = new TextArea();
			participants.setSizeFull();
			participants.setValue(participantsHelper);
			participants.setReadOnly(true);
			layout.add(title, description, tags, progressBar, participants, buttonChat, buttonMarkDone);
			layout.getStyle().set("padding", "5px");
			layout.getStyle().set("margin-left", "2px");
			layout.getStyle().set("margin-right", "2px");
			layout.setMinWidth("350px");

			eventLayout.add(layout);
		}

		Label labelMyPastEvents = new Label("My Past Events: ");
		labelMyPastEvents.getStyle().set("font-size", "24px");
		labelMyPastEvents.getStyle().set("font-weight", "bold");

		Grid<Event> grid = new Grid<>();
		ListDataProvider<Event> dataProvider = new ListDataProvider<>(presenter.getMyDoneEvents());
		grid.setDataProvider(dataProvider);

		Grid.Column<Event> titleColumn = grid.addColumn(event -> event.getEventTemplate().getTitle())
				.setHeader("Title");
		Grid.Column<Event> descriptionColumn = grid.addColumn(event -> event.getEventTemplate().getDescription())
				.setHeader("Description");
		Grid.Column<Event> tagColumn = grid
				.addColumn(event -> event.getEventTemplate().getTags().toString().replaceAll("\\[|\\]", ""))
				.setHeader("Tags");
		Grid.Column<Event> paricipantsColumn = grid
				.addColumn(event -> event.getParticipants().toString().replaceAll("\\[|\\]", ""))
				.setHeader("Participants");
		descriptionColumn.setFlexGrow(3);

		grid.addSelectionListener(event -> {
			Set<Event> temp = event.getAllSelectedItems();
			createDetailDialogBoxForEvent(temp.iterator().next());
		});

		HeaderRow filterRow = grid.appendHeaderRow();

		// First filter for rating
		TextField titleField = new TextField();
		titleField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getEventTemplate().getTitle(), titleField.getValue())));

		titleField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(titleColumn).setComponent(titleField);
		titleField.setSizeFull();
		titleField.setPlaceholder("Filter");

		// Second Filter for description
		TextField descriptionField = new TextField();
		descriptionField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getEventTemplate().getDescription(), descriptionField.getValue())));

		descriptionField.setValueChangeMode(ValueChangeMode.EAGER);
		filterRow.getCell(descriptionColumn).setComponent(descriptionField);
		descriptionField.setSizeFull();
		descriptionField.setPlaceholder("Filter");

		// Thrid Filter for tags
		TextField tagField = new TextField();
		ArrayList<String> tagList = new ArrayList<>();
		tagField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getEventTemplate().getTags().toString(), tagField.getValue())));

		tagField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(tagColumn).setComponent(tagField);
		tagField.setSizeFull();
		tagField.setPlaceholder("Filter");

		// Fourth Filter for participants
		TextField participantsField = new TextField();
		ArrayList<String> participantsList = new ArrayList<>();
		participantsField.addValueChangeListener(event -> dataProvider.addFilter(publicEvent -> StringUtils
				.containsIgnoreCase(publicEvent.getParticipants().toString(), tagField.getValue())));

		participantsField.setValueChangeMode(ValueChangeMode.EAGER);

		filterRow.getCell(tagColumn).setComponent(tagField);
		participantsField.setSizeFull();
		participantsField.setPlaceholder("Filter");

		Grid<Account> gridFriends = new Grid<>();
		ListDataProvider<Account> dataProviderFriends = new ListDataProvider<>(presenter.getMyFriends());
		gridFriends.setDataProvider(dataProviderFriends);
		Grid.Column<Account> firstNameColumn = gridFriends.addColumn(acc -> acc.getFirstName()).setHeader("First Name");
		Grid.Column<Account> lastNameColumn = gridFriends.addColumn(acc -> acc.getLastName()).setHeader("Last Name");
		gridFriends.getStyle().set("overflowY", "auto");
		gridFriends.setWidth("50%");

		grid.setWidth("100%");
		grid.setHeight("400px");
		grid.getStyle().set("overflowY", "auto");

		Button buttonSearchFriends = new Button("SEARCH NEW FRIENDS");
		buttonSearchFriends.addClickListener(event -> presenter.buttonClick(EventViewInterface.EventAction.ADDFRIEND, null));

		VerticalLayout topLeftLayout = new VerticalLayout(labelMyEvents, eventLayout);
		topLeftLayout.setWidth("50%");
		VerticalLayout topRightLayout = new VerticalLayout(labelMyPatients, gridFriends, buttonSearchFriends);
		topRightLayout.setWidth("50%");
		topLayout.add(topLeftLayout, topRightLayout);
		topLeftLayout.getStyle().set("align-items", "center");
		topLayout.setMinHeight("65vh");

		add(topLayout, labelMyPastEvents, grid);
	}

	private void createDetailDialogBoxForEvent(Event singleEvent) {
		dialogShowEventDetails = new Dialog();
		dialogShowEventDetails.setSizeFull();

		VerticalLayout events = new VerticalLayout();
		events.setWidth("100%");
		events.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//		templates.getStyle().set("border", "1px solid black");
		events.getStyle().set("overflowY", "auto");
		events.getStyle().set("display", "block");
		String participantsHelper = new String(singleEvent.getParticipants().toString());
		participantsHelper = participantsHelper.substring(1, participantsHelper.length() - 1);
		participantsHelper = participantsHelper.replaceAll(", ", "\n");

		VerticalLayout layout = new VerticalLayout();
		layout.getStyle().set("border", "1px solid #2f6f91");
		layout.getStyle().set("margin", "2px");

		TextField title = new TextField();
		title.setSizeFull();
		title.setValue(singleEvent.getEventTemplate().getTitle());
		title.setReadOnly(true);

		TextArea description = new TextArea();
		description.setSizeFull();
		description.setValue(singleEvent.getEventTemplate().getDescription());
		description.setReadOnly(true);

		MultiSelectListBox<Tag> tags = new MultiSelectListBox<Tag>();
		tags.setItems(singleEvent.getEventTemplate().getTags());
		tags.setReadOnly(true);

		ProgressBar progressBar = new ProgressBar();
		progressBar.setValue(singleEvent.getEventTemplate().getAvgRating() / 10);

		TextArea participants = new TextArea();
		participants.setSizeFull();
		participants.setValue(participantsHelper);
		participants.setReadOnly(true);

		Image selfie = new Image("https://dummyimage.com/600x400/000/fff", "");
		selfie.setWidth("100%");

//		byte[] imageBytes = // your data source here
//				StreamResource resource = new StreamResource("dummyImageName.jpg", () -> new ByteArrayInputStream(imageBytes));
//				Image image = new Image(resource, "dummy image");

		layout.add(title, description, tags, progressBar, participants, selfie);
		layout.setMinWidth("350px");
		layout.setMaxWidth("100vw");
		events.add(layout);

		dialogShowEventDetails.add(events);
		dialogShowEventDetails.open();
	}

}
