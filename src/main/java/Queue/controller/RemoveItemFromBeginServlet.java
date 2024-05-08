package Queue.controller;

import Queue.services.NameValidator.NameValidator;
import Queue.model.Queue;
import Queue.model.User;

import Queue.services.DaoServices.AbstractQueueDaoService;
import Queue.services.DaoServices.impl.ServiceFactory;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.SneakyThrows;

@WebServlet("/RemoveFromBegin")
public class RemoveItemFromBeginServlet extends HttpServlet {

  private AbstractQueueDaoService queueDaoService;

  @Override
  @SneakyThrows
  public void init(){
    super.init();
    queueDaoService = ServiceFactory.getQueueDaoService();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");


    if (user != null) {
      String selectedQueueName = request.getParameter("selectedQueue");

      if(!NameValidator.isValidName(selectedQueueName)){
        request.getRequestDispatcher("/EmptyFormSubmitted.jsp").forward(request, response);
        return;
      }

      Queue selectedQueue = queueDaoService.findQueueByName(selectedQueueName);

      if (selectedQueue != null) {
        if (selectedQueue.isBlocked()) {
          request.getRequestDispatcher("/QueueIsBlocked.jsp").forward(request, response);
          return;
        }
        selectedQueue.removeFirstItem();
        request.getRequestDispatcher("ItemSuccessfullyDeleted.jsp").forward(request, response);
      }else {
        request.getRequestDispatcher("/NothingToShowQueueIsEmpty.jsp").forward(request, response);
      }
    }
  }
}
