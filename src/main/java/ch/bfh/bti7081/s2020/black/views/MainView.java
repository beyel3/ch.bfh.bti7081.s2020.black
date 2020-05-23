package ch.bfh.bti7081.s2020.black.views;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.presenters.HomeViewPresenter;
import ch.bfh.bti7081.s2020.black.presenters.SuperPresenter;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout{

	private static final long serialVersionUID = 1L;

	public MainView() {

		MainModel mainModel = new MainModel();

	    //SuperPresenter superPresenter = new SuperPresenter(this,mainModel);
        HomeViewPresenter homeViewPresenter = new HomeViewPresenter(this, mainModel);
    }
}
