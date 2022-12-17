package ch.bbw.ap.quizbackend.model;

public class UserWithCredentials extends User{

    private String password;

    public UserWithCredentials() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
