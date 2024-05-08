package Queue.controller;

import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.Queue;
import Queue.model.User;
import lombok.SneakyThrows;

@WebServlet("/EditQueue")
public class EditQueueServlet extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    List<Queue> userQueues = (List<Queue>) queueDaoService.getUserQueues(user.getUsername());

    request.setAttribute("queues", userQueues);
    request.getRequestDispatcher("EditSelectedQueue.jsp").forward(request, response);
  }
}
