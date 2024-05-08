package Queue.web;

import Queue.dao.DaoFactory;
import Queue.dao.impl.InMemoryDatabase;
import Queue.dao.impl.InMemoryTestData;
import Queue.services.Factories.ServiceFactory;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import lombok.SneakyThrows;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

  @SneakyThrows
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    InMemoryDatabase database = new InMemoryDatabase();
    InMemoryTestData.generateTo(database);
    DaoFactory daoFactory = database.getDaoFactory();
    sce.getServletContext().setAttribute("daoFactory", daoFactory);
    sce.getServletContext().setAttribute("database", database);
    ServiceFactory.init(sce.getServletContext());
    System.out.println("ApplicationContext initialized with ServiceFactory and database");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {}
}
