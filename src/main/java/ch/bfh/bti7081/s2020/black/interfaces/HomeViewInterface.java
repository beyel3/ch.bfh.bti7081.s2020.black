package ch.bfh.bti7081.s2020.black.interfaces;

public interface HomeViewInterface {

	public void buttonClick(HomeAction action);

	public enum HomeAction {
		CREATEEVENT, JOINPUBLICEVENT, MYEVENTS
	}
}
