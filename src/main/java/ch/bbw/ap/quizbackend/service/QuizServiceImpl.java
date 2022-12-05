package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.request.Paging;
import ch.bbw.ap.quizbackend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz getQuiz(String id) {
        return quizRepository.findQuizById(id);
    }

    @Override
    public List<Quiz> getQuizes(Paging paging) {
        return quizRepository.findAll(paging);
    }

    @Override
    public Quiz createQuiz(Quiz quiz, User user) {
        quiz.setCreatedAt(LocalDate.now());
        quiz.setCreatedBy(user);
        return quizRepository.createQuiz(quiz);
    }

    @Override
    public Quiz deleteQuiz(String id) {
        return quizRepository.deleteQuiz(id);
    }

    @Override
    public Map<String, Quiz> editQuiz(String id, Quiz quiz) {
        return quizRepository.editQuiz(id, quiz);
    }

}
