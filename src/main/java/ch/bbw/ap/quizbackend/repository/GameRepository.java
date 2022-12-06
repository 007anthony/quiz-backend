package ch.bbw.ap.quizbackend.repository;

import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository {

    public Document findGameById(String id);
    public void createGame(Document document);
}
