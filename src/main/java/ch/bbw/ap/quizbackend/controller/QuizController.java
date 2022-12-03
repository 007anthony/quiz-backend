package ch.bbw.ap.quizbackend.controller;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz/all")
    public List<Quiz> allQuizes() {
        return quizService.getQuizes();
    }

    @GetMapping("/quiz/{id}")
    public Quiz getQuiz(@PathVariable String id) {
        return quizService.getQuiz(id);
    }
}
