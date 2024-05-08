package Queue.view;

import Queue.services.NameValidatorService.AbstractNameValidatorService;
import Queue.services.NameValidatorService.impl.NameValidatorService;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.Factories.ServiceFactory;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Queue.model.Queue;
import Queue.model.User;

import java.io.IOException;
import lombok.SneakyThrows;

@WebServlet("/ViewAllQueueSelected")
public class ViewAllQueueSelectedServlet extends HttpServlet {

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
    String selectedQueueName = request.getParameter("selectedQueue");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");


    if(!nameValidatorService.isValidName(selectedQueueName)){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      List<Queue> allQueues = (List<Queue>) queueDaoService.getUserQueues(user.getUsername());

      for(Queue queue : allQueues){
        Queue selectedQueue = queue;
        if (!selectedQueue.getItems().isEmpty()) {
          request.setAttribute("selectedQueue", selectedQueue);
          request.getRequestDispatcher("ViewSelectedQueue.jsp").forward(request, response);
        }else {
          request.getRequestDispatcher("NothingToShowQueueIsEmpty.jsp").forward(request, response);
        }
      }
    }
  }
}
