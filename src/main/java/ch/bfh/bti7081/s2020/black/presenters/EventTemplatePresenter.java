package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.stateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.views.EventTemplateViewImplementation;

public class EventTemplatePresenter extends Presenter implements EventTemplateInterface {
		
		private ChooseTemplate chooseTemplateState;
	
		public EventTemplatePresenter(SuperPresenter superPresenter) {
		super(superPresenter);
		
		this.chooseTemplateState = new ChooseTemplate();
		superPresenter.setState(chooseTemplateState);

		
		ArrayList<EventTemplate> templates = chooseTemplateState.getEventTemplateList();

		
		this.currentView = new EventTemplateViewImplementation(this, templates);
		superPresenter.addPage(currentView);

	}

		@Override
		public void buttonClick(EventTemplate eventTemplate) {
			superPresenter.removePage(currentView);
			
			new CreateEventPresenter(superPresenter, eventTemplate);
			
			
		}
		
}
