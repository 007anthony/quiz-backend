package ch.bbw.ap.quizbackend.repository;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;


@Repository
public interface UserRepository {

    public FindIterable<Document> findAllUsers();
    public Document findUserById(String id);
    public Document findUserByUsername(String username);
    public void createUser(Document userDocument);
    public void editUser(Document oldDocument, Document newDocument);
    public void deleteUser(Document document);

}
