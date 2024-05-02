package Queue.controller;

import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import Queue.model.Queue;
import Queue.model.User;
import Queue.NameValidator.NameValidator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreateQueue")
public class CreateQueueServlet extends HttpServlet {

  private QueueDao queueDao;

  @Override
  public void init() throws ServletException {
    super.init();
    DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");
    this.queueDao = daoFactory.getQueueDao();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User creator = (User) session.getAttribute("user");
    if (creator == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }
    request.getRequestDispatcher("/CreateQueue.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String queueName = request.getParameter("queueName");
    HttpSession session = request.getSession();
    User creator = (User) session.getAttribute("user");

    if (!NameValidator.isValidName(queueName)) {
      request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
      return;
    }

    Queue newQueue = new Queue(queueName, creator);
    if (queueDao.findQueueById(newQueue.getId()) != null) {
      request.getRequestDispatcher("/QueueIsAlreadyCreated.jsp").forward(request, response);
      return;
    }

    queueDao.addQueue(creator, newQueue);
    response.sendRedirect("/MainPage.jsp");
  }
}
