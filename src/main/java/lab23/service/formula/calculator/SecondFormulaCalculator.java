package lab23.service.formula.calculator;

public class SecondFormulaCalculator implements FormulaCalculator {

  @Override
  public double calculate(double a, double b, double c, double d) {
    double tanA = Math.tan(a);
    double absoluteArccosB = Math.abs(Math.acos(b));
    double sqrtResult = Math.sqrt(tanA / absoluteArccosB);
    double expDifference = Math.exp(c - a);
    double sinhD = Math.sinh(d);
    double powerResult = Math.pow(expDifference * sinhD, 1.0 / 3.0);
    return  2 * sqrtResult - 3 * powerResult;
  }

}
