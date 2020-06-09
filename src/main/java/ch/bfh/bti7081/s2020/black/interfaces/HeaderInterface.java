package ch.bfh.bti7081.s2020.black.interfaces;

public interface HeaderInterface {
    public void buttonClick(HeaderAction action);
    public enum HeaderAction{
        LOGOUT,
        ADMIN,
        HOME,
        CREATEEVENT,
        JOINPUBLICEVENT,
        MYEVENTS
    }
}
