package ch.bbw.ap.quizbackend.controller;

import ch.bbw.ap.quizbackend.model.Answer;
import ch.bbw.ap.quizbackend.model.Game;
import ch.bbw.ap.quizbackend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;
    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable String id) {
        return gameService.getGame(id);
    }
    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PostMapping("/game/<id>")
    public Game saveAnswer(@PathVariable String id, @RequestBody Answer answer) {
        return gameService.saveAnswer(id, answer);
    }

}
