package laba4.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import laba4.controller.Factories.InstituteFactory;
import laba4.model.Institute;

@WebServlet("/AddInstituteControllerServlet")
public class AddInstituteControllerServlet extends HttpServlet {

  private InstituteFactory instituteFactory;

  @Override
  public void init() throws ServletException {
    instituteFactory = InstituteFactory.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String instituteName = request.getParameter("instituteName");
    if (!instituteFactory.instituteExists(instituteName)) {
      instituteFactory.createInstitute(instituteName);
      response.sendRedirect("/lab4-page");
    } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Institute with name " + instituteName + " already exists.");
    }
  }
}
