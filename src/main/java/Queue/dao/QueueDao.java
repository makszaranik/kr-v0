package Queue.dao;

import Queue.model.User;
import Queue.model.Queue;
import java.util.Collection;

public interface QueueDao extends Dao<Queue> {
    Collection<Queue> findQueueById(Integer value1);
    void addQueue(User user, Queue queue);
    void deleteQueue(Queue queue);
}
