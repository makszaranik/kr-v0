package lab23.service.formula.calculator;

public class ThirdFormulaCalculator implements FormulaCalculator {

  @Override
  public double calculate(double a, double b, double c, double d) {
    double cosB = Math.cos(b);
    double sqrtA = Math.sqrt(a);
    double sinSqrtA = Math.sin(sqrtA);
    double logC = Math.log(c);
    double expD = Math.exp(d);
    double den = 2 * logC + expD;
    return  (cosB + sinSqrtA) / den;
  }
}
