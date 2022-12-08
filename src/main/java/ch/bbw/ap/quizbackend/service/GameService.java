package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.Answer;
import ch.bbw.ap.quizbackend.model.Game;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GameService {

    public Game getGame(String id);
    public Game createGame(Game game);
    public Game saveAnswer(String id, Answer answer);
}
