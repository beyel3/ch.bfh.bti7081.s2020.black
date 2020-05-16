package ch.bfh.bti7081.s2020.black.model.eventStateModel;

import java.sql.SQLException;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;
import ch.bfh.bti7081.s2020.black.presenters.Presenter;

public abstract class EventStateModel {
	
	protected Persistence persistence;
	protected EventTemplate eventTemplate;
	protected Event event;
	protected Presenter presenter;
	
	
	public EventStateModel() {
		
		try {
			persistence = new Persistence();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Tag> getTags() {
		
		try {
			return persistence.getTagList();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
