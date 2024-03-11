package lab23.service.validator;

public class ParametersValidatorLab3 {

  public static boolean isValid(
      String aParamFrom, String aParamTo, String aParamStep,
      String bParamFrom, String bParamTo, String bParamStep,
      String cParamFrom, String cParamTo, String cParamStep,
      String dParamFrom, String dParamTo, String dParamStep
  ) {
    return isNumeric(aParamFrom) && isNumeric(aParamTo) && isNumeric(aParamStep) &&
        isNumeric(bParamFrom) && isNumeric(bParamTo) && isNumeric(bParamStep) &&
        isNumeric(cParamFrom) && isNumeric(cParamTo) && isNumeric(cParamStep) &&
        isNumeric(dParamFrom) && isNumeric(dParamTo) && isNumeric(dParamStep);
  }

  private static boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

}
