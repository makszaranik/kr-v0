package Queue.model;

import java.util.ArrayList;
import java.util.List;

public class Queue {
  private String name;
  private User creator;
  private List<User> users;
  private List<String> items;
  private boolean isBlocked;

  public Queue(String name, User creator) {
    this.name = name;
    this.creator = creator;
    this.users = new ArrayList<>();
    this.items = new ArrayList<>();
  }

  public boolean isBlocked() {
    return isBlocked;
  }
  public boolean isEmpty(){
    return items.isEmpty();
  }

  public void setBlocked(boolean blocked) {
    isBlocked = blocked;
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

  public void addUser(User user) {
    users.add(user);
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

  public void removeFirstItem(){
    items.remove(0);
  }

}
