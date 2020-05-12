package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.views.MainViewImplementation;

public class MainPresenter {

	private Persistence persistence;
	private MainViewImplementation view;
	private EventPresenter eventPresenter;
	private AccountPresenter accountPresenter;

	public MainPresenter(MainModel model, MainViewImplementation view) {
		this.view = view;
		this.eventPresenter = new EventPresenter();
	}
}
