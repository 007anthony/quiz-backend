package ch.bbw.ap.quizbackend.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Quiz {

    private String name;
    private User createdBy;
    private Date createdOn;
    private List<Question> questions;

    public Quiz() {
        this(null, null, null, null);
    }

    public Quiz(String name, User createdBy, Date createdOn, List<Question> questions) {
        this.name = name;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
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

    public Date getCreatedAt() {
        return createdOn;
    }

    public void setCreatedAt(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
