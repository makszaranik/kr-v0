package lab23.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab3-page")
public class Lab3PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      Cookie[] cookies = request.getCookies();

      if (cookies != null) {
        for (Cookie cookie : cookies) {
          String name = cookie.getName();
          String value = cookie.getValue();
          switch (name) {
            case "aFrom", "aTo", "aStep", "bFrom",
                "bTo", "bStep", "cFrom", "cTo",
                "cStep", "dFrom", "dTo", "dStep" -> request.setAttribute(name, value);
          }
        }
      }

      request.getRequestDispatcher("/lab3Page.jsp").forward(request, response);
    }
}
