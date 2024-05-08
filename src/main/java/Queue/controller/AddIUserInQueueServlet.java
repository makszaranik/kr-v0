package Queue.controller;

import Queue.services.NameValidatorService.NameValidatorService;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.*;

@WebServlet("/AddToQueueServlet")
public class AddIUserInQueueServlet extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;

  @Override
  public void init() throws ServletException {
      super.init();
      this.queueDaoService = ServiceFactory.getQueueDaoService();
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
    request.getRequestDispatcher("/EditSelectedQueue.jsp").forward(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    String newItem = request.getParameter("newItem");

    if(!NameValidatorService.isValidName(selectedQueueName)
        || !NameValidatorService.isValidName(newItem)){
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
