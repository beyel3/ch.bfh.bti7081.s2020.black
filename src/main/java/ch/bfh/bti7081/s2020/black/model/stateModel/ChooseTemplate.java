package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;

public class ChooseTemplate extends StateModel {

	private Random random = new Random();

	public ArrayList<Tag> getTagsList() {

		try {
			return persistence.getTagList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public EventTemplate saveEventTemplate(String title, String description, Set<Tag> tagSet) {

		ArrayList<Tag> tagList = new ArrayList<>();
		for (Tag t : tagSet) {
			tagList.add(t);
		}

		EventTemplate et = new EventTemplate(title, description, tagList);

		try {
			Persistence persistence = new Persistence();
			persistence.executeUpdate("INSERT INTO tbl_eventTemplate VALUES (NULL, '" + et.getTitle() + "', '"
					+ et.getDescription() + "', '" + et.getAvgRating() + "')");
			ResultSet id = persistence.executeQuery("SELECT LAST_INSERT_ROWID()");
			et.setId(id.getInt(1));

			for (Tag t : tagList) {
				persistence.executeUpdate(
						"INSERT INTO tbl_tagEventTemplateREL(tagID,eventTemplateID) SELECT " + t.getId() + ", '"
								+ et.getId() + "' WHERE NOT EXISTS(SELECT 1 FROM tbl_tagEventTemplateREL WHERE tagID = "
								+ t.getId() + " AND eventTemplateID = " + et.getId() + ");");
			}

			// persistence.saveEventTemplate(et);
			return et;
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			return null;
		}
	}
}
