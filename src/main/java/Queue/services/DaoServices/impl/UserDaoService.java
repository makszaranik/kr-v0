package Queue.services.DaoServices.impl;

import Queue.dao.UserDao;
import Queue.model.User;
import Queue.services.DaoServices.AbstractUserDaoService;
import java.util.Optional;

public class UserDaoService implements AbstractUserDaoService {

  private final UserDao userDao;

  public UserDaoService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User findUserByUsername(String username) {
    return userDao.findAll().stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst().orElse(null);
  }

  @Override
  public void createUser(User user) {
    User existingUser = findUserByUsername(user.getUsername());
    if (existingUser != null) {
      throw new IllegalArgumentException("User already exists with username: " + user.getUsername());
    }
    userDao.insert(user, true);
  }


  @Override
  public void deleteUser(String username) {
    User user = findUserByUsername(username);
    if(user != null){
      userDao.delete(user);
    }
  }


  @Override
  public boolean isExist(String username) {
    return findUserByUsername(username) != null;
  }
}
