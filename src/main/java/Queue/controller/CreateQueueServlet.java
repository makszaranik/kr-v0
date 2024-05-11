package Queue.controller;

import Queue.model.Queue;
import Queue.model.Role.RoleType;
import Queue.model.User;
import Queue.services.NameValidatorService.AbstractNameValidatorService;
import Queue.services.NameValidatorService.impl.NameValidatorService;

import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.Factories.ServiceFactory;
import Queue.services.RoleConfiguratorService.AbstractRoleConfiguratorService;
import Queue.services.RoleConfiguratorService.impl.RoleConfiguratorService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.SneakyThrows;

@WebServlet("/CreateQueue")
public class CreateQueueServlet extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;
  private AbstractRoleConfiguratorService roleConfiguratorService;
  private AbstractNameValidatorService nameValidatorService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
    roleConfiguratorService = ServiceFactory.getRoleConfiguratorService();
    nameValidatorService = ServiceFactory.getNameValidatorService();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }
    request.getRequestDispatcher("/CreateQueue.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("queueName");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (!nameValidatorService.isValidName(selectedQueueName)) {
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);
    if(selectedQueue != null){
      request.getRequestDispatcher("/QueueIsAlreadyCreated.jsp").forward(request, response);
      return;
    }

    Queue queue = new Queue(selectedQueueName, user);
    roleConfiguratorService.configure(user, queue, RoleType.OWNER);
    queueDaoService.addQueueToUser(user, queue);
    response.sendRedirect("/MainPage.jsp");
  }
}
