package ch.bfh.bti7081.s2020.black.presenters;


import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.model.stateModel.CreateTemplate;

public class CreateTemplatePresenter extends Presenter {
	
	private CreateTemplate createTemplateState;
	
	public CreateTemplatePresenter(SuperPresenter superPresenter) {
		super(superPresenter);
		
	}
	
	public ArrayList<Tag> getTags() {
		return createTemplateState.getTagList();
	}

	public EventTemplate getSavedTemplate() {
		return null;
	}

	
}
