package ch.bbw.ap.quizbackend.common;

import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.UserWithCredentials;
import ch.bbw.ap.quizbackend.repository.UserRepository;
import ch.bbw.ap.quizbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Locale;

@Component
public class CredentialHelper {

    @Autowired
    private QuizProperties quizProperties;



    public static String getHash(String password, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder(2 * hashedPassword.length);
        for(byte hash : hashedPassword) {
            String hex = Integer.toHexString(0xff & hash);
            if(hex.length() == 1) {
                stringBuilder.append('0');
            }

            stringBuilder.append(hex);

        }
        return stringBuilder.toString();
    }
    public String createToken(User user) {
        String result = null;
        try {
            String signature = getSignature(user);
            result = Base64.getEncoder()
                    .encodeToString((signature + "." + user.getUsername() + "." + LocalDate.now().format(DateTimeFormatter.ISO_DATE))
                    .getBytes(StandardCharsets.UTF_8));
        }
        catch(NoSuchAlgorithmException e) {
        }

        return result;

    }

    public String getSignature(User user) throws NoSuchAlgorithmException{
        return CredentialHelper.getHash(quizProperties.getSecretKey() + user.getUsername(), "SHA-256");
    }


}
