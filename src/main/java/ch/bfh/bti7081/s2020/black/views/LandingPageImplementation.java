package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;


@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class LandingPageImplementation extends VerticalLayout {
	
	private final HorizontalLayout contentLayoutFirstRow;
	private final HorizontalLayout contentLayoutSecondRow;


	public LandingPageImplementation(){
		
		contentLayoutFirstRow = new HorizontalLayout();
		contentLayoutFirstRow.setWidth("100%");
		contentLayoutFirstRow.setHeight("50%");

		contentLayoutSecondRow = new HorizontalLayout();
		contentLayoutSecondRow.setWidth("100%");
		contentLayoutSecondRow.setHeight("50%");
		
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
			b.getStyle().set("height", "90%");
			b.getStyle().set("width", "40%");
			b.getStyle().set("font-size", "20px");
			b.getStyle().set("font-size", "2vw");	
		}

		contentLayoutFirstRow.add(createEventButton, browseEventButton);
		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.add(searchOpenPublicEventButton, myEventsButton);
		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		//contentLayoutSecondRow.getContent().setHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);		

		
		add(contentLayoutFirstRow,contentLayoutSecondRow);


	}
	
}
