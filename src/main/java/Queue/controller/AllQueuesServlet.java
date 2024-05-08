package Queue.controller;

import Queue.model.Queue;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.User;
import lombok.SneakyThrows;
import java.util.Optional;
import java.util.Collection;

@WebServlet("/AllQueues")
public class AllQueuesServlet extends HttpServlet {

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
    User user = (User) session.getAttribute("user");

    if (user != null) {
      Collection<Queue> allQueuesCollection = queueDaoService.getAllQueues();
      List<Queue> allQueues = new ArrayList<>(allQueuesCollection);
      request.setAttribute("queues", allQueues);
      request.getRequestDispatcher("AllQueues.jsp").forward(request, response);
    } else {
      response.sendRedirect("LoginPage.jsp");
    }
  }

}
