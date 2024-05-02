package Queue.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Queue.model.Queue;
import Queue.model.User;

import java.io.IOException;

@WebServlet("/ViewMyQueueSelected")
public class ViewMyQueueSelectedServlet extends HttpServlet {

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
      Queue selectedQueue = queueManager.getQueueByName(selectedQueueName);

      if (!selectedQueue.getItems().isEmpty()) {
        request.setAttribute("selectedQueue", selectedQueue);
        request.getRequestDispatcher("ViewSelectedQueue.jsp").forward(request, response);
      } else {
        request.getRequestDispatcher("NothingToShowQueueIsEmpty.jsp").forward(request, response);
      }
    }
  }
}
