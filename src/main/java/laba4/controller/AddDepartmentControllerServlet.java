package laba4.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import laba4.controller.Factories.DepartmentFactory;
import laba4.controller.Factories.InstituteFactory;
import laba4.model.Department;
import laba4.model.Institute;

@WebServlet("/AddDepartmentControllerServlet")
public class AddDepartmentControllerServlet extends HttpServlet {

  private DepartmentFactory departmentFactory;
  private InstituteFactory instituteFactory;

  @Override
  public void init() throws ServletException {
    departmentFactory = DepartmentFactory.getInstance();
    instituteFactory = InstituteFactory.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String departmentName = request.getParameter("departmentName");
    String instituteName = request.getParameter("instituteName");

    if (!instituteFactory.instituteExists(instituteName)) {
      response.sendRedirect("InstituteNotFound.jsp");
      return;
    }

    try {
      Department department = departmentFactory.getDepartmentInstance(departmentName, instituteName);
      response.getWriter().println("Department created successfully: " + department.getName());
    } catch (IllegalArgumentException e) {
      request.getRequestDispatcher("InstituteNotFound.jsp").forward(request, response);
    }
  }
}
