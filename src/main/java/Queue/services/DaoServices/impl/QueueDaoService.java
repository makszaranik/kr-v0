package Queue.services.DaoServices.impl;

import Queue.dao.QueueDao;
import Queue.model.Queue;
import Queue.model.User;
import Queue.services.DaoServices.AbstractQueueDaoService;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class QueueDaoService implements AbstractQueueDaoService {

  private final QueueDao queueDao;

  public QueueDaoService(QueueDao queueDao) {
    this.queueDao = queueDao;
  }

  @Override
  public Queue findQueueByName(String name) {
    return queueDao.findAll().stream()
        .filter(queue -> queue.getName().equals(name))
        .findFirst().orElse(null);
  }

  @Override
  public void addQueueToUser(User user, Queue queue) {
    if (user != null && queue != null) {
      queue.setCreator(user);
      queueDao.insert(queue, true);
    }
  }

  @Override
  public Collection<Queue> getUserQueues(String username) {
     Collection<Queue> result =  queueDao.findAll().stream()
         .filter(queue -> queue.getCreator().getUsername().equals(username))
         .collect(Collectors.toList());
     return result;
  }

  @Override
  public Collection<Queue> getAllQueues() {
      return queueDao.findAll();
  }

  @Override
  public void updateQueue(Queue queue) {
      queueDao.update(queue);
  }

  @Override
  public void delete(Queue queue) {
      queueDao.delete(queue);
  }

}
