package Queue.controller;

import Queue.dao.DaoFactory;
import Queue.dao.QueueDao;
import Queue.model.Queue;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.User;

@WebServlet("/AllQueues")
public class AllQueuesServlet extends HttpServlet {

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
    if (user != null) {
      List<Queue> allQueues = (List<Queue>) queueDao.findAll();
      request.setAttribute("queues", allQueues);
      request.getRequestDispatcher("AllQueues.jsp").forward(request, response);
    } else {
      response.sendRedirect("LoginPage.jsp");
    }
  }
}
