package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.ButtonInterface;
import ch.bfh.bti7081.s2020.black.views.HomeViewImplementation;

public class HomeViewPresenter extends Presenter implements ButtonInterface {

	public HomeViewPresenter(SuperPresenter superPresenter) {
	
		super(superPresenter);
		currentView = new HomeViewImplementation(this);
		superPresenter.addView(currentView);
	}

	@Override
	public void buttonClick(String information) {
		superPresenter.removeView(currentView);
		new EventTemplatePresenter(superPresenter);
	}
}
