package Queue.web;

import Queue.dao.DaoFactory;
import Queue.dao.impl.InMemoryDatabase;
import Queue.dao.impl.InMemoryTestData;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextListener implements ServletContextListener {

  public ApplicationContextListener() {}

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    InMemoryDatabase database = new InMemoryDatabase();
    InMemoryTestData.generateTo(database);

    DaoFactory daoFactory = database.getDaoFactory();

    sce.getServletContext().setAttribute("database", database);
    sce.getServletContext().setAttribute("daoFactory", daoFactory);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {}
}
