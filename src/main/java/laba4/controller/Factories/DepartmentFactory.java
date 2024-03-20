package laba4.controller.Factories;

import laba4.model.Department;
import laba4.model.Institute;

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

  public Department createOrGetInstance(String departmentName, String instituteName) {
    Institute institute = instituteFactory.createOrGetInstance(instituteName);
    if (isExists(departmentName)) {
      return departments.get(departmentName);
    } else {
      Department department = new Department(departmentName);
      departments.put(departmentName, department);
      return department;
    }
  }

  public boolean isExists(String departmentName) {
    return departments.containsKey(departmentName);
  }

  public Set<String> getAllDepartmentNames() {
    return departments.keySet();
  }
}
