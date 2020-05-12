package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.presenters.MainPresenter;


@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout implements RouterLayout{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainView() {
       			     	
    	MainModel model = new MainModel();
    	MainViewImplementation view = new MainViewImplementation();
    	
    	new MainPresenter(model, view);
        add(view);
        
    }
}
