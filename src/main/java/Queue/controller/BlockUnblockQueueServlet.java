package Queue.controller;

import Queue.NameValidator.NameValidator;
import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.Queue;
import Queue.model.User;

@WebServlet("/BlockUnblockQueue")
public class BlockUnblockQueueServlet extends HttpServlet {

  private QueueDao queueDao;

  @Override
  public void init() throws ServletException {
    super.init();
    DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");
    this.queueDao = daoFactory.getQueueDao();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (!NameValidator.isValidName(selectedQueueName)) {
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Queue queue = queueDao.findAll().stream()
          .filter(q -> q.getName().equals(selectedQueueName))
          .findFirst()
          .orElse(null);

      if (queue == null) {
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        return;
      }

      queue.setBlocked(!queue.isBlocked());
      queueDao.update(queue);

      if (queue.isBlocked()) {
        request.getRequestDispatcher("/QueueSuccessfullyBlocked.jsp").forward(request, response);
      } else {
        request.getRequestDispatcher("/QueueSuccessfullyUnBlocked.jsp").forward(request, response);
      }
    } else {
      response.sendRedirect("/LoginPage.jsp");
    }
  }
}
