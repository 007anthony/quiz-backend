package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.request.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    public Quiz getQuiz(String id);
    public List<Quiz> getQuizes(Paging paging);

}
