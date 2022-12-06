package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.QuizMongoDbConnector;
import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.request.Paging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.bson.Document;

import javax.print.Doc;
import javax.swing.event.DocumentEvent;
import java.util.*;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    @Autowired
    private QuizMongoDbConnector mongoConnector;
    @Override
    public Iterator<Document> findAll(Paging paging) {
        List<Quiz> result = new ArrayList<>();
        MongoCollection<Document> quizDocs = mongoConnector.getCollection("quiz");
        if(paging.getRows() != null) {
            AggregateIterable<Document> iterDoc = quizDocs.aggregate(Arrays.asList(new Document("$skip", paging.getOffset()),
                    new Document("$limit", paging.getRows())));
            return iterDoc.iterator();

        }
        else {
            FindIterable<Document> iterDoc = quizDocs.find();
            return iterDoc.iterator();
        }

    }

    @Override
    public Document findQuizById(String id) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        AggregateIterable<Document> aggregation = collection.aggregate(Arrays.asList(new Document("$match",
                new Document("_id",
                        new ObjectId(id)))));
        Iterator<Document> it = aggregation.iterator();
        return it.next();
    }

    @Override
    public void createQuiz(Document document) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        collection.insertOne(document);
    }

    @Override
    public void deleteQuiz(Document document) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        collection.deleteOne(document);
    }

    @Override
    public void editQuiz(Document oldDocument, Document newDocument) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        collection.updateOne(oldDocument, newDocument);
    }
}
