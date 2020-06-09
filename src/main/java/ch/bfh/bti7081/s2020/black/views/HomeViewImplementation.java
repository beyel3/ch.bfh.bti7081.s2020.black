package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import ch.bfh.bti7081.s2020.black.interfaces.HomeViewInterface;

public class HomeViewImplementation<T extends HomeViewInterface> extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	private final HorizontalLayout contentLayoutFirstRow;
	private final HorizontalLayout contentLayoutSecondRow;
	private final HorizontalLayout contentLayoutThirdRow;
	private T presenter;

	public HomeViewImplementation(T presenter){
		this.presenter = presenter;
		
		setSizeFull();
				
		contentLayoutFirstRow = new HorizontalLayout();
		contentLayoutFirstRow.setWidth("100%");
		contentLayoutFirstRow.setHeight("33%");

		contentLayoutSecondRow = new HorizontalLayout();
		contentLayoutSecondRow.setWidth("100%");
		contentLayoutSecondRow.setHeight("33%");
		
		contentLayoutThirdRow = new HorizontalLayout();
		contentLayoutThirdRow.setWidth("100%");
		contentLayoutThirdRow.setHeight("33%");
		
		setFlexGrow(1, contentLayoutFirstRow, contentLayoutSecondRow, contentLayoutThirdRow);
		
		
		List<Button> buttons = new ArrayList<Button>();
		
		Button createEventButton = new Button("CREATE EVENT");
		createEventButton.addClickListener(event -> 
		presenter.buttonClick(HomeViewInterface.HomeAction.CREATEEVENT));
			 		
		Button searchOpenPublicEventButton = new Button("JOIN PUBLIC EVENT");
		searchOpenPublicEventButton.addClickListener(event -> 
		presenter.buttonClick(HomeViewInterface.HomeAction.JOINPUBLICEVENT));

		Button myEventsButton = new Button("MY EVENTS");
		myEventsButton.addClickListener(event -> 
		presenter.buttonClick(HomeViewInterface.HomeAction.MYEVENTS));
		
		buttons.add(myEventsButton);
		buttons.add(searchOpenPublicEventButton);
		buttons.add(createEventButton);
		
		for(Button b : buttons) {
			b.getStyle().set("height", "150px");
			b.getStyle().set("width", "40%");
			b.getStyle().set("font-size", "20px");
			b.getStyle().set("font-size", "2vw");	
		}

		contentLayoutFirstRow.add(createEventButton);
		contentLayoutFirstRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutSecondRow.add(searchOpenPublicEventButton);
		contentLayoutSecondRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		contentLayoutThirdRow.add(myEventsButton);
		contentLayoutThirdRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		
		add(contentLayoutFirstRow,contentLayoutSecondRow, contentLayoutThirdRow);

	}
	
}
