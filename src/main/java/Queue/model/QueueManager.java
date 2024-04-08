package Queue.model;

import java.util.ArrayList;
import java.util.List;

public class QueueManager {
  private static QueueManager instance;
  private List<Queue> queues;

  private QueueManager() {
    this.queues = new ArrayList<>();
  }

  public static synchronized QueueManager getInstance() {
    if (instance == null) {
      instance = new QueueManager();
    }
    return instance;
  }

  public List<Queue> getQueues() {
    return queues;
  }

  public void createQueue(String name, User creator) {
    Queue queue = new Queue(name, creator);
    queue.addItem("12");
    queue.addItem("13");
    queue.addItem("15");
    queue.addUser(creator);
    queues.add(queue);
  }

  public void deleteQueue(Queue queue) {
    queues.remove(queue);
  }

  public List<Queue> getQueuesByUsername(String username) {
    List<Queue> userQueues = new ArrayList<>();
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

}
