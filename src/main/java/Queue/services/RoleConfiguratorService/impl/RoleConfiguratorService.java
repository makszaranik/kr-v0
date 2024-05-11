package Queue.services.RoleConfiguratorService.impl;

import Queue.model.Queue;
import Queue.model.Role.RoleType;
import Queue.model.User;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.Factories.ServiceFactory;
import Queue.services.RoleConfiguratorService.AbstractRoleConfiguratorService;


public class RoleConfiguratorService implements AbstractRoleConfiguratorService {
  private AbstractQueueDaoService queueDaoService = ServiceFactory.getQueueDaoService();

  @Override
  public RoleType getConfiguration(User user, Queue queue) {
    if(queueDaoService.findQueueByName(queue.getName()).getCreator().equals(user)){
      return RoleType.OWNER;
    } else return RoleType.USER;
  }

  @Override
  public void configure(User user, Queue queue, RoleType roleType) {
    if(roleType == RoleType.OWNER){
        Queue existingQueue = queueDaoService.findQueueByName(queue.getName());
        if(existingQueue != null){
          existingQueue.setCreator(user);
        }
    }
  }
}
