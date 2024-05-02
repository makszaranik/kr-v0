package Queue.controller;

import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import Queue.dao.UserDao;
import java.io.IOException;
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

@WebServlet("/EditQueue")
public class EditQueueServlet extends HttpServlet {

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
    User user = (User) session.getAttribute("user");

    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    List<Queue> userQueues = (List<Queue>) queueDao
        .findAll()
        .stream()
        .filter(queue -> queue.getCreator()
            .equals(user));

    request.setAttribute("queues", userQueues);
    request.getRequestDispatcher("EditSelectedQueue.jsp").forward(request, response);
  }
}
