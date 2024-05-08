package Queue.view;

import Queue.model.Queue;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.Factories.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.User;
import lombok.SneakyThrows;

@WebServlet("/ViewMyPositionInQueue")
public class ViewMyPositionInQueue extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");
      if (user != null) {
        List<Queue> userQueues = (List<Queue>) queueDaoService.getUserQueues(user.getUsername());
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


    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

    if (selectedQueue != null) {
      List<String> itemsOfQueue = selectedQueue.getItems();
      int position = itemsOfQueue.indexOf(user.getUsername());
      if(position == -1){
          request.getRequestDispatcher("/YouWasDeletedFromQueue.jsp").forward(request, response);
      }else{
        request.setAttribute("UserPositionInQueue", position+1);
      }
      request.getRequestDispatcher("/UserPositionInQueue.jsp").forward(request, response);
    }
  }
}
