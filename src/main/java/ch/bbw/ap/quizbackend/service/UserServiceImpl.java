package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.common.CredentialHelper;
import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.UserWithCredentials;
import ch.bbw.ap.quizbackend.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialHelper credentialHelper;
    private Gson gson = new GsonBuilder().create();


    @Override
    public List<UserWithCredentials> getAllUsersWithPassword() {
        FindIterable<Document> iterable = userRepository.findAllUsers();

        Iterator<Document> it = iterable.iterator();

        List<UserWithCredentials> users = new ArrayList<>();
        while(it.hasNext()) {
            Document document = it.next();
            users.add(gson.fromJson(document.toJson(), UserWithCredentials.class));
        }
        return users;
    }

    @Override
    public User getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    @Override
    public UserWithCredentials getUser(String username) {
        Document doc = userRepository.findUserByUsername(username);
        return gson.fromJson(doc.toJson(), UserWithCredentials.class);
    }

    @Override
    public UserWithCredentials getUserByToken(String token) throws NoSuchAlgorithmException {
        String[] decodedToken = new String(Base64.getDecoder().decode(token)).split(".");
        LocalDate dueDate = LocalDate.now().plus(2, ChronoUnit.HOURS);
        if(LocalDate.now().isAfter(dueDate)) {
            return null;
        }
        UserWithCredentials user = getUser(decodedToken[1]);
        if(user != null && credentialHelper.getSignature(user).equals(decodedToken[0])) {
            return user;
        }

        return null;

    }

    @Override
    public User createUser(UserWithCredentials user) throws NoSuchAlgorithmException {
        try {
            user.setPassword(getSha256HashedPasswordFromUser(user));
            Document document = Document.parse(gson.toJson(user));
            userRepository.createUser(document);
            user.setPassword(null);
            return user;
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Map<String, User> editUser(User user) {

        User oldUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Document oldDocument = Document.parse(gson.toJson(oldUser));
        Document newDocument = Document.parse(gson.toJson(user));
        userRepository.editUser(oldDocument, newDocument);

        return Map.of("oldUser", oldUser, "newUser", user);
    }

    @Override
    public User deleteUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Document document = Document.parse(gson.toJson(user));
        userRepository.deleteUser(document);

        return user;
    }

    @Override
    public Map<String, String> login(UserWithCredentials user)  {
        try {
            if(validateUser(user)) {
                return Map.of("token", credentialHelper.createToken(user));
            }
        }
        catch (NoSuchAlgorithmException e) {
        }

        return null;
    }

    public UserWithCredentials getUserFromDb(UserWithCredentials user) {
        Document doc = userRepository.findUserByUsername(user.getUsername());
        return gson.fromJson(doc.toJson(), UserWithCredentials.class);
    }

    @Override
    public boolean validateUser(UserWithCredentials user) throws NoSuchAlgorithmException {

        String userFromDb = getUserFromDb(user).getPassword();
        String hashedPassword = getSha256HashedPasswordFromUser(user);
        return userFromDb != null && userFromDb.equals(hashedPassword);
    }

    private String getSha256HashedPasswordFromUser(UserWithCredentials user) throws NoSuchAlgorithmException {
        return CredentialHelper.getHash(user.getUsername() + user.getPassword(), "SHA-256");
    }
}
