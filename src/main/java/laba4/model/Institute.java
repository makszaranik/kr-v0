package laba4.model;

import java.util.ArrayList;
import java.util.List;

public class Institute {
  private String name;
  private ArrayList<Department> listDepartments;

  public Institute(String name, ArrayList<Department> listDepartments) {
    this.name = name;
    this.listDepartments = listDepartments;
  }

  public Institute(String name, Department... departments) {
    this.name = name;
    this.listDepartments = new ArrayList<>();

    for (Department department : departments) {
      listDepartments.add(department);
    }
  }

  public void addDepartment(Department department) {
    this.listDepartments.add(department);
  }

  // Task 1
  public int amountStudents() {
    int result = 0;
    for (Department department : listDepartments) {
      result += department.getAmountStudents();
    }
    return result;
  }

  // Task 2
  public String maxStudentDepartment() {
    if (listDepartments.isEmpty()) {
      return "No departments in the institute.";
    }

    Department maxDepartment = listDepartments.get(0);

    for (Department department : listDepartments) {
      if (department.getAmountStudents() > maxDepartment.getAmountStudents()) {
        maxDepartment = department;
      }
    }

    return "Department with the most students: " + maxDepartment.getName();
  }

  // Task 3
  public List<String> getStudentsInRange(double startRange, double endRange) {
    List<String> result = new ArrayList<>();

    for (Department department : listDepartments) {
      List<Student> departmentStudents = department.getListStudents();
      for (Student student : departmentStudents) {
        double averageGrade = student.getAverageMark();
        if (averageGrade >= startRange && averageGrade <= endRange) {
          result.add(student.getInfo());
        }
      }
    }

    return result;
  }
}
