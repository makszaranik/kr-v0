package Queue.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Queue.model.Queue;
import Queue.model.QueueManager;
import Queue.model.User;

@WebServlet("/AddToQueueServlet")
public class AddToQueueServlet extends HttpServlet {
  private QueueManager queueManager = null;

  public void init() {
    queueManager = QueueManager.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");
      if (user != null) {
        String username = user.getUsername();
        List<Queue> userQueues = queueManager.getQueuesByUsername(username);
        request.setAttribute("queues", userQueues);
      }
    }
    request.getRequestDispatcher("/EditQueueSelected.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String selectedQueueName = request.getParameter("selectedQueue");
    String newItem = request.getParameter("newItem");

    if (selectedQueueName != null && newItem != null) {
      Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);
      if (selectedQueue != null) {
        selectedQueue.addItem(newItem.trim());
      }
      doGet(request, response);
    }
  }
}
