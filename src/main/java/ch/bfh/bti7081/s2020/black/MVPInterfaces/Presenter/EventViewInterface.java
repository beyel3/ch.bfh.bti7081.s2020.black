package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

public interface EventViewInterface {
	
	public void buttonClick(EventAction action);
    public enum EventAction{
        CHAT,
        DETAILS,
        MARKDONE
    }
	

}
