package Queue.dao.impl;

import Queue.dao.UserDao;
import Queue.model.User;

public class InMemoryUserDao extends InMemoryAbstractDao<User> implements UserDao {
  public InMemoryUserDao(InMemoryDatabase database) {
    super(database.users, User::getUserId, User::setUserId, database);
  }

  @Override
  public User getByUsername(String username) {
    return this.database.users.values().stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void insert(User user) {
    if (getByUsername(user.getUsername()) != null) {
      throw new IllegalArgumentException("User with username " + user.getUsername() + " already exists.");
    }
    insert(user, true);
  }
}
