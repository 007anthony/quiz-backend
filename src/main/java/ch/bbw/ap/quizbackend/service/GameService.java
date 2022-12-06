package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    public Game getGame(String id);
    public Game createGame(Game game);
}
