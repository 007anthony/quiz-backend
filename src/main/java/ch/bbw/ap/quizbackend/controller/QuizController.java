package ch.bbw.ap.quizbackend.controller;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.request.Paging;
import ch.bbw.ap.quizbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz/all")
    public List<Quiz> allQuizes(Paging paging) {
        return quizService.getQuizes(paging);
    }

    @GetMapping("/quiz/{id}")
    public Quiz getQuiz(@PathVariable String id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("/quiz")
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        User user = new User("test", "test@test.com");
        return quizService.createQuiz(quiz, user);
    }

    @DeleteMapping("/quiz/{id}")
    public Quiz deleteQuiz(@PathVariable String id) {
        return quizService.deleteQuiz(id);
    }

    @PutMapping("/quiz/{id}")
    public Map<String, Quiz> editQuiz(@RequestBody Quiz quiz) {
        return quizService.editQuiz(quiz);
    }
}
