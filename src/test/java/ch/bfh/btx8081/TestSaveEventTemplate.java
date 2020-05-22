package ch.bfh.btx8081;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;
import ch.bfh.bti7081.s2020.black.model.eventStateModel.ChooseTemplate;
import ch.bfh.bti7081.s2020.black.persistence.Persistence;

public class TestSaveEventTemplate {

	@Test
	public void test() {
		
		ChooseTemplate chooseTempalte = new ChooseTemplate();
		EventTemplate template = chooseTempalte.saveNewEventTemplate("Jassen", "Lade dein Patient zu einer Jassrunde ein", chooseTempalte.getTagsList());
		Persistence persistence;
		ArrayList<Tag> tags;
		
		try {
			
			persistence = new Persistence();
			tags = persistence.getTagList();
			assertTrue(tags != null);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
