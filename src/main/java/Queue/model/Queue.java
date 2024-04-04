package Queue.model;

import java.util.ArrayList;
import java.util.List;

public class Queue {
  private String name;
  private User creator;
  private List<User> users;
  private List<String> items;

  public Queue(String name, User creator) {
    this.name = name;
    this.creator = creator;
    this.users = new ArrayList<>();
    this.items = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public List<User> getUsers() {
    return users;
  }

  public void addUser(User user) {
    users.add(user);
  }

  public void removeUser(User user) {
    users.remove(user);
  }

  public boolean isCreator(User user) {
    return this.creator.equals(user);
  }


  public List<String> getItems() {
    return items;
  }

  public void addItem(String item) {
    items.add(item);
  }

  public void removeItem(String item) {
    items.remove(item);
  }
}
