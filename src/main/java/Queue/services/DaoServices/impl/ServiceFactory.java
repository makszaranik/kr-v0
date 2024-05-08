package Queue.services.DaoServices.impl;

import Queue.dao.DaoFactory;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.AbstractUserDaoService;

import javax.servlet.ServletContext;

public class ServiceFactory {

  private static AbstractUserDaoService userService;
  private static AbstractQueueDaoService queueService;

  public static void init(ServletContext context) {
    DaoFactory daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    userService = new UserDaoService(daoFactory.getUserDao());
    queueService = new QueueDaoService(daoFactory.getQueueDao());
  }

  public static AbstractQueueDaoService getQueueDaoService() {return queueService;}

  public static AbstractUserDaoService getUserDaoService() {
    return userService;
  }

}
