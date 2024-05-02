package Queue.controller;

import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.*;



@WebServlet("/MyQueues")
public class MyQueuesServlet extends HttpServlet {

  private QueueDao queueDao;

  @Override
  public void init() throws ServletException{
    super.init();
    DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");
    this.queueDao = daoFactory.getQueueDao();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    List<Queue> userQueues = (List<Queue>) queueDao
        .findAll()
        .stream()
        .filter(queue -> queue.getCreator()
            .equals(user));

    request.setAttribute("queues", userQueues);

    request.getRequestDispatcher("MyQueues.jsp").forward(request, response);
  }
}
