package Queue.controller;

import Queue.NameValidator.NameValidator;
import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Queue.model.Queue;
import Queue.model.User;

import java.io.IOException;

@WebServlet("/RemoveFromQueue")
public class RemoveItemFromQueueServlet extends HttpServlet {

  private QueueDao queueDao;

  @Override
  public void init() throws ServletException {
    super.init();
    DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("doaFactory");
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

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    String itemToRemove = request.getParameter("itemToRemove");

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if(!NameValidator.isValidName(selectedQueueName) || !NameValidator.isValidName(itemToRemove)){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if (user != null) {
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
        selectedQueue.get().removeItem(itemToRemove.trim());
        request.getRequestDispatcher("/ItemSuccessfullyDeleted.jsp").forward(request, response);
      }
    }
  }
}
