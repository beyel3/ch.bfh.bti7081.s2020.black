package ch.bfh.bti7081.s2020.black.presenters;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.MainModel;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.model.stateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.views.MainView;

public class CreateTemplatePresenter extends Presenter {
	
	public CreateTemplatePresenter(SuperPresenter superPresenter) {
		super(superPresenter);
		
	}

	private ChooseTemplate model;
	


	public ArrayList<Tag> getTags() {
		return model.getTagsList();
	}

	public EventTemplate getSavedTemplate() {
		return null;
	}

	
}
