package ch.bbw.ap.quizbackend.model;

import com.google.gson.annotations.Expose;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

public class User {
    @Expose
    private String username;

    @Expose
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
