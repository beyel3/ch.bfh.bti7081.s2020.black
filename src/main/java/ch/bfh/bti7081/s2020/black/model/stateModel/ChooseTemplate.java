package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class ChooseTemplate extends StateModel {
	
	public ArrayList<Tag> getTagList() {
		ArrayList<Tag> tags = new ArrayList<Tag>();

		try {
			ResultSet tagResult = persistence.executeQuery("SELECT * FROM tbl_tag");
			while (tagResult.next()) {
				Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
				tags.add(t);
			}
			return tags;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public EventTemplate saveEventTemplate(String title, String description, ArrayList<Tag> tags) {

		EventTemplate et = new EventTemplate(title, description, tags);
		try {

			persistence.executeUpdate("INSERT INTO tbl_eventTemplate VALUES (NULL, '" + et.getTitle() + "', '"
					+ et.getDescription() + "', '" + et.getAvgRating() + "')");
			ResultSet id = persistence.executeQuery("SELECT LAST_INSERT_ROWID()");
			et.setId(id.getInt(1));

			for (Tag t : tags) {
				persistence.executeUpdate(
						"INSERT INTO tbl_tagEventTemplateREL(tagID,eventTemplateID) SELECT " + t.getId() + ", '"
								+ et.getId() + "' WHERE NOT EXISTS(SELECT 1 FROM tbl_tagEventTemplateREL WHERE tagID = "
								+ t.getId() + " AND eventTemplateID = " + et.getId() + ");");
			}

			return et;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
