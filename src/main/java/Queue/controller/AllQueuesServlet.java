package Queue.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.QueueManager;
import Queue.model.User;

@WebServlet("/AllQueues")
public class AllQueuesServlet extends HttpServlet {

  QueueManager queueManager;

  @Override
  public void init(){
    queueManager = QueueManager.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user != null) {
      request.setAttribute("queues", queueManager.getQueues());
      request.getRequestDispatcher("AllQueues.jsp").forward(request, response);
    } else {
      response.sendRedirect("LoginPage.jsp");
    }
  }
}
