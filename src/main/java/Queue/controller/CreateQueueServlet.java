package Queue.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.QueueManager;
import Queue.model.User;

@WebServlet("/CreateQueue")
public class CreateQueueServlet extends HttpServlet {

  private QueueManager queueManager;

  public void init(){
    queueManager = QueueManager.getInstance();
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

    if(queueManager.IsExist(queueName)){
      request.getRequestDispatcher("/QueueIsAlreadyCreated.jsp").forward(request, response);
      return;
    }
    queueManager.createQueue(queueName, creator);
    response.sendRedirect("/MainPage.jsp");
  }
}
