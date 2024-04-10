package Queue.dao;

public class UserDAOFactory {
    private static UserDAOFactory instance;

    private UserDAOFactory(){};

    public static UserDAOFactory getInstance(){
        if(instance == null){
          instance = new UserDAOFactory();
        }
        return instance;
    }

    public UserDAO getUserDAO(){
        return UserDataBase.getInstance();
    }
}
