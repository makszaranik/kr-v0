package Queue.model;

import java.util.HashSet;
import java.util.Set;

public class QueueManager {
  private static QueueManager instance;
  private Set<Queue> queues;

  private QueueManager() {
    this.queues = new HashSet<>();
  }

  public static synchronized QueueManager getInstance() {
    if (instance == null) {
      instance = new QueueManager();
    }
    return instance;
  }

  public Set<Queue> getQueues() {
    return queues;
  }

  public void createQueue(String name, User creator) {
    Queue queue = new Queue(name, creator);
    queue.addUser(creator);
    queues.add(queue);
  }

  public void deleteQueue(Queue queue) {
    queues.remove(queue);
  }

  public Set<Queue> getQueuesByUsername(String username) {
    Set<Queue> userQueues = new HashSet<>();
    for (Queue queue : queues) {
      if (queue.getCreator().getUsername().equals(username)) {
        userQueues.add(queue);
      }
    }
    return userQueues;
  }

  public Queue getQueueByName(String name) {
    for (Queue queue : queues) {
      if (queue.getName().equals(name)) {
        return queue;
      }
    }
    return null;
  }

  public boolean IsExist(String queueName) {
    for (Queue queue : queues) {
      if (queue.getName().equals(queueName)) {
        return true;
      }
    }
    return false;
  }

  public void blockQueue(Queue queue) {
    queue.setBlocked(true);
  }

  public void unblockQueue(Queue queue) {
    queue.setBlocked(false);
  }
}
