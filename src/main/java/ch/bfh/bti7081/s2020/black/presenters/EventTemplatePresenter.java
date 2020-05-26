package ch.bfh.bti7081.s2020.black.presenters;

import java.util.ArrayList;
import java.util.Set;

import com.vaadin.flow.component.dialog.Dialog;

import ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter.EventTemplateInterface;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.model.stateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.views.CreateTemplateViewImplementation;
import ch.bfh.bti7081.s2020.black.views.EventTemplateViewImplementation;

public class EventTemplatePresenter extends Presenter implements EventTemplateInterface {
		
		private ChooseTemplate chooseTemplateState;
		private Dialog dialogCreateEvent;
		
		public EventTemplatePresenter(SuperPresenter superPresenter) {
		super(superPresenter);
		
		this.chooseTemplateState = new ChooseTemplate();
		superPresenter.setState(chooseTemplateState);

		
		ArrayList<EventTemplate> templates = chooseTemplateState.getEventTemplateList();

		
		this.currentView = new EventTemplateViewImplementation(this);
		superPresenter.addPage(currentView);
		
		dialogCreateEvent = new Dialog();
		dialogCreateEvent.add(new CreateTemplateViewImplementation(this));
		superPresenter.addPage(dialogCreateEvent);
	}

		@Override
		public void buttonClick(EventTemplate eventTemplate) {
			superPresenter.removePage(currentView);
			
			new CreateEventPresenter(superPresenter, eventTemplate);
			
			
		}

		@Override
		public void createTempalte() {
			dialogCreateEvent.open();
		}

		@Override
		public void submit(String title, String description, Set<Tag> tagSet) {
			
			ArrayList<Tag> tagList = new ArrayList<>();
			for (Tag t : tagSet) {
				tagList.add(t);
			}
			chooseTemplateState.saveEventTemplate(title, description, tagList);
		}

		@Override
		public ArrayList<Tag> getTags() {
			return chooseTemplateState.getTagList();
		}

		@Override
		public ArrayList<EventTemplate> getEventTemplates() {
			return chooseTemplateState.getEventTemplateList();
		}
		
}
