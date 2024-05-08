package Queue.services.Factories;

import Queue.dao.DaoFactory;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.AbstractUserDaoService;

import Queue.services.DaoServices.impl.QueueDaoService;
import Queue.services.DaoServices.impl.UserDaoService;
import Queue.services.NameValidatorService.AbstractNameValidatorService;
import Queue.services.NameValidatorService.impl.NameValidatorService;
import Queue.services.RoleConfiguratorService.AbstractRoleConfiguratorService;
import Queue.services.RoleConfiguratorService.impl.RoleConfiguratorService;
import javax.servlet.ServletContext;
import lombok.Getter;

public class ServiceFactory {

  @Getter
  private static AbstractUserDaoService userDaoService;
  @Getter
  private static AbstractQueueDaoService queueDaoService;
  @Getter
  private static AbstractRoleConfiguratorService roleConfiguratorService;
  @Getter
  private static AbstractNameValidatorService nameValidatorService;

  public static void init(ServletContext context) {
    DaoFactory daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    userDaoService = new UserDaoService(daoFactory.getUserDao());
    queueDaoService = new QueueDaoService(daoFactory.getQueueDao());
    roleConfiguratorService = new RoleConfiguratorService(getQueueDaoService());
    nameValidatorService = new NameValidatorService();
  }

}
