package Queue.services.DaoServices;

import Queue.model.User;
import java.util.Optional;

public interface AbstractUserDaoService {
  User findUserByUsername(String username);
  void createUser(User user);
  void deleteUser(String username);
  boolean isExist(String username);
}
