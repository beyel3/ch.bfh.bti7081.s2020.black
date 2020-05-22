package ch.bfh.bti7081.s2020.black.presenters;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.model.eventStateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;

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

	public EventTemplate saveEventTemplate(String title, String description, Set<Tag> tags){
		
//		model.saveNewEventTemplate(title, description, tags);

		ArrayList<Tag> taglist = new ArrayList<Tag>(tags);
		EventTemplate et = new EventTemplate(title, description, taglist, null,0);
		try {
			Persistence persistence = new Persistence();
			persistence.saveEventTemplate(et);
			return et;
		}
		catch (ClassNotFoundException | SQLException e){
			System.err.println(e);
			return null;
		}
	}

}
