package ch.bfh.bti7081.s2020.black.model.stateModel;

import ch.bfh.bti7081.s2020.black.model.Event;

public class CloseEvent extends StateModel {

	public void savePicture(byte [] picture) {
		persistence.executeUpdate("INSERT INTO tbl_image (image)" +
				" VALUES ("+picture+")");
	}
	
	public void setEventRating(Event event) {
		
	}
}
