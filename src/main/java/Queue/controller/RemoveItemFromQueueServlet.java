package Queue.controller;


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

@WebServlet("/RemoveFromQueue")
public class RemoveItemFromQueueServlet extends HttpServlet {

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
        List<Queue> userQueues = (List<Queue>) queueDaoService.getUserQueues(user.getUsername());
        request.setAttribute("queues", userQueues);
      }
    }
    request.getRequestDispatcher("/EditSelectedQueue.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    String itemToRemove = request.getParameter("itemToRemove");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if(!nameValidatorService.isValidName(selectedQueueName)
        || !nameValidatorService.isValidName(itemToRemove)){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

      if(selectedQueue != null){
        if(selectedQueue.isBlocked()){
          request.getRequestDispatcher("/QueueIsBlocked.jsp").forward(request, response);
          return;
        }
        if(selectedQueue.getQueueSize() == 0){
          request.getRequestDispatcher("/QueueIsEmpty.jsp").forward(request, response);
          return;
        }
        selectedQueue.removeItem(itemToRemove.trim());
        request.getRequestDispatcher("/ItemSuccessfullyDeleted.jsp").forward(request, response);
      }
    }
  }
}
