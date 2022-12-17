package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.QuizMongoDbConnector;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.Arrays;
import java.util.Iterator;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private QuizMongoDbConnector mongoConnector;

    @Override
    public FindIterable<Document> findAllUsers() {
        MongoCollection<Document> collection = mongoConnector.getCollection("user");
        return collection.find();
    }

    @Override
    public Document findUserById(String id) {
        MongoCollection<Document> collection = mongoConnector.getCollection("user");
        AggregateIterable<Document> aggregation = collection.aggregate(Arrays.asList(new Document("$match",
                new Document("_id",
                        new ObjectId(id)))));
        Iterator<Document> it = aggregation.iterator();
        return it.next();
    }

    @Override
    public Document findUserByUsername(String username) {
        MongoCollection<Document> collection = mongoConnector.getCollection("user");
        AggregateIterable<Document> aggregation = collection.aggregate(Arrays.asList(new Document("$match",
                new Document("username", username))));

        return aggregation.iterator().next();
    }

    @Override
    public void createUser(Document userDocument) {
        MongoCollection<Document> collection = mongoConnector.getCollection("user");
        collection.insertOne(userDocument);
    }

    @Override
    public void editUser(Document oldDocument, Document newDocument) {
        MongoCollection<Document> collection = mongoConnector.getCollection("user");
        collection.updateOne(oldDocument, newDocument);
    }

    @Override
    public void deleteUser(Document document) {
        MongoCollection<Document> collection = mongoConnector.getCollection("user");
        collection.deleteOne(document);

    }
}
