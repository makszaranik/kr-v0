package Queue.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.Queue;
import Queue.model.QueueManager;
import Queue.model.User;

@WebServlet("/BlockUnblockQueue")
public class BlockUnblockQueueServlet extends HttpServlet {
  QueueManager queueManager;

  @Override
  public void init(){
    queueManager = QueueManager.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null && selectedQueueName != null) {
      Queue queue = queueManager.getQueueByName(selectedQueueName);
        boolean isBlocked = queue.isBlocked();
        if(isBlocked){
          request.getRequestDispatcher("/QueueSuccessfullyUnBlocked.jsp").forward(request, response);
          queueManager.unblockQueue(queue);
        }else{
          request.getRequestDispatcher("/QueueSuccessfullyBlocked.jsp").forward(request, response);
          queueManager.blockQueue(queue);
        }
    } else {
      response.sendRedirect("/LoginPage.jsp");
    }
  }
}
