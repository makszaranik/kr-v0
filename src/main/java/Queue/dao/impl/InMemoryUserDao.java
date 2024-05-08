package Queue.dao.impl;

import Queue.dao.UserDao;
import Queue.model.Queue;
import Queue.model.User;
import java.util.Collection;

public class InMemoryUserDao extends InMemoryAbstractDao<User> implements UserDao {
  public InMemoryUserDao(InMemoryDatabase database) {
    super(database.users, User::getUserId, User::setUserId, database);
  }

  @Override
  public Collection<User> findAll() {
     return super.findAll();
  }

  @Override
  public void insert(User user, boolean generateId) {
    super.insert(user, generateId);
  }

  @Override
  public void delete(User user){
    super.delete(user);
  }

  @Override
  public void update(User user){
    super.update(user);
  }

  @Override
  public User get(Integer id){
    return super.get(id);
  }
}
