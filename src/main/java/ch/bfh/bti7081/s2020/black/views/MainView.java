package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.presenters.MainPresenter;


@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
public class MainView extends VerticalLayout implements RouterLayout{
	
    public MainView() {
       			     	
    	MainModel model = new MainModel();
    	MainViewImplementation view = new MainViewImplementation();
    	
    	new MainPresenter(model, view);
        add(view);
 
    }
}
