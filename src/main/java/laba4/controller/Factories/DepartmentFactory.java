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

    // Проверяем, существует ли уже департамент с указанным именем
    if (departments.containsKey(departmentName)) {
      // Если департамент уже существует, просто возвращаем его
      return departments.get(departmentName);
    } else {
      // Иначе создаем новый департамент и добавляем его в мапу департаментов
      Department department = new Department(departmentName);
      departments.put(departmentName, department);
      return department;
    }
  }

  public Set<String> getAllDepartmentNames() {
    return departments.keySet();
  }
}
