package ch.bfh.bti7081.s2020.black.views;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2020.black.model.Coreuser;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;


public interface EventCreaterView {

	public void setTitle(String title);
	public void setDescription(String description);
	public void setTags(List<Tag> tags);
	public void setRating(double avgRating);

	interface EventCreaterViewListener {
		
		void saveEventWithTemplate(EventTemplate eventTemplate, boolean isPublic, int maxParicipants, List<Coreuser> participatns);
		void saveEventCreateNewEventTemplate(String title, String description, ArrayList<Tag> tags, boolean isPublic,
				int maxParicipants, List<Coreuser> participatns);
		
	}
	
	public void addListener(EventCreaterViewListener listener);
}
