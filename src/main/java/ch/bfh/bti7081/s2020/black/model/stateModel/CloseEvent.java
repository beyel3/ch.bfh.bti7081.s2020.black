package ch.bfh.bti7081.s2020.black.model.stateModel;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialException;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;

public class CloseEvent extends StateModel {

	public void savePicture(byte[] picture) throws SerialException, SQLException {
//		Blob blob = new javax.sql.rowset.serial.SerialBlob(picture);
//	    insertStatement_logo.bindBlob(2, picture);
		persistence.executePreparedStatement(picture);
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
