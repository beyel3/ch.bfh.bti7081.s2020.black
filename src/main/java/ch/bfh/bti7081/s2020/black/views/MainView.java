package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import ch.bfh.bti7081.s2020.black.presenters.EventPresenter;

/**
 * The main view contains a button and a click listener.
 */

@Route(value = "home", layout = MainLayoutView.class)
@RouteAlias(value = "", layout = MainLayoutView.class)
@CssImport("./styles/shared-styles.css")
@PageTitle("Home")

public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public static final EventPresenter eventPresenter = new EventPresenter();
	private final HorizontalLayout contentLayoutFirstRow;
	private final HorizontalLayout contentLayoutSecondRow;

	public MainView() {

		setMargin(false);
		setSpacing(false);
		setSizeFull();

		contentLayoutFirstRow = new HorizontalLayout();
		contentLayoutFirstRow.setWidth("100%");
		contentLayoutFirstRow.setHeight("35%");

		contentLayoutSecondRow = new HorizontalLayout();
		contentLayoutSecondRow.setWidth("100%");
		contentLayoutSecondRow.setHeight("35%");

		Button createEventButton = new Button("CREATE EVENT");
		createEventButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("EventCreaterView")));
		createEventButton.getStyle().set("height", "90%");
		createEventButton.getStyle().set("width", "40%");
		createEventButton.getStyle().set("font-size", "20px");
		createEventButton.getStyle().set("font-size", "2vw");

		Button browseEventButton = new Button("CREATE EVENT FROM TEMPLATE");
		browseEventButton.addClickListener(event -> browseEventButton.getUI()
				.ifPresent(ui -> ui.navigate("EventCreaterView/CreatedFromTemplate")));
		browseEventButton.getStyle().set("height", "90%");
		browseEventButton.getStyle().set("width", "40%");
		browseEventButton.getStyle().set("font-size", "20px");
		browseEventButton.getStyle().set("font-size", "2vw");

		Button searchOpenPublicEventButton = new Button("SEARCH OPEN PUBLIC EVENTS");
		searchOpenPublicEventButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("EventSearchView")));
		searchOpenPublicEventButton.getStyle().set("height", "90%");
		searchOpenPublicEventButton.getStyle().set("width", "40%");
		searchOpenPublicEventButton.getStyle().set("font-size", "20px");
		searchOpenPublicEventButton.getStyle().set("font-size", "2vw");

		Button myEventsButton = new Button("MY EVENTS");
		myEventsButton.addClickListener(event -> browseEventButton.getUI().ifPresent(ui -> ui.navigate("AccountView")));
		myEventsButton.getStyle().set("height", "90%");
		myEventsButton.getStyle().set("width", "40%");
		myEventsButton.getStyle().set("font-size", "20px");
		myEventsButton.getStyle().set("font-size", "2vw");

		contentLayoutFirstRow.add(createEventButton, browseEventButton);
		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.add(searchOpenPublicEventButton, myEventsButton);
		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.setDefaultVerticalComponentAlignment(Alignment.STRETCH);

		add(contentLayoutFirstRow);
		add(contentLayoutSecondRow);

	}

}
