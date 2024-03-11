package lab23.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab2-page")
public class Lab2PageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        switch (name) {
          case "a", "b", "c", "d" -> request.setAttribute(name, value);
        }
      }
    }
    request.getRequestDispatcher("/lab2Page.jsp").forward(request, response);
  }

}
