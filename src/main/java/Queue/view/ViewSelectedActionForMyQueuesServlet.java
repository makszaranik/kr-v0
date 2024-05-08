package Queue.view;

import Queue.services.NameValidator.NameValidator;
import Queue.model.Queue;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/ViewSelectedActionForMyQueues")
public class ViewSelectedActionForMyQueuesServlet extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if ("View".equals(action)) {
      viewQueue(request, response);
    } else if ("ViewMyPosition".equals(action)) {
      viewMyPosition(request, response);
    }
  }

  private void viewQueue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");

    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

    if(!NameValidator.isValidName(selectedQueueName)){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if(selectedQueue != null){
      request.setAttribute("selectedQueue", selectedQueue);
      request.getRequestDispatcher("/ViewMyQueueSelected").forward(request, response);
    }
  }

  private void viewMyPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/ViewMyPositionInQueue").forward(request, response);
  }
}
