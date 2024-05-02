package Queue.dao.impl;

import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import Queue.dao.UserDao;

public class InMemoryDaoFactory implements DaoFactory {
  InMemoryDatabase database;
  UserDao userDao;
  QueueDao queueDao;

  InMemoryDaoFactory(InMemoryDatabase database) {
    this.database = database;
    this.userDao = new InMemoryUserDao(database);
    this.queueDao = new InMemoryQueueDao(database);
  }

  @Override
  public UserDao getUserDao() {
    return this.userDao;
  }

  @Override
  public QueueDao getQueueDao() {return this.queueDao;}

}
