package ch.bbw.ap.quizbackend.model;

import java.util.List;
import java.util.Map;

public class Game {
    private User player;
    private Quiz quiz;
    private List<Answer> answers;


    public Game() {
    }

    public Game(User player, Quiz quiz, List<Answer> answers) {
        this.player = player;
        this.quiz = quiz;
        this.answers = answers;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
}
