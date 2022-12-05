package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.request.Paging;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository {

    public List<Quiz> findAll(Paging paging);
    public Quiz findQuizById(String id);
    
}
