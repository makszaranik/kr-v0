package Queue.view;

import Queue.model.Queue;
import Queue.model.QueueManager;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.User;

@WebServlet("/ViewMyPosition")
public class ViewMyPositionInQueue extends HttpServlet {
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
    request.getRequestDispatcher("/AllQueues.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession(false);
    User user = (User) session.getAttribute("user");
    Queue queue = queueManager.getQueueByName(selectedQueueName);

    if (queue != null) {
      List<String> itemsOfQueue = queue.getItems();
      Integer position = itemsOfQueue.indexOf(user.getUsername());
      if(position == -1){
          request.getRequestDispatcher("/YouWereDeletedFromQueue.jsp").forward(request, response);
      }else{
        request.setAttribute("UserPositionInQueue", position+1);
      }
      request.getRequestDispatcher("/UserPositionInQueue.jsp").forward(request, response);
    }
  }
}
