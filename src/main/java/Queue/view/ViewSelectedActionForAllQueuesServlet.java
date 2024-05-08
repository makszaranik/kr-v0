package Queue.view;

import Queue.services.NameValidatorService.NameValidatorService;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Queue.model.Queue;
import lombok.SneakyThrows;


@WebServlet("/ViewSelectedActionForAllQueues")
public class ViewSelectedActionForAllQueuesServlet extends HttpServlet {

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

    if(!NameValidatorService.isValidName(selectedQueueName)){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if(selectedQueue != null){
      request.setAttribute("selectedQueue", selectedQueue);
      request.getRequestDispatcher("/ViewAllQueueSelected").forward(request, response);
    }
  }


  private void viewMyPosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/ViewMyPositionInQueue").forward(request, response);
  }

}

