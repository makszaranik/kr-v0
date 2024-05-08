package Queue.dao.impl;

import Queue.dao.QueueDao;
import Queue.model.Queue;
import java.util.Collection;



public class InMemoryQueueDao extends InMemoryAbstractDao<Queue> implements QueueDao {

  public InMemoryQueueDao(InMemoryDatabase database) {
    super(database.queues, Queue::getId, Queue::setId, database);
  }

  @Override
  public Collection<Queue> findAll() {
     return super.findAll();
  }

  @Override
  public void insert(Queue queue, boolean generateId) {
    super.insert(queue, generateId);
  }

  @Override
  public void delete(Queue queue){
      super.delete(queue);
  }

  @Override
  public void update(Queue queue){
    super.update(queue);
  }

  @Override
  public Queue get(Integer id){
    return super.get(id);
  }
}
