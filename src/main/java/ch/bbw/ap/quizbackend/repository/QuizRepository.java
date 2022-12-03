package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository {

    public List<Quiz> getAllQuizes();
    public Quiz getQuizById(String id);
}
