package ch.bfh.bti7081.s2020.black.MVPInterfaces;

public interface LoginInterface {
    interface LoginViewInterface{
        public void dialogMessage(String message);
        public void route();
    }
    interface LoginPresenterInterface{
        public void submit(String email, String password);
    }
}
