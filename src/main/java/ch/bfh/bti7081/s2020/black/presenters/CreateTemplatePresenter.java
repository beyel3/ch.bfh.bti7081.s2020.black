package ch.bfh.bti7081.s2020.black.presenters;

import java.util.Collection;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.model.eventStateModel.ChooseTemplate;

public class CreateTemplatePresenter extends Presenter {
	
	private ChooseTemplate model;
	
	public CreateTemplatePresenter() {
		this.model = new ChooseTemplate();
	}

	public Collection<Tag> getTags() {
		return model.getTagsList();
	}

	public EventTemplate getSavedTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveEventTemplate(String title, String description, Set<Tag> tags) {
		
//		model.saveNewEventTemplate(title, description, tags);

	}

}
