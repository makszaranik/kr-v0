package Queue.controller;

import Queue.model.Queue;
import Queue.model.QueueManager;
import Queue.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RemoveFromBegin")
public class RemoveFromBeginServlet extends HttpServlet {

  private QueueManager queueManager;

  @Override
  public void init() {
    queueManager = QueueManager.getInstance();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null) {
      String selectedQueueName = request.getParameter("selectedQueue");

      if(selectedQueueName == null || selectedQueueName.trim().isEmpty()){
        request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
        return;
      }

      Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);
      if (selectedQueue != null) {
        if (selectedQueue.isBlocked()) {
          request.getRequestDispatcher("/QueueIsBlocked.jsp").forward(request, response);
          return;
        }
        if(selectedQueue.isEmpty()){
          request.getRequestDispatcher("/NothingToShowQueueIsEmpty.jsp").forward(request, response);
          return;
        }
        selectedQueue.removeFirstItem();
      }
    }
    request.getRequestDispatcher("ItemSuccessfullyDeleted.jsp").forward(request, response);
  }
}
