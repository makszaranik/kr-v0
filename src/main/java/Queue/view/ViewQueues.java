package Queue.view;

import Queue.model.User;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ViewQueues")
public class ViewQueues extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    User user = (User) session.getAttribute("user");


    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }

    request.getRequestDispatcher("/Queues.jsp").forward(request, response);
  }
}
