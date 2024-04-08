package Queue.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Queue.model.Queue;
import Queue.model.QueueManager;
import Queue.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewAllQueues")
public class ViewAllQueueSelectedServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null) {
      QueueManager queueManager = QueueManager.getInstance();
      List<Queue> allQueues = queueManager.getQueues();

      if (!allQueues.isEmpty()) {
        request.setAttribute("selectedQueue", allQueues);
        request.getRequestDispatcher("ViewSelectedQueue.jsp").forward(request, response);
      } else {
        request.getRequestDispatcher("NothingToShow.jsp").forward(request, response);
      }
    } else {
      response.sendRedirect("LoginPage.jsp");
    }
  }
}
