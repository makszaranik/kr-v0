package Queue.dao;

import Queue.model.User;

public interface UserDAO {
  void addUser(User user);
  User getUserById(int UserId);
  boolean isUserExist(String username, String password);
}
