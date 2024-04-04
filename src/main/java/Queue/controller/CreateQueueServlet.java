package Queue.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Queue.model.Queue;
import Queue.model.QueueManager;
import Queue.model.User;

@WebServlet("/createQueue")
public class CreateQueueServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String queueName = request.getParameter("queueName");

    HttpSession session = request.getSession();
    User creator = (User) session.getAttribute("user");

    QueueManager queueManager = QueueManager.getInstance();
    queueManager.createQueue(queueName, creator);

    response.sendRedirect("/MainPage.jsp");
  }
}
