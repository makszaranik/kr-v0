package laba4.controller.Factories;

import laba4.model.Department;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DepartmentFactory {
  private static DepartmentFactory instance;
  private Map<String, Department> departments;
  private InstituteFactory instituteFactory;

  private DepartmentFactory() {
    departments = new HashMap<>();
    instituteFactory = InstituteFactory.getInstance();
  }

  public static DepartmentFactory getInstance() {
    if (instance == null) {
      instance = new DepartmentFactory();
    }
    return instance;
  }

  public Department getDepartmentInstance(String departmentName, String instituteName) {
    if (!instituteFactory.instituteExists(instituteName)) {
      throw new IllegalArgumentException("Institute with name " + instituteName + " does not exist.");
    }

    if (departments.containsKey(departmentName)) {
      return departments.get(departmentName);
    } else {
      Department department = new Department(departmentName);
      departments.put(departmentName, department);
      return department;
    }
  }

  public Set<String> getAllDepartmentNames() {
    return departments.keySet();
  }
}
