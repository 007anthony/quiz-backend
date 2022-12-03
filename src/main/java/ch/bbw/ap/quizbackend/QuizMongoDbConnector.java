package ch.bbw.ap.quizbackend;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class QuizMongoDbConnector {

    @Autowired
    private MongoProperties mongoProperties;

    public MongoCollection<Document> getCollection(String collectionName) {
        if(mongoProperties.getUri() == null) {
            mongoProperties.setUri("mongodb://" + mongoProperties.getUsername() + ":" + mongoProperties.getPassword()
            + "@" + mongoProperties.getHost() + ":" + mongoProperties.getPort() + "/" + mongoProperties.getDatabase());
        }

        try(MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder().applyConnectionString(
                        new ConnectionString(mongoProperties.getUri())).build()
        )) {
            MongoDatabase db = mongoClient.getDatabase(mongoProperties.getDatabase());
            return db.getCollection(collectionName);
        } catch (MongoException e) {
            System.err.println("Something went wrong with the MongoDB Connection");
            return null;
        }
    }
}
