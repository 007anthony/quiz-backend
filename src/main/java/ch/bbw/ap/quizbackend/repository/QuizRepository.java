package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.request.Paging;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuizRepository {

    public List<Quiz> findAll(Paging paging);
    public Quiz findQuizById(String id);
    public Quiz createQuiz(Quiz quiz);

    public Quiz deleteQuiz(String id);
    public Map<String, Quiz> editQuiz(String id, Quiz quiz);
    
}
