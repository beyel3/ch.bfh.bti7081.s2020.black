package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@Route(value = "LandingPage", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class LandingPageImplementation extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HorizontalLayout contentLayoutFirstRow;
	private final HorizontalLayout contentLayoutSecondRow;


	public LandingPageImplementation(){
		setSizeFull();
				
		contentLayoutFirstRow = new HorizontalLayout();
		contentLayoutFirstRow.setWidth("100%");
		contentLayoutFirstRow.setHeight("200px");

		contentLayoutSecondRow = new HorizontalLayout();
		contentLayoutSecondRow.setWidth("100%");
		contentLayoutSecondRow.setHeight("200px");
		setFlexGrow(1, contentLayoutFirstRow,contentLayoutSecondRow);
		
		List<Button> buttons = new ArrayList<Button>();
		
		Button createEventButton = new Button("CREATE EVENT");
		
		createEventButton.addClickListener(event -> 
			 getUI().ifPresent(ui -> ui.navigate("Event")));
			 
		
		Button browseEventButton = new Button("CREATE EVENT FROM TEMPLATE");
		
		browseEventButton.addClickListener(event -> 
			getUI().ifPresent(ui -> ui.navigate("BrowseEventTemplates")));
		
		Button searchOpenPublicEventButton = new Button("SEARCH OPEN PUBLIC EVENTS");
		
		searchOpenPublicEventButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("")));

		Button myEventsButton = new Button("MY EVENTS");
		
		myEventsButton.addClickListener(event -> browseEventButton.getUI().ifPresent(ui -> ui.navigate("AccountView")));
		
		
		buttons.add(myEventsButton);
		buttons.add(searchOpenPublicEventButton);
		buttons.add(browseEventButton);
		buttons.add(createEventButton);
		
		for(Button b : buttons) {
			b.getStyle().set("height", "200px");
			b.getStyle().set("width", "40%");
			b.getStyle().set("font-size", "20px");
			b.getStyle().set("font-size", "2vw");	
		}

		contentLayoutFirstRow.add(createEventButton, browseEventButton);
		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.add(searchOpenPublicEventButton, myEventsButton);
		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		
		add(contentLayoutFirstRow,contentLayoutSecondRow);


	}
	
}
