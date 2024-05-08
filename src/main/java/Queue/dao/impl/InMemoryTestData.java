package Queue.dao.impl;

import Queue.model.Queue;
import Queue.model.Role.RoleType;
import Queue.model.User;
import java.util.Arrays;
import java.util.List;

public class InMemoryTestData {

  public InMemoryTestData() {
  }

  public static void generateTo(InMemoryDatabase database) {
    database.users.clear();
    database.queues.clear();


    User max = new User(1, "Max", "max123", "123", RoleType.USER);
    User admin = new User(2, "admin", "admin", "admin", RoleType.ADMIN);
    User lisa = new User(3, "Lisa", "lisa123", "password2", RoleType.GUEST);

    List<User> users = Arrays.asList(max, admin, lisa);
    users.forEach(user -> {
      database.users.put(user.getUserId(), user);
    });


    Queue q1 = new Queue("IT Support", max);
    Queue q2 = new Queue("HR Services", admin);
    Queue q3 = new Queue("Facilities", lisa);

    List<Queue> queues = Arrays.asList(q1, q2, q3);
    queues.forEach(queue -> {
      database.queues.put(queue.getId(), queue);
    });


    q1.addItem("Install new software");
    q1.addItem("Update server");
    q1.addItem("Check email issues");

    q2.addItem("Recruit new developer");
    q2.addItem("Organize team building");

    q3.addItem("Repair office AC");
    q3.addItem("Replace light bulbs");

  }
}
