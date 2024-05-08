package Queue.controller;

import Queue.services.NameValidator.NameValidator;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.*;
import Queue.model.Queue;
import lombok.SneakyThrows;

@WebServlet("/BlockUnblockQueue")
public class BlockUnblockQueueServlet extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");


    if (!NameValidator.isValidName(selectedQueueName)) {
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

      if (selectedQueue == null) {
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        return;
      }

      selectedQueue.setBlocked(!selectedQueue.isBlocked());
      queueDaoService.updateQueue(selectedQueue);

      if (selectedQueue.isBlocked()) {
        request.getRequestDispatcher("/QueueSuccessfullyBlocked.jsp").forward(request, response);
      } else {
        request.getRequestDispatcher("/QueueSuccessfullyUnBlocked.jsp").forward(request, response);
      }
    } else {
      response.sendRedirect("/LoginPage.jsp");
    }
  }
}
