package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

import ch.bfh.bti7081.s2020.black.model.Event;

public interface CloseEventViewInterface {
	public Event getEvent();
	public void closeEvent(byte [] picture, int rating);
}
