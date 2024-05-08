package Queue.services.RoleConfiguratorService;

import Queue.model.Queue;
import Queue.model.Role.RoleType;
import Queue.model.User;
import lombok.Getter;


public interface AbstractRoleConfiguratorService {
  RoleType getConfiguration(User user, Queue queue);
  void configure(User user, Queue queue, RoleType roleType);
}
