package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialException;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;

public class CloseEvent extends StateModel {

	// Save image to DB
	public void savePicture(byte[] picture, Event event) throws SerialException, SQLException {
		
		String sql = "INSERT INTO tbl_image (eventID, image) VALUES (?,?)";
		
		PreparedStatement stm = persistence.getPreparedStatement(sql);
		
		stm.setInt(1, event.getId());
		stm.setBytes(2, picture);
		
		stm.executeUpdate();
		stm.close();
	}


	// Set rating of the done event
	public void setEventRating(Event event) {
		persistence.executeUpdate(
				"UPDATE tbl_event SET rating = " + event.getRating() + " WHERE eventID = " + event.getId());
	}

	// Set status for the done event
	public void setEventStatus(Event event) {
		persistence.executeUpdate(
				"UPDATE tbl_event SET state = '" + event.getStatus() + "' WHERE eventID = " + event.getId());

	}

	// Calculate the average rating of an event template
	public void updateAvgRating(EventTemplate eventTemplate) {
		ArrayList<Event> events = getEventListByTemplate(eventTemplate);

		double count = 0;
		double sum = 0;
		
		for (Event e : events) {
			if (e.getRating() != 0) {
				sum += e.getRating();
				count++;
			}
		}
		double avgRating = sum/count;

		persistence.executeUpdate(
				"UPDATE tbl_eventTemplate SET rating = '" + avgRating  + "' WHERE eventTemplateID = " + eventTemplate.getId());

	}

}
