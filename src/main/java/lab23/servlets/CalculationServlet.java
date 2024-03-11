package lab23.servlets;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab23.service.formula.FormulaCalculatorFactory;
import lab23.service.formula.calculator.FormulaCalculator;
import lab23.service.validator.ParametersValidator;

@WebServlet("/CalculationServlet")
public class CalculationServlet extends HttpServlet {

  private FormulaCalculatorFactory formulaCalculatorFactory;

  @Override
  public void init() {
    this.formulaCalculatorFactory = FormulaCalculatorFactory.getInstance();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String formula = request.getParameter("formula");
    String aParam = request.getParameter("a");
    String bParam = request.getParameter("b");
    String cParam = request.getParameter("c");
    String dParam = request.getParameter("d");

    updateCookies(response, aParam, bParam, cParam, dParam);

    FormulaCalculator formulaCalculator;
    try {
      formulaCalculator = formulaCalculatorFactory.getFormula(formula);
    } catch (IllegalArgumentException exception) {
      request.setAttribute("formulaName", formula);
      request.getRequestDispatcher("/no_such_formula_error_page.jsp").forward(request, response);
      return;
    }
    boolean isValid = ParametersValidator.isValid(aParam, bParam, cParam, dParam);

    if (!isValid) {
      request.getRequestDispatcher("/error_page_incorrect_input.html").forward(request, response);
      return;
    }

    double a = Double.parseDouble(aParam);
    double b = Double.parseDouble(bParam);
    double c = Double.parseDouble(cParam);
    double d = Double.parseDouble(dParam);
    double result = formulaCalculator.calculate(a, b, c, d);

    if (Double.isNaN(result) || Double.isInfinite(result)) {
      request.getRequestDispatcher("/error_page_incorrect_result.html").forward(request, response);
      return;
    }

    request.setAttribute("result", result);
    request.getRequestDispatcher("/result_page.jsp").forward(request, response);
  }

  private void setCookie(HttpServletResponse response, String name, String value) {
    Cookie cookie = new Cookie(name, value);
    long seconds = TimeUnit.DAYS.toSeconds(2);
    cookie.setMaxAge((int) seconds);
    response.addCookie(cookie);
  }


  private void updateCookies(HttpServletResponse response,
      String aParam, String bParam, String cParam, String dParam) {
      setCookie(response, "a", aParam);
      setCookie(response, "b", bParam);
      setCookie(response, "c", cParam);
      setCookie(response, "d", dParam);
  }

}
