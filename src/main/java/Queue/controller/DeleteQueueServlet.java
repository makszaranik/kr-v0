package Queue.controller;

import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import Queue.NameValidator.NameValidator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.Queue;
import Queue.model.User;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/DeleteQueueServlet")
public class DeleteQueueServlet extends HttpServlet {

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
        request.setAttribute("queues", queueDao.findAll());
      }
    }
    request.getRequestDispatcher("/EditSelectedQueue.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedQueueName = request.getParameter("selectedQueue");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if(!NameValidator.isValidName(selectedQueueName)){
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    if (user != null) {
      Optional<Queue> selectedQueue = queueDao.findAll().stream()
          .filter(q -> q.getName().equals(selectedQueueName))
          .findFirst();

      if (selectedQueue.isPresent()) {
          queueDao.delete(selectedQueue.get());
          doGet(request, response);
      } else {
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
      }
    } else {
      response.sendRedirect("/LoginPage.jsp");
    }
  }
}
