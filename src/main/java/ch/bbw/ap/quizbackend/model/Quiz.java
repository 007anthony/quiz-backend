package ch.bbw.ap.quizbackend.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public class Quiz {

    private String name;
    private User createdBy;
    private LocalDate creationDate;
    private List<Question> questions;

    public Quiz() {
        this(null, null, null, null);
    }

    public Quiz(String name, User createdBy, LocalDate creationDate, List<Question> questions) {
        this.name = name;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.questions = questions;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
