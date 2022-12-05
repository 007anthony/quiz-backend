package ch.bbw.ap.quizbackend.controller;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.request.Paging;
import ch.bbw.ap.quizbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
