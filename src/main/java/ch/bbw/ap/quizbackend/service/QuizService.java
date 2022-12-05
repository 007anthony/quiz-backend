package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.request.Paging;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface QuizService {

    public Quiz getQuiz(String id);
    public List<Quiz> getQuizes(Paging paging);

    public Quiz createQuiz(Quiz quiz, User user);
    public Quiz deleteQuiz(String id);
    public Map<String, Quiz> editQuiz(String id, Quiz quiz);
}
