package Queue.controller;

import Queue.services.NameValidatorService.AbstractNameValidatorService;
import Queue.services.NameValidatorService.impl.NameValidatorService;
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

import Queue.model.Queue;
import Queue.model.User;
import lombok.SneakyThrows;

@WebServlet("/AddMeInQueue")
public class AddMeInQueueServlet extends HttpServlet {

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
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");

      if (user != null) {
        List<Queue> userQueues = (List<Queue>) queueDaoService.getAllQueues();
        request.setAttribute("queues", userQueues);
      }
    }
    request.getRequestDispatcher("/AddMeInQueue.jsp").forward(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession(false);
    User user = (User) session.getAttribute("user");

    String newItem = user.getUsername();


    if(!nameValidatorService.isValidName(selectedQueueName)
        || !nameValidatorService.isValidName(newItem)){
        request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
        return;
    }

    Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);
    if(selectedQueue != null){
      if(selectedQueue.isBlocked()){
        request.getRequestDispatcher("/QueueIsBlocked.jsp").forward(request, response);
        return;
      }
      if(selectedQueue.getItems().contains(newItem)){
        request.getRequestDispatcher("/ItemIsAlreadyExist.jsp").forward(request, response);
        return;
      }
      selectedQueue.addItem(newItem.trim());
      request.getRequestDispatcher("/ItemSuccessfullyAdded.jsp").forward(request, response);
    }

  }
}
