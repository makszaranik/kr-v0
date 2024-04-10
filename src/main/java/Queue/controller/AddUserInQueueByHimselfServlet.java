package Queue.controller;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.Queue;
import Queue.model.QueueManager;
import Queue.model.User;

@WebServlet("/AddUserInQueueByHimself")
public class AddUserInQueueByHimselfServlet extends HttpServlet {

  private QueueManager queueManager;

  @Override
  public void init() throws ServletException {
    queueManager = QueueManager.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    Set<Queue> allQueues = queueManager.getQueues();
    request.setAttribute("queues", allQueues);
    request.getRequestDispatcher("AddMeInQueue.jsp").forward(request, response);
  }
}
