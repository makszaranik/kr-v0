package Queue.dao;

import Queue.model.User;
import java.util.Map;
import java.util.TreeMap;

public class UserDataBase implements UserDAO {

  private static UserDataBase instance;
  private Map<Integer, User> userMap = new TreeMap<>();

  private UserDataBase() {}


  public static synchronized UserDataBase getInstance() {
    if (instance == null) {
      instance = new UserDataBase();
    }
    return instance;
  }

  @Override
  public void addUser(User user) {
    userMap.put(user.hashCode(), user);
  }

  @Override
  public User getUserById(int userId) {
    return userMap.get(userId);
  }

  @Override
  public boolean isUserExist(String username, String password) {
    int userId = new User(username, password).hashCode();
    return getUserById(userId) != null;
  }
}
