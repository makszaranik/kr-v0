package laba4.controller.Factories;

import java.util.HashMap;
import java.util.Map;
import laba4.model.Institute;

public class InstituteFactory {
  private static InstituteFactory instance;
  private Map<String, Institute> institutes;

  private InstituteFactory() {
    institutes = new HashMap<>();
  }

  public static InstituteFactory getInstance() {
    if (instance == null) {
      instance = new InstituteFactory();
    }
    return instance;
  }

  public Institute createInstitute(String instituteName) {
    if (institutes.containsKey(instituteName)) {
      throw new IllegalArgumentException("Institute with name " + instituteName + " already exists.");
    }
    Institute institute = new Institute(instituteName);
    institutes.put(instituteName, institute);
    return institute;
  }

  public boolean instituteExists(String instituteName) {
    return institutes.containsKey(instituteName);
  }
}
