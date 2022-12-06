package ch.bbw.ap.quizbackend.controller;

import ch.bbw.ap.quizbackend.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class GameController {


    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable String id) {
        return null;
    }

}
