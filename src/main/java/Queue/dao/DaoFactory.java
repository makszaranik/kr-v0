package Queue.dao;

import Queue.model.User;

public interface DaoFactory {
  QueueDao getQueueDao();
  UserDao getUserDao();
}
