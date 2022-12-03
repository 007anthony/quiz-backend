package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.QuizMongoDbConnector;
import ch.bbw.ap.quizbackend.model.Quiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class QuizRepositoryImpl implements QuizRepository {


    @Autowired
    private QuizMongoDbConnector mongoConnector;
    @Override
    public List<Quiz> getAllQuizes() {
        List<Quiz> result = new ArrayList<>();
            MongoCollection<Document> quizDocs = mongoConnector.getCollection("Quiz");
            FindIterable<Document> iterDoc = quizDocs.find();
            Iterator<Document> it = iterDoc.iterator();
            Gson gson = new GsonBuilder().create();
            while(it.hasNext()) {
                Document doc = it.next();
                Quiz quiz = gson.fromJson(doc.toJson(), Quiz.class);
                result.add(quiz);
            }
        return result;
    }

    @Override
    public Quiz getQuizById(String id) {
        return null;
    }
}
