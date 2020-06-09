package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialException;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;

import java.util.ArrayList;

public class CloseEvent extends StateModel {

	public void savePicture(byte[] picture, Event event) throws SerialException, SQLException {
		persistence.executePreparedStatement(picture, event);
	}

	public void setEventRating(Event event) {
		persistence.executeUpdate(
				"UPDATE tbl_event SET rating = " + event.getRating() + " WHERE eventID = " + event.getId());
	}

	public void setEventStatus(Event event) {
		persistence.executeUpdate(
				"UPDATE tbl_event SET state = '" + event.getStatus() + "' WHERE eventID = " + event.getId());

	}

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
