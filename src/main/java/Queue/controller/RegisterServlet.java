package Queue.controller;

import Queue.dao.DaoFactory;
import Queue.dao.UserDao;
import Queue.model.Role.RoleType;
import Queue.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    super.init();
    DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");
    this.userDao = daoFactory.getUserDao();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");

    if (!password.equals(confirmPassword)) {
      response.sendRedirect("PasswordMismatch.jsp");
      return;
    }

    if (userDao.getByUsername(username) != null) {
      response.sendRedirect("UserExists.jsp");
      return;
    }


    User newUser = new User(username, password, RoleType.USER);
    userDao.insert(newUser, true);

    response.sendRedirect("LoginPage.jsp");
  }
}
