package Queue.controller;

import Queue.model.User;
import Queue.services.DaoServices.AbstractUserDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

  private AbstractUserDaoService userDaoService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    this.userDaoService = ServiceFactory.getUserDaoService();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    User user = (userDaoService.findUserByUsername(username));

    if(user == null){
      System.out.println("user is null");
    }

    assert (user != null);

    if (user.getPassword().equals(password)) {
      request.getSession().setAttribute("username", username);
      request.getSession().setAttribute("user", user);
      response.sendRedirect("MainPage.jsp");
    } else {
      request.getRequestDispatcher("InvalidLoginOrPassword.jsp").forward(request, response);
    }
  }
}
