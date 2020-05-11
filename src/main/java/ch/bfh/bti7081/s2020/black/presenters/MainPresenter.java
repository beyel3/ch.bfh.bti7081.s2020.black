package ch.bfh.bti7081.s2020.black.presenters;

import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.RouterLayout;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.views.EventCreaterViewImplementaion;
import ch.bfh.bti7081.s2020.black.views.BrwoseEventTemplatesViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainViewInterface;

public class MainPresenter {

	private Persistence persistence;
	private MainViewImplementation view;
	private EventPresenter eventPresenter;
	private AccountPresenter accountPresenter;

	public MainPresenter(MainModel model, MainViewImplementation view) {
		this.view = view;
		this.eventPresenter = new EventPresenter(this);
	}
}
