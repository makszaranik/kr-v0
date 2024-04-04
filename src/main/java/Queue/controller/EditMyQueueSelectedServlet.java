package Queue.controller;

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

@WebServlet("/EditMyQueueSelected")
public class EditMyQueueSelectedServlet extends HttpServlet {
  private QueueManager queueManager = null;

  public void init(){
    queueManager = QueueManager.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");
      if (user != null) {
        String username = user.getUsername();
        request.setAttribute("queues", queueManager.getQueuesByUsername(username));
      }
    }
    response.sendRedirect("/EditMyQueueSelected.jsp");
  }



  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    String selectedQueueName = request.getParameter("selectedQueue");
    String newItem = request.getParameter("newItem");
    String itemToRemove = request.getParameter("itemToRemove");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null && selectedQueueName != null) {
      Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);
      if (selectedQueue != null) {
        switch (action) {
          case "deleteQueue":
            queueManager.deleteQueue(selectedQueue);
            break;
          case "addBack":
            if (newItem != null && !newItem.isEmpty()) {
              selectedQueue.addItem(newItem);
            }
            break;
          case "removeFront":
            if (itemToRemove != null && !itemToRemove.isEmpty()) {
              selectedQueue.removeItem(itemToRemove);
            }
            break;
          default:
            break;
        }
      }
    }

    String username = user.getUsername();
    List<Queue> queues = queueManager.getQueuesByUsername(username);
    request.setAttribute("queues", queues);
    request.getRequestDispatcher("/EditMyQueueSelected.jsp").forward(request, response);
  }
}
