package fi.livi.like.backend.data.dao;



public interface UserDao {
    public String getUserId(String username, String password);
    public void addUser(String userId, String username, String password);
}
