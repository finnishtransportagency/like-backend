package fi.livi.like.backend.data;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fi.livi.like.backend.data.dao.UserDao;
import fi.livi.like.backend.domain.User;

@Component
public class UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);

    private UserDao userDao;
    private String addUserCommandAndArguments;

    @Autowired
    public UserRepository(@Value("${script.adduser.commandandarguments}") String addUserCommandAndArguments, 
            UserDao userDao) {
        this.userDao = userDao;
        this.addUserCommandAndArguments = addUserCommandAndArguments;
    }
    
    public User getUser(String username, String password) {
        String hashedUsername = HashUtils.encryptString(username);
        String hashedPassword = HashUtils.encryptString(password);
        String userId = userDao.getUserId(hashedUsername, hashedPassword);
        if (userId == null) {
            userId = hashedUsername + hashedPassword;
            userDao.addUser(userId, hashedUsername, hashedPassword);
            if (addUserCommandAndArguments != null && addUserCommandAndArguments.length() > 0) {
                try {
                    addUserToDbm(addUserCommandAndArguments, username, password);
                } catch (IOException ioe) {
                    LOG.error("Adding user {} to dbm failed.", userId, ioe);
                }
            }
        }
        return new User(userId, hashedUsername, hashedPassword);
    }
    
    private void addUserToDbm(String addUserCommandAndArguments, String username, String password) throws IOException {
        String args = addUserCommandAndArguments.replace("<username>", username);
        args = args.replace("<password>", password);
        String[] splitted = args.split(" ");
        LOG.info("Creating user to dbm.");
        List<String> commandAndArguments = Arrays.asList(splitted);
        ProcessBuilder pb = new ProcessBuilder(commandAndArguments);
        pb.start();
    }
}