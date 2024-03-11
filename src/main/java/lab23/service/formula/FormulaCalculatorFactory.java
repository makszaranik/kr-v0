package lab23.service.formula;

import lab23.service.formula.calculator.FirstFormulaCalculator;
import lab23.service.formula.calculator.FormulaCalculator;
import lab23.service.formula.calculator.ThirdFormulaCalculator;
import lab23.service.formula.calculator.SecondFormulaCalculator;

public class FormulaCalculatorFactory {

  private static FormulaCalculatorFactory instance;

  public static FormulaCalculatorFactory getInstance() {
    if (instance == null) {
      instance = new FormulaCalculatorFactory();
    }
    return instance;
  }

  private FormulaCalculatorFactory() {}

  public FormulaCalculator getFormula(String formula) throws IllegalArgumentException {
    if (formula == null) {
      throw new IllegalArgumentException("formula with name " + formula + " does not exist");
    }
    return switch (formula) {
      case "form1" -> new FirstFormulaCalculator();
      case "form2" -> new SecondFormulaCalculator();
      case "form3" -> new ThirdFormulaCalculator();
      default -> throw new IllegalArgumentException("formula with name " + formula + " does not exist");
    };
  }
}
