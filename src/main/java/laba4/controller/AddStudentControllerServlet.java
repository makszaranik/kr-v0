package laba4.controller;

import laba4.controller.Factories.DepartmentFactory;
import laba4.model.Department;
import laba4.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddStudentControllerServlet")
public class AddStudentControllerServlet extends HttpServlet {
  private DepartmentFactory departmentFactory;

  @Override
  public void init() throws ServletException {
    departmentFactory = DepartmentFactory.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("studentName");
    String surname = request.getParameter("studentSurname");
    String departmentName = request.getParameter("departmentName");
    String instituteName = request.getParameter("instituteName");

    Department department = departmentFactory.createOrGetInstance(departmentName, instituteName);
    Student student = new Student(name, surname);
    department.addStudent(student);

    response.sendRedirect("/lab4-page");

  }
}
