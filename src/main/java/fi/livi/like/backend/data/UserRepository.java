package fi.livi.like.backend.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.livi.like.backend.data.dao.UserDao;
import fi.livi.like.backend.domain.User;

@Component
public class UserRepository {

    private UserDao userDao;

    @Autowired
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public User getUser(String username, String password) {
        String userId = userDao.getUserId(username, password);
        if (userId == null) {
            userId = username + password;
            userDao.addUser(userId, username, password);
        }
        return new User(userId, username, password);
    }
}