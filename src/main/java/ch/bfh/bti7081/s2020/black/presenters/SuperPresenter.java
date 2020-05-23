package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.views.HeaderView;
import ch.bfh.bti7081.s2020.black.views.HomeViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainView;
import com.vaadin.flow.component.Component;
import sun.applet.Main;

public class SuperPresenter {

	protected MainView mainView;
	protected MainModel mainModel;

	public SuperPresenter() { }

	public SuperPresenter(MainView mainView, MainModel mainModel) {
		this.mainView = mainView;
		this.mainModel = mainModel;
	}
}
