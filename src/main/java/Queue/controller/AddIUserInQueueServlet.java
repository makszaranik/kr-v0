package Queue.controller;

import Queue.NameValidator.NameValidator;
import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.*;

@WebServlet("/AddToQueueServlet")
public class AddIUserInQueueServlet extends HttpServlet {

  private QueueDao queueDao;

  @Override
  public void init() throws ServletException {
      super.init();
      DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");
      this.queueDao = daoFactory.getQueueDao();
  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) session.getAttribute("user");
      if (user != null) {
        List<Queue> userQueues = (List<Queue>) queueDao
            .findAll()
            .stream()
            .filter(queue -> queue.getCreator()
                .equals(user));
        request.setAttribute("queues", userQueues);
      }
    }
    request.getRequestDispatcher("/EditSelectedQueue.jsp").forward(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    String newItem = request.getParameter("newItem");

    if(!NameValidator.isValidName(selectedQueueName)
        || !NameValidator.isValidName(newItem)){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    Optional<Queue> selectedQueue = queueDao
        .findAll()
        .stream()
        .filter(queue -> queue.getName()
            .equals(selectedQueueName))
        .findFirst();

    if(selectedQueue.isPresent()){
        if(selectedQueue.get().isBlocked()){
          request.getRequestDispatcher("/QueueIsBlocked.jsp").forward(request, response);
          return;
        }
        if(selectedQueue.get().getItems().contains(newItem)){
          request.getRequestDispatcher("/ItemIsAlreadyExist.jsp").forward(request, response);
          return;
        }
        selectedQueue.get().addItem(newItem.trim());
        request.getRequestDispatcher("/ItemSuccessfullyAdded.jsp").forward(request, response);
      }
    }

}
