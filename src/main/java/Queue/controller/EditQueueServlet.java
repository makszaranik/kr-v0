package Queue.controller;

import Queue.model.Role.RoleType;
import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.Factories.ServiceFactory;
import Queue.services.RoleConfiguratorService.AbstractRoleConfiguratorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.Queue;
import Queue.model.User;
import lombok.SneakyThrows;

@WebServlet("/EditQueue")
public class EditQueueServlet extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;
  private AbstractRoleConfiguratorService roleConfiguratorService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    this.queueDaoService = ServiceFactory.getQueueDaoService();
    this.roleConfiguratorService = ServiceFactory.getRoleConfiguratorService();
  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("LoginPage.jsp");
      return;
    }
    List<Queue> allQueues = new ArrayList<>(queueDaoService.getAllQueues());
    request.setAttribute("queues", allQueues);
    request.getRequestDispatcher("EditSelectedQueue.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String selectedAction = request.getParameter("action");
      String selectedQueueName = request.getParameter("selectedQueue");
      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);
      HttpSession session = request.getSession();
      User user = (User) session.getAttribute("user");


      if(roleConfiguratorService.getConfiguration(user, selectedQueue) != RoleType.OWNER){
        System.out.println(user.getRoleType());
        response.sendRedirect("PermissionDenied.jsp");
        return;
      }

      switch (selectedAction){
        case "Add" :
          request.getRequestDispatcher("/AddToQueueServlet").forward(request, response);
          break;
        case "RemoveItem":
          request.getRequestDispatcher("/RemoveFromQueue").forward(request, response);
          break;
        case "RemoveFirstItem":
          request.getRequestDispatcher("/RemoveFromBegin").forward(request, response);
          break;
        case "BlockOrUnblockQueue":
          request.getRequestDispatcher("/BlockUnblockQueue").forward(request, response);
          break;
        case "DeleteQueue":
          request.getRequestDispatcher("/DeleteQueueServlet").forward(request, response);
        default:
          break;
      }
  }
}
