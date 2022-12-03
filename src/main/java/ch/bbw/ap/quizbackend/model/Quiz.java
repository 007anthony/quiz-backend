package ch.bbw.ap.quizbackend.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Quiz {

    private String name;
    private User createdBy;
    private LocalDate creationDate;

    public Quiz() {
        this(null, null, null);
    }

    public Quiz(String name, User createdBy, LocalDate creationDate) {
        this.name = name;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
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
}
