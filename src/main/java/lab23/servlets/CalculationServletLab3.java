package lab23.servlets;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab23.service.validator.ParametersValidatorLab3;
import lab23.service.formula.FormulaCalculatorFactory;
import lab23.service.formula.calculator.FormulaCalculator;

@WebServlet("/CalculationServletLab3")
public class CalculationServletLab3 extends HttpServlet {

  private FormulaCalculatorFactory formulaCalculatorFactory;

  @Override
  public void init() {
    this.formulaCalculatorFactory = FormulaCalculatorFactory.getInstance();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalArgumentException {
    String aParamFrom = request.getParameter("aFrom");
    String aParamTo = request.getParameter("aTo");
    String aParamStep = request.getParameter("aStep");

    String bParamFrom = request.getParameter("bFrom");
    String bParamTo = request.getParameter("bTo");
    String bParamStep = request.getParameter("bStep");

    String cParamFrom = request.getParameter("cFrom");
    String cParamTo = request.getParameter("cTo");
    String cParamStep = request.getParameter("cStep");

    String dParamFrom = request.getParameter("dFrom");
    String dParamTo = request.getParameter("dTo");
    String dParamStep = request.getParameter("dStep");

    updateCookies(response, aParamFrom, aParamTo, aParamStep,
        bParamFrom, bParamTo, bParamStep,
        cParamFrom, cParamTo, cParamStep,
        dParamFrom, dParamTo, dParamStep
    );

    String formulaName = request.getParameter("formula");
    FormulaCalculator formulaCalculator = formulaCalculatorFactory.getFormula(formulaName);

    boolean isValid = ParametersValidatorLab3.isValid(
        aParamFrom, aParamTo, aParamStep,
        bParamFrom, bParamTo, bParamStep,
        cParamFrom, cParamTo, cParamStep,
        dParamFrom, dParamTo, dParamStep
    );

    if (!isValid) {
      request.getRequestDispatcher("/error_page_incorrect_input.html").forward(request, response);
      return;
    }

    Integer aFrom = Integer.parseInt(aParamFrom);
    Integer aTo = Integer.parseInt(aParamTo);
    Integer aStep = Integer.parseInt(aParamStep);

    Integer bFrom = Integer.parseInt(bParamFrom);
    Integer bTo = Integer.parseInt(bParamTo);
    Integer bStep = Integer.parseInt(bParamStep);

    Integer cFrom = Integer.parseInt(cParamFrom);
    Integer cTo = Integer.parseInt(cParamTo);
    Integer cStep = Integer.parseInt(cParamStep);

    Integer dFrom = Integer.parseInt(dParamFrom);
    Integer dTo = Integer.parseInt(dParamTo);
    Integer dStep = Integer.parseInt(dParamStep);



    request.setAttribute("aFrom", aFrom);
    request.setAttribute("aTo", aTo);
    request.setAttribute("aStep", aStep);

    request.setAttribute("bFrom", bFrom);
    request.setAttribute("bTo", bTo);
    request.setAttribute("bStep", bStep);

    request.setAttribute("cFrom", cFrom);
    request.setAttribute("cTo", cTo);
    request.setAttribute("cStep", cStep);

    request.setAttribute("dFrom", dFrom);
    request.setAttribute("dTo", dTo);
    request.setAttribute("dStep", dStep);

    request.setAttribute("formula", formulaCalculator);

    request.getRequestDispatcher("/resultTableLab3.jsp").forward(request, response);
  }

  private void setCookie(HttpServletResponse response, String name, String value) {
    Cookie cookie = new Cookie(name, value);
    long seconds = TimeUnit.DAYS.toSeconds(2);
    cookie.setMaxAge((int) seconds);
    response.addCookie(cookie);
  }

  private void updateCookies(HttpServletResponse response,
      String aParamFrom, String aParamTo, String aParamStep,
      String bParamFrom, String bParamTo, String bParamStep,
      String cParamFrom, String cParamTo, String cParamStep,
      String dParamFrom, String dParamTo, String dParamStep
  ) {
    setCookie(response, "aFrom", aParamFrom);
    setCookie(response, "aTo", aParamTo);
    setCookie(response, "aStep", aParamStep);

    setCookie(response, "bFrom", bParamFrom);
    setCookie(response, "bTo", bParamTo);
    setCookie(response, "bStep", bParamStep);

    setCookie(response, "cFrom", cParamFrom);
    setCookie(response, "cTo", cParamTo);
    setCookie(response, "cStep", cParamStep);

    setCookie(response, "dFrom", dParamFrom);
    setCookie(response, "dTo", dParamTo);
    setCookie(response, "dStep", dParamStep);
  }
}
