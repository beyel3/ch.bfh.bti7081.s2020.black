package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

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
    	
    	contentLayout = new VerticalLayout();
		final FormLayout mainFormLayout = new FormLayout();
		
        Button createEventButton = new Button("Create Event");
        createEventButton.addClickListener(event -> 
        		getUI().ifPresent(ui ->  ui.navigate("EventCreaterView/")));
        
        Button browseEventButton = new Button("Create From Template Eventtemplates");
        browseEventButton.addClickListener(event -> 
        		browseEventButton.getUI().ifPresent(ui ->  ui.navigate("EventCreaterView/CreatedFromTemplate")));
        
		contentLayout.setAlignItems(Alignment.CENTER);
		contentLayout.setAlignSelf(Alignment.END);       
        mainFormLayout.add(createEventButton, browseEventButton);
 
		final Div header = new Div();
		header.setText("Test");
		header.setClassName("header");
		header.setHeight("50px");

		contentLayout.add(mainFormLayout);
		add(header);
		add(contentLayout);
        
    }
    


}
