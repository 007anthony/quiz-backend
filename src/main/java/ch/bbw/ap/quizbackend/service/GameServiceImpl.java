package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.config.SecurityConfiguration;
import ch.bbw.ap.quizbackend.model.Answer;
import ch.bbw.ap.quizbackend.model.Game;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.repository.GameRepository;
import ch.bbw.ap.quizbackend.serializer.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GameServiceImpl implements GameService{
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game getGame(String id) {
        Document doc = gameRepository.findGameById(id);
        Game game = this.gson.fromJson(doc.toJson(), Game.class);
        return game;

    }

    @Override
    public Game createGame(Game game) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        game.setPlayer(user);
        Document document = Document.parse(gson.toJson(game));
        gameRepository.createGame(document);
        return game;
    }

    @Override
    public Game saveAnswer(String id, Answer answer) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = this.getGame(id);
        if(user != null && user.equals(game.getPlayer())) {
            return null;
        }
        Document oldDocument = Document.parse(gson.toJson(game));
        game.addAnswer(answer);
        Document newDocument = Document.parse(gson.toJson(game));
        gameRepository.editGame(oldDocument, newDocument);
        return game;
    }
}
