package Queue.dao.impl;

import Queue.dao.DaoFactory;
import Queue.model.Queue;
import Queue.model.User;
import java.util.Map;
import java.util.TreeMap;

public class InMemoryDatabase {
  Map<Integer, Queue> queues = new TreeMap();
  Map<Integer, User> users = new TreeMap();

  public InMemoryDatabase() {}

  public DaoFactory getDaoFactory() {
    return new InMemoryDaoFactory(this);
  }
}
