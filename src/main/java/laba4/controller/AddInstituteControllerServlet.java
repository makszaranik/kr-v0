package laba4.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import laba4.controller.Factories.InstituteFactory;

@WebServlet("/AddInstituteControllerServlet")
public class AddInstituteControllerServlet extends HttpServlet {

  private InstituteFactory instituteFactory;

  @Override
  public void init() throws ServletException {
    instituteFactory = InstituteFactory.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String instituteName = request.getParameter("instituteName");
    if (!instituteFactory.isExists(instituteName)) {
      instituteFactory.createOrGetInstance(instituteName);
      response.sendRedirect("/lab4-page");
    }
  }

}
