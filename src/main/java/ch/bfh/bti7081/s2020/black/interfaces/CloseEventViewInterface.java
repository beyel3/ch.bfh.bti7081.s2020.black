package ch.bfh.bti7081.s2020.black.interfaces;

import ch.bfh.bti7081.s2020.black.model.Event;

public interface CloseEventViewInterface {
	
	public Event getEvent();
	public void closeEvent(byte[] picture, int rating);
}
