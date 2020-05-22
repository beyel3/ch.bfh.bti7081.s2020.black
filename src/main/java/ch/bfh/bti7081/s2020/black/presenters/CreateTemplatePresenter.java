package ch.bfh.bti7081.s2020.black.presenters;

<<<<<<< Updated upstream
import java.lang.reflect.Array;
import java.sql.SQLException;
=======
>>>>>>> Stashed changes
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

	public ArrayList<Tag> getTags() {
		return model.getTagsList();
	}

	public EventTemplate getSavedTemplate() {
		return null;
	}

<<<<<<< Updated upstream
	public EventTemplate saveEventTemplate(String title, String description, Set<Tag> tags){
=======
	public EventTemplate saveEventTemplate(String title, String description, Set<Tag> tagSet) {
>>>>>>> Stashed changes
		
		ArrayList<Tag> tagList = new ArrayList<>();
		for(Tag t : tagSet) {
			tagList.add(t);
		}
		
		return model.saveNewEventTemplate(title, description, tagList);

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
