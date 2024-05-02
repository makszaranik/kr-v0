package Queue.controller;

import Queue.NameValidator.NameValidator;
import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import Queue.model.Queue;
import Queue.model.User;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RemoveFromBegin")
public class RemoveItemFromBeginServlet extends HttpServlet {

  private QueueDao queueDao;

  @Override
  public void init() {
    DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");
    this.queueDao = daoFactory.getQueueDao();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null) {
      String selectedQueueName = request.getParameter("selectedQueue");

      if(!NameValidator.isValidName(selectedQueueName)){
        request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
        return;
      }

      Optional<Queue> selectedQueue = queueDao
          .findAll()
          .stream()
          .filter(queue -> queue.getName()
              .equals(selectedQueueName))
          .findFirst();

      if (selectedQueue.isPresent()) {
        if (selectedQueue.get().isBlocked()) {
          request.getRequestDispatcher("/QueueIsBlocked.jsp").forward(request, response);
          return;
        }
        selectedQueue.get().removeFirstItem();
        request.getRequestDispatcher("ItemSuccessfullyDeleted.jsp").forward(request, response);
      }else {
        request.getRequestDispatcher("/NothingToShowQueueIsEmpty.jsp").forward(request, response);
      }
    }
  }
}
