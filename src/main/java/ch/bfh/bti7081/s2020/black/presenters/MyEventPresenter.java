package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventViewInterface;

public class MyEventPresenter extends Presenter implements EventViewInterface {

	public MyEventPresenter(SuperPresenter superPresenter) {
		super(superPresenter);

		currentView = null;
		superPresenter.addPage(currentView);

	}

	@Override
	public void buttonClick(String action) {

		superPresenter.removePage(currentView);

		switch (action) {
		
		case "CHAT":
			new PostViewPresenter(superPresenter);
			break;
		case "DETAILS":
			new PostViewPresenter(superPresenter);
			break;

		}
	}
}
