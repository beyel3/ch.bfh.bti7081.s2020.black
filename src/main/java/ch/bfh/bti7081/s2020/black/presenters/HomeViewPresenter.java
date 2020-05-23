package ch.bfh.bti7081.s2020.black.presenters;

import ch.bfh.bti7081.s2020.black.model.stateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.model.stateModel.StateModel;
import ch.bfh.bti7081.s2020.black.views.HomeViewImplementation;
import ch.bfh.bti7081.s2020.black.views.MainView;

public class HomeViewPresenter extends Presenter {

	public HomeViewPresenter(SuperPresenter superPresenter) {
	
		super(superPresenter);
		currentView = new HomeViewImplementation(this);
		superPresenter.addView(currentView);
	}
	
	public void goToCreateEventState() {
		
		superPresenter.removeView(currentView);
		new EventTemplatePresenter(superPresenter);
		
	}
}
