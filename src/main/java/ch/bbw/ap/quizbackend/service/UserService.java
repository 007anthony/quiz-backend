package ch.bbw.ap.quizbackend.service;

import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.UserWithCredentials;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    public List<UserWithCredentials> getAllUsersWithPassword();
    public User getUser();
    public UserWithCredentials getUser(String username);
    public UserWithCredentials getUserByToken(String token) throws NoSuchAlgorithmException;
    public User createUser(UserWithCredentials user) throws NoSuchAlgorithmException;
    public Map<String, User> editUser(User user);
    public User deleteUser();

    public Map<String, String> login(UserWithCredentials user);
    public UserWithCredentials getUserFromDb(UserWithCredentials user);
    public boolean validateUser(UserWithCredentials user) throws NoSuchAlgorithmException;
}
