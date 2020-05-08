package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.presenters.AccountPresenter;
import ch.bfh.bti7081.s2020.black.presenters.EventPresenter;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout {
	
	public static final EventPresenter eventPresenter =  new EventPresenter();
	private final VerticalLayout contentLayout;
    public MainView() {
       			     	
        Button createEventButton = new Button("Create Event");
        createEventButton.addClickListener(event -> 
        		getUI().ifPresent(ui ->  ui.navigate("EventCreaterView/")));
        
        Button browseEventButton = new Button("Create From Template Eventtemplates");
        browseEventButton.addClickListener(event -> 
        		browseEventButton.getUI().ifPresent(ui ->  ui.navigate("EventCreaterView/CreatedFromTemplate")));
        
        add(createEventButton, browseEventButton);
 
    }
    


}
