package laba4.view;

import java.util.ArrayList;
import laba4.controller.Factories.InstituteFactory;
import laba4.controller.Factories.DepartmentFactory;
import laba4.model.Department;
import laba4.model.Institute;
import laba4.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowStudentsServlet")
public class ShowStudentsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String instituteName = request.getParameter("institute");
    String departmentName = request.getParameter("department");

    InstituteFactory instituteFactory = InstituteFactory.getInstance();
    Institute institute = instituteFactory.createOrGetInstance(instituteName);

    DepartmentFactory departmentFactory = DepartmentFactory.getInstance();
    Department department = departmentFactory.createOrGetInstance(departmentName, instituteName);


    List<Student> students = department.getListStudents();

    request.setAttribute("students", students);
    request.getRequestDispatcher("/showStudents.jsp").forward(request, response);

  }
}
