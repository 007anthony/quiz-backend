package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.Quiz;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.request.Paging;
import ch.bbw.ap.quizbackend.repository.QuizRepository;
import ch.bbw.ap.quizbackend.serializer.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class QuizServiceImpl implements QuizService {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).setDateFormat("yyy-MM-dd").create();

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz getQuiz(String id) {
        Document doc = quizRepository.findQuizById(id);

        Quiz quiz = this.gson.fromJson(doc.toJson(), Quiz.class);
        return quiz;
    }

    @Override
    public List<Quiz> getQuizes(Paging paging) {
        List<Quiz> result = new ArrayList<>();
        Iterator<Document> it =  quizRepository.findAll(paging);
        while(it.hasNext()) {
            Document doc = it.next();
            Quiz quiz = this.gson.fromJson(doc.toJson(), Quiz.class);
            result.add(quiz);
        }
        return result;
    }

    @Override
    public Quiz createQuiz(Quiz quiz, User user) {
        quiz.setCreatedOn(LocalDate.now());
        quiz.setCreatedBy(user);
        Document document = Document.parse(gson.toJson(quiz));
        quizRepository.createQuiz(document);
        return quiz;
    }

    @Override
    public Quiz deleteQuiz(String id) {
        Quiz quiz = this.getQuiz(id);
        quizRepository.deleteQuiz(Document.parse(gson.toJson(quiz)));
        return quiz;
    }

    @Override
    public Map<String, Quiz> editQuiz(String id, Quiz newQuiz) {

        Quiz oldQuiz = this.getQuiz(id);
        quizRepository.editQuiz(Document.parse(gson.toJson(oldQuiz)), Document.parse(gson.toJson(newQuiz)));
        return Map.of("oldQuiz", oldQuiz, "newQuiz", newQuiz);
    }

}
