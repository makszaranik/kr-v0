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

  public Institute createOrGetInstance(String instituteName) {
    if (isExists(instituteName)) {
      return institutes.get(instituteName);
    } else {
      Institute institute = new Institute(instituteName);
      institutes.put(instituteName, institute);
      return institute;
    }
  }

  public boolean isExists(String instituteName) {
    return institutes.containsKey(instituteName);
  }
}
