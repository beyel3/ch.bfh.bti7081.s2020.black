package ch.bfh.bti7081.s2020.black.presenters;

import java.sql.SQLException;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.stateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.views.EventTemplateViewImplementation;

public class EventTemplatePresenter extends Presenter {
		
		private ChooseTemplate chooseTemplateState;
	
		public EventTemplatePresenter(SuperPresenter superPresenter) {
		super(superPresenter);
		
		this.chooseTemplateState = new ChooseTemplate();
		superPresenter.setState(chooseTemplateState);

		
		ArrayList<EventTemplate> templates = chooseTemplateState.getEventTemplateList();

		
		this.currentView = new EventTemplateViewImplementation(this, templates);
		superPresenter.addView(currentView);

	}
		
}
