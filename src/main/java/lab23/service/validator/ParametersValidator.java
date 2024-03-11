package lab23.service.validator;

public class ParametersValidator {

  public static boolean isValid(String aParam, String bParam, String cParam, String dParam) {
    return isNumeric(aParam) && isNumeric(bParam) && isNumeric(cParam) && isNumeric(dParam);
  }

  private static boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

}
