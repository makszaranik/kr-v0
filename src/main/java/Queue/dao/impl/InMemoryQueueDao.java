package Queue.dao.impl;

import Queue.dao.QueueDao;
import Queue.model.Queue;
import Queue.model.User;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class InMemoryQueueDao extends InMemoryAbstractDao<Queue> implements QueueDao {

  public InMemoryQueueDao(InMemoryDatabase database) {
    super(database.queues, Queue::getId, Queue::setId, database);
  }

  @Override
  public Collection<Queue> findQueueById(Integer id) {
    return database.queues.values().stream()
        .filter(queue -> queue.getId().equals(id))
        .collect(Collectors.toList());
  }

  @Override
  public void addQueue(User user, Queue queue) {
    queue.setCreator(user);
    insert(queue, true);
  }

  @Override
  public void deleteQueue(Queue queue) {
    Iterator<Queue> it = database.queues.values().iterator();
    while (it.hasNext()) {
      if (it.next().equals(queue)) {
        it.remove();
        break;
      }
    }
  }
}
