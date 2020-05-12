package ch.bfh.bti7081.s2020.black.views;

import java.sql.SQLException;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.bti7081.s2020.black.model.HardCoded;
import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.presenters.MainPresenter;


@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout implements RouterLayout{
	

	public static HardCoded hardCoded = new HardCoded();
	
	private static final long serialVersionUID = 1L;

	public MainView() {
//		Persistence persistance;
//		try {
//			persistance = new Persistence();
//
//			persistance.saveEventTemplate(hardCoded.getEventTemplateFromID(1));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	MainModel model = new MainModel();
    	MainViewImplementation view = new MainViewImplementation();
    	
    	new MainPresenter(model, view);
        add(view);
        
    }
}
