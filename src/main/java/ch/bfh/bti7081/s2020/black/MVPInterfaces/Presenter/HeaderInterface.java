package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

public interface HeaderInterface {
    public void buttonClick(HeaderAction action);
    public enum HeaderAction{
        LOGIN,
        SIGNUP,
        HOME,
        CREATEEVENT,
        JOINPUBLICEVENT,
        MYEVENTS
    }
}
