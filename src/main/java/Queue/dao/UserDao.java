package Queue.dao;

import Queue.model.User;

public interface UserDao extends Dao<User> {
    User getByUsername(String value);
    void insert(User user);
}
