package Queue.controller;

import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import Queue.services.NameValidatorService.NameValidatorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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


  @Override
  @SneakyThrows
  public void init(){
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
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
    switch (selectedAction){
      case "Add" :
        AddItemInQueue(request, response);
        break;
      case "RemoveItem":
        RemoveItemFromQueue(request, response);
        break;
      case "RemoveFirstItem":
        RemoveItemFromBeginOfQueue(request, response);
        break;
      case "BlockOrUnblockQueue":
        BlockOrUnBlockQueue(request, response);
        break;
      case "DeleteQueue":
        DeleteQueue(request, response);
      default:
        break;
    }


    }

    @SneakyThrows
    protected void AddItemInQueue(HttpServletRequest request, HttpServletResponse response){
      AddIUserInQueueServlet addIUserInQueueServlet = new AddIUserInQueueServlet();
      addIUserInQueueServlet.init();
      addIUserInQueueServlet.doPost(request, response);

    }

    @SneakyThrows
    protected void RemoveItemFromQueue(HttpServletRequest request, HttpServletResponse response){
      RemoveItemFromQueueServlet removeItemFromQueueServlet = new RemoveItemFromQueueServlet();
      removeItemFromQueueServlet.init();
      removeItemFromQueueServlet.doPost(request, response);
    }

    @SneakyThrows
    protected void RemoveItemFromBeginOfQueue(HttpServletRequest request, HttpServletResponse response){
        RemoveItemFromQueueServlet removeItemFromQueueServlet = new RemoveItemFromQueueServlet();
        removeItemFromQueueServlet.init();
        removeItemFromQueueServlet.doPost(request, response);
    }

    @SneakyThrows
    protected void BlockOrUnBlockQueue(HttpServletRequest request, HttpServletResponse response){
      BlockOrUnblockQueueServlet blockOrUnblockQueueServlet = new BlockOrUnblockQueueServlet();
      blockOrUnblockQueueServlet.init();
      blockOrUnblockQueueServlet.doPost(request, response);
    }

    @SneakyThrows
    protected void DeleteQueue(HttpServletRequest request, HttpServletResponse response){
      DeleteQueueServlet deleteQueueServlet = new DeleteQueueServlet();
      deleteQueueServlet.init();
      deleteQueueServlet.doPost(request, response);
    }


}
