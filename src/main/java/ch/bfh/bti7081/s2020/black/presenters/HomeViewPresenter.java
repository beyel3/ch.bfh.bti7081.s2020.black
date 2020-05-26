package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.HomeViewInterface;
import ch.bfh.bti7081.s2020.black.views.HomeViewImplementation;

public class HomeViewPresenter extends Presenter implements HomeViewInterface {

	public HomeViewPresenter(SuperPresenter superPresenter) {

		super(superPresenter);
		currentView = new HomeViewImplementation(this);
		superPresenter.addPage(currentView);
	}

	@Override
	public void buttonClick(HomeAction action) {

		superPresenter.removePage(currentView);

		switch (action) {

		case CREATEEVENT:
			new EventTemplatePresenter(superPresenter);
			break;
		case JOINPUBLICEVENT:
			break;
		case MYEVENTS:
			new MyEventPresenter(superPresenter);
			break;
		}
	}
}
