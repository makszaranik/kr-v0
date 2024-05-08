package Queue.services.DaoServices;

import Queue.model.Queue;
import Queue.model.User;
import java.util.Collection;
import java.util.Optional;

public interface AbstractQueueDaoService {
  Queue findQueueByName(String name);
  void addQueueToUser(User user, Queue queue);
  Collection<Queue> getUserQueues(String username);
  Collection<Queue> getAllQueues();
  void updateQueue(Queue queue);
  void delete(Queue queue);
}
