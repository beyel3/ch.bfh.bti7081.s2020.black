package ch.bfh.bti7081.s2020.black.model.eventStateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class ChooseTemplate extends EventStateModel {

	
	public ArrayList<EventTemplate> getEventTemplates() {
		
		
		Random random = new Random();
		
		
		
		try {
			
			ArrayList<EventTemplate> eventTemplates = new ArrayList<EventTemplate>();
			ResultSet rs = persistence.executeQuery("SELECT * FROM tbl_eventTemplate");


			
			while(rs.next()) {
				
				int id = rs.getInt("eventTemplateID");
				String title = rs.getString("title");
				String description= rs.getString("description");
				ArrayList<Tag> tags = new ArrayList<Tag>();
				
				ResultSet getTags = persistence.executeQuery("SELECT * FROM tbl_tag, tbl_tagEventTemplateREL");
				
				while(getTags.next()) {
					tags.add(new Tag(getTags.getInt("tagID"), getTags.getString("tag_name")));
				}
				
				eventTemplates.add(new EventTemplate(id,title,description,tags, null,random.nextInt(100)/10));
			}
			
			return eventTemplates;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Tag> getTagsList() {
		
		try {
			
			ArrayList<Tag> tags = new ArrayList<Tag>();
			ResultSet getTags = persistence.executeQuery("SELECT * FROM tbl_tag");
			
			while(getTags.next()) {
				tags.add(new Tag(getTags.getInt("tagID"), getTags.getString("tag_name")));
			}
			return tags;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveNewEventTemplate(String title, String description, Set<Tag> tags) {
		
		
		persistence.executeQuery("insert into tbl_eventTemplate values()");
		
	}


}
