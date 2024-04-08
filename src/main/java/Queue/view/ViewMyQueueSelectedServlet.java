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

@WebServlet("/ViewSelectedQueue")
public class ViewMyQueueSelectedServlet extends HttpServlet {

  QueueManager queueManager;
  public void init(){
    queueManager = QueueManager.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null && selectedQueueName != null) {
      Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);

      if (selectedQueue != null && !selectedQueue.getItems().isEmpty()) {
        request.setAttribute("selectedQueue", selectedQueue);
        request.getRequestDispatcher("ViewSelectedQueue.jsp").forward(request, response);
      } else {
        request.getRequestDispatcher("NothingToShow.jsp").forward(request, response);
      }
    }
  }
}
