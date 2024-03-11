package lab23.service.formula.calculator;

public class FirstFormulaCalculator implements FormulaCalculator {

  @Override
  public double calculate(double a, double b, double c, double d) {
    double absoluteValue = Math.abs(b / a);
    double logAbsoluteValue = Math.log( absoluteValue );
    double innerExpression = Math.sin(c) + Math.exp(d);
    double sqrtInnerExpression = Math.sqrt( innerExpression );
    return  3 * (logAbsoluteValue + sqrtInnerExpression);
  }

}
