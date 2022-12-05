package ch.bbw.ap.quizbackend.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Question {
    @Expose
    private String question;
    @Expose
    private List<String> answers;

    public Question(String question) {
        this.question = question;
        this.answers = new ArrayList<>();
    }

    public Question(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
