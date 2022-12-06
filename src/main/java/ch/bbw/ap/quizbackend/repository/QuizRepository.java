package ch.bbw.ap.quizbackend.repository;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.request.Paging;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public interface QuizRepository {

    public Iterator<Document> findAll(Paging paging);
    public Document findQuizById(String id);
    public void createQuiz(Document document);

    public void deleteQuiz(Document document);
    public void editQuiz(Document oldDocument, Document newDocument);
    
}
