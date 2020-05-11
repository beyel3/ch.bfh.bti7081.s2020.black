package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@Route(value = "LandingPage", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class LandingPageImplementation extends VerticalLayout {
	
	private final HorizontalLayout contentLayoutFirstRow;
	private final HorizontalLayout contentLayoutSecondRow;
	
	public LandingPageImplementation(){
		
		contentLayoutFirstRow = new HorizontalLayout();
		contentLayoutFirstRow.setWidth("100%");
		contentLayoutFirstRow.setHeight("35%");

		contentLayoutSecondRow = new HorizontalLayout();
		contentLayoutSecondRow.setWidth("100%");
		contentLayoutSecondRow.setHeight("35%");
		
		List<Button> buttons = new ArrayList<Button>();
		
		Button createEventButton = new Button("CREATE EVENT");
		buttons.add(createEventButton);
		createEventButton.addClickListener(event -> 
		
			 getUI().ifPresent(ui -> ui.navigate("Event"))
			 
				
			
//		{
//		remove(contentLayoutSecondRow, contentLayoutFirstRow); 
//		add(new EventCreaterViewImplementaion());
//		}
				);
		
		Button browseEventButton = new Button("CREATE EVENT FROM TEMPLATE");
		buttons.add(browseEventButton);
		browseEventButton.addClickListener(event -> 
		
			getUI().ifPresent(ui -> ui.navigate("BrowseEventTemplates"))
		
//		{
//			remove(contentLayoutSecondRow, contentLayoutFirstRow); 
//			add(new EventSearchViewImplementation());
//			}
		);
		
	
		Button searchOpenPublicEventButton = new Button("SEARCH OPEN PUBLIC EVENTS");
		buttons.add(searchOpenPublicEventButton);
		searchOpenPublicEventButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("")));


		Button myEventsButton = new Button("MY EVENTS");
		buttons.add(myEventsButton);
		myEventsButton.addClickListener(event -> browseEventButton.getUI().ifPresent(ui -> ui.navigate("")));
		
		
		for(Button b : buttons) {
			b.getStyle().set("height", "90%");
			b.getStyle().set("width", "40%");
			b.getStyle().set("background-color", "#336699");
			b.getStyle().set("font-size", "20px");
			b.getStyle().set("font-size", "2vw");	
		}

		contentLayoutFirstRow.add(createEventButton, browseEventButton);
//		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.add(searchOpenPublicEventButton, myEventsButton);
//		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
		
		add(contentLayoutFirstRow);
//		add(contentLayoutSecondRow);
		
		
	}

}
