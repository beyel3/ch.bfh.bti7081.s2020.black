package ch.bfh.bti7081.s2020.black.MVPInterfaces.Presenter;

public interface SignUpInterface {
    public void submit(String firstName,
                       String lastName,
                       String email,
                       String password,
                       String confirmPassword,
                       String accountType);
    public void logIn();
}
