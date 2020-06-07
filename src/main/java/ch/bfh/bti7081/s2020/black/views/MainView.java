package ch.bfh.bti7081.s2020.black.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.bti7081.s2020.black.presenters.CloseEventPresenter;
import ch.bfh.bti7081.s2020.black.presenters.SuperPresenter;


@Route("")
@PWA(name = "MainPage", shortName = "MainPage")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@BodySize
public class MainView extends VerticalLayout implements RouterLayout, PageConfigurator {

	private static final long serialVersionUID = 1L;
	
	public MainView() {

		add(new CloseEventViewImplementation(new CloseEventPresenter(new SuperPresenter(this))));
//		

    }
    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addInlineWithContents("body {min-height:100vh;}",
                InitialPageSettings.WrapMode.STYLESHEET);
    }
}
