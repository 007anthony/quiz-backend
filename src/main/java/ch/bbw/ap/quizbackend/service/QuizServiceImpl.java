package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz getQuiz(String id) {
        return quizRepository.findQuizById(id);
    }

    @Override
    public List<Quiz> getQuizes() {
        return quizRepository.findAll();
    }
}
