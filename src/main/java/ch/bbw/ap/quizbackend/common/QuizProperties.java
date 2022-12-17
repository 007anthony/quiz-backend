package ch.bbw.ap.quizbackend.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ch.bbw.ap.quiz-backend")
public class QuizProperties {

    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
