package Queue.controller;

import Queue.model.Role.RoleType;
import Queue.model.User;
import Queue.services.DaoServices.AbstractUserDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

  private AbstractUserDaoService userDaoService;

  @Override
  @SneakyThrows
  public void init() {
    super.init();
    this.userDaoService = ServiceFactory.getUserDaoService();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm_password");


    if (!password.equals(confirmPassword)) {
      response.sendRedirect("PasswordMismatch.jsp");
      return;
    }

    if (userDaoService.isExist(username)) {
      response.sendRedirect("UserExists.jsp");
      return;
    }


    User newUser = new User(username, password, RoleType.USER);
    userDaoService.createUser(newUser);

    response.sendRedirect("LoginPage.jsp");
  }
}
