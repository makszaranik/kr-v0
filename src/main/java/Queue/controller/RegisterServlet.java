package Queue.controller;

import Queue.dao.UserDAO;
import Queue.dao.UserDAOFactory;
import Queue.dao.UserDataBase;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Queue.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  private UserDAOFactory userDAOFactory;


  public void init(){
      userDAOFactory = UserDAOFactory.getInstance();
  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String username = request.getParameter("username");
    String password = request.getParameter("password");


    User user = new User(username, password);
    UserDAO userDataBase = userDAOFactory.getUserDAO();
    userDataBase.addUser(user);


    response.sendRedirect("/LoginPage.jsp");
  }
}
