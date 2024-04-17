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

@WebServlet("/AddMeInQueue")
public class AddMeInQueueServlet extends HttpServlet {

  QueueManager queueManager;

  @Override
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
        Set<Queue> userQueues = queueManager.getQueuesByUsername(username);
        request.setAttribute("queues", userQueues);
      }
    }
    request.getRequestDispatcher("/AddMeInQueue.jsp").forward(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession(false);
    User user = (User) session.getAttribute("user");
    String newItem = user.getUsername();

    if(selectedQueueName == null || selectedQueueName.trim().isEmpty() ||
        newItem == null || newItem.trim().isEmpty()){
        request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
        return;
    }

    Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);
    if(selectedQueue.isBlocked()){
      request.getRequestDispatcher("/QueueIsBlocked.jsp").forward(request, response);
      return;
    }
    if(selectedQueue.getItems().contains(newItem)){
      request.getRequestDispatcher("/ItemIsAlreadyExist.jsp").forward(request, response);
      return;
    }
    selectedQueue.addItem(newItem.trim());
    request.getRequestDispatcher("/ItemSuccessfullyAdded.jsp").forward(request, response);
  }
}
