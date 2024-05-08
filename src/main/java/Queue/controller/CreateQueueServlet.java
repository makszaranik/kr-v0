package Queue.controller;

import Queue.model.Queue;
import Queue.model.User;
import Queue.services.NameValidator.NameValidator;

import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import java.util.Optional;
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

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User creator = (User) session.getAttribute("user");
    if (creator == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }
    request.getRequestDispatcher("/CreateQueue.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("queueName");
    HttpSession session = request.getSession();
    User creator = (User) session.getAttribute("user");

    if (creator == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    if (!NameValidator.isValidName(selectedQueueName)) {
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

    if(selectedQueue != null){
      request.getRequestDispatcher("/QueueIsAlreadyCreated.jsp").forward(request, response);
      return;
    }

    Queue newQueue = new Queue(selectedQueueName, creator);
    queueDaoService.addQueueToUser(creator, newQueue);
    response.sendRedirect("/MainPage.jsp");
  }
}
