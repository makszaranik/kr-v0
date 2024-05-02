package Queue.controller;

import Queue.dao.impl.InMemoryDaoFactory;
import Queue.dao.UserDao;
import Queue.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    super.init();
    this.userDao = (UserDao) getServletContext().getAttribute("userDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    User user = userDao.getByUsername(username);
    if (user != null && user.getPassword().equals(password)) {
      request.getSession().setAttribute("username", username);
      request.getSession().setAttribute("user", user);
      response.sendRedirect("MainPage.jsp");
    } else {
      request.setAttribute("loginFailed", true);
      request.getRequestDispatcher("InvalidLoginOrPassword.jsp").forward(request, response);
    }
  }
}
