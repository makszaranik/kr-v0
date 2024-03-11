package lab23.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import lab23.service.MemberPageGenerationService;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {

  private MemberPageGenerationService service;

  @Override
  public void init() {
    this.service = MemberPageGenerationService.getInstance();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    try (PrintWriter out = response.getWriter()) {
      String memberId = request.getParameter("memberId");
      String page = service.getPage(memberId);
      out.println(page);
    }
  }

}