package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.QuizMongoDbConnector;
import ch.bbw.ap.quizbackend.model.Game;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Iterator;

@Repository
public class GameRepositoryImpl implements GameRepository {

    @Autowired
    private QuizMongoDbConnector mongoConnector;

    @Override
    public Document findGameById(String id) {
        MongoCollection<Document> collection = mongoConnector.getCollection("game");
        AggregateIterable<Document> aggregation = collection.aggregate(Arrays.asList(new Document("$match",
                new Document("_id",
                        new ObjectId(id)))));
        Iterator<Document> it = aggregation.iterator();
        return it.next();
    }

    @Override
    public void createGame(Document document) {
        MongoCollection<Document> collection = mongoConnector.getCollection("game");
        collection.insertOne(document);
    }
}
