package ch.bbw.ap.quizbackend.model;

public class UserWithCredentials extends User{

    private String password;
    private String[] roles;

    public UserWithCredentials(String... roles) {
        super();
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }
}
