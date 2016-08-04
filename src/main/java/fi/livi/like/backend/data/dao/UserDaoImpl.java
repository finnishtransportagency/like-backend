package fi.livi.like.backend.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.livi.like.backend.data.mapper.LikeMapper;

@Component
public class UserDaoImpl implements UserDao {

    private final LikeMapper mapper;
    
    @Autowired
    public UserDaoImpl(LikeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String getUserId(String username, String password) {
        return mapper.selectUserId(username, password);
    }
    
    @Override
    public void addUser(String userId, String username, String password) {
        mapper.insertUser(userId, username, password);
    }
}
