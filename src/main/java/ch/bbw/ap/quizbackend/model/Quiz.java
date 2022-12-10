package ch.bbw.ap.quizbackend.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.gson.annotations.Expose;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Quiz {
    @Expose
    private String name;
    @Expose
    private User createdBy;
    private LocalDate createdOn;
    @Expose
    private List<Question> questions;

    public Quiz() {
        this(null, null, null, null);
    }

    public Quiz(String name, User createdBy, LocalDate createdOn, List<Question> questions) {
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

    public LocalDate getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
