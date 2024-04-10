package Queue.view;

import Queue.model.Queue;
import Queue.model.QueueManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewSelectedActionForMyQueues")
public class ViewSelectedActionForMyQueuesServlet extends HttpServlet {

  QueueManager queueManager;

  @Override
  public void init(){
      queueManager = QueueManager.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if ("View".equals(action)) {
      viewQueue(request, response);
    } else if ("ViewMyPosition".equals(action)) {
      viewMyPosition(request, response);
    } else {
      // Handle invalid action
    }
  }

  private void viewQueue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);
    if (selectedQueue != null) {
      request.setAttribute("selectedQueue", selectedQueue);
      request.getRequestDispatcher("/ViewMyQueueSelected").forward(request, response);
    }
  }

  private void viewMyPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/ViewMyPosition").forward(request, response);
  }
}
