package Queue.view;

import Queue.services.NameValidatorService.AbstractNameValidatorService;
import Queue.services.NameValidatorService.impl.NameValidatorService;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.Factories.ServiceFactory;
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
  private AbstractNameValidatorService nameValidatorService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    this.queueDaoService = ServiceFactory.getQueueDaoService();
    this.nameValidatorService = ServiceFactory.getNameValidatorService();
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

    if(!nameValidatorService.isValidName(selectedQueueName)){
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

