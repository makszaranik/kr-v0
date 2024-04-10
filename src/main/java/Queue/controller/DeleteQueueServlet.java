package Queue.controller;

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

import java.io.IOException;

@WebServlet("/DeleteQueueServlet")
public class DeleteQueueServlet extends HttpServlet {
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
        Set<Queue> userQueues = queueManager.getQueuesByUsername(username);
        request.setAttribute("queues", userQueues);
      }
    }
    request.getRequestDispatcher("/EditSelectedQueue.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null && selectedQueueName != null) {
      Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);
      if (selectedQueue != null) {
        queueManager.deleteQueue(selectedQueue);
      }
    }

    doGet(request, response);
  }
}
