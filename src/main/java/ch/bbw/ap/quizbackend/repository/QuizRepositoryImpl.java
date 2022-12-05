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
import java.util.*;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    private final Gson gson = new GsonBuilder().create();


    @Autowired
    private QuizMongoDbConnector mongoConnector;
    @Override
    public List<Quiz> findAll(Paging paging) {
        List<Quiz> result = new ArrayList<>();
            MongoCollection<Document> quizDocs = mongoConnector.getCollection("quiz");
            AggregateIterable<Document> iterDoc = null;
            if(paging.getRows() != null) {
                iterDoc = quizDocs.aggregate(Arrays.asList(new Document("$skip", paging.getOffset()),
                        new Document("$limit", paging.getRows())));
            }
            else {
                iterDoc = (AggregateIterable<Document>) quizDocs.find();
            }
            Iterator<Document> it = iterDoc.iterator();
            while(it.hasNext()) {
                Document doc = it.next();
                Quiz quiz = this.gson.fromJson(doc.toJson(), Quiz.class);
                quiz.setCreatedAt(doc.getDate("creationDate"));
                result.add(quiz);
            }
        return result;
    }

    @Override
    public Quiz findQuizById(String id) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        AggregateIterable<Document> aggregation = collection.aggregate(Arrays.asList(new Document("$match",
                new Document("_id",
                        new ObjectId(id)))));
        Iterator<Document> it = aggregation.iterator();
        Document doc = it.next();
        return this.gson.fromJson(doc.toJson(), Quiz.class);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        Document document = Document.parse(gson.toJson(quiz));
        collection.insertOne(document);
        return quiz;
    }

    @Override
    public Quiz deleteQuiz(String id) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        Quiz quiz = this.findQuizById(id);
        collection.deleteOne(Document.parse(gson.toJson(quiz)));
        return quiz;
    }

    @Override
    public Map<String, Quiz> editQuiz(String id, Quiz newQuiz) {
        MongoCollection<Document> collection = mongoConnector.getCollection("quiz");
        Quiz oldQuiz = this.findQuizById(id);

        collection.updateOne(Document.parse(gson.toJson(oldQuiz)), Document.parse(gson.toJson(newQuiz)));
        return Map.of("oldQuiz", oldQuiz, "newQuiz", newQuiz);
    }
}
