package ch.bbw.ap.quizbackend.controller;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.request.Paging;
import ch.bbw.ap.quizbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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

    @PostMapping(value = "/quiz", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @DeleteMapping("/quiz/{id}")
    public Quiz deleteQuiz(@PathVariable String id) {
        return quizService.deleteQuiz(id);
    }

    @PutMapping("/quiz/{id}")
    public Map<String, Quiz> editQuiz(@PathVariable String id, @RequestBody Quiz quiz) {
        return quizService.editQuiz(id, quiz);
    }
}
