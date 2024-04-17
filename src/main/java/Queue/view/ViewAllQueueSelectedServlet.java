package Queue.view;

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

@WebServlet("/ViewAllQueueSelected")
public class ViewAllQueueSelectedServlet extends HttpServlet {

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

    if(selectedQueueName == null || selectedQueueName.trim().isEmpty()){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Set<Queue> queues = queueManager.getQueues();

      for(Queue queue : queues){
          if(queue.getName().equals(selectedQueueName)){
            Queue selectedQueue = queue;
              if (!selectedQueue.getItems().isEmpty()) {
                request.setAttribute("selectedQueue", selectedQueue);
                request.getRequestDispatcher("ViewSelectedQueue.jsp").forward(request, response);
              }else {
                request.getRequestDispatcher("NothingToShowQueueIsEmpty.jsp").forward(request, response);
            }
              break;
          }
      }
    }
  }
}
