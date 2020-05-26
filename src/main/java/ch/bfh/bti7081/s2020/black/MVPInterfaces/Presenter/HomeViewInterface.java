package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

public interface HomeViewInterface {
    public void buttonClick(HomeAction action);
    public enum HomeAction{
        CREATEEVENT,
        JOINPUBLICEVENT,
        MYEVENTS
    }
}
