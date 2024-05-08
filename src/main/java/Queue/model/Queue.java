package Queue.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Queue {
  private String name;
  private User creator;
  private List<String> items;
  private boolean isBlocked;
  private Integer id;

  public Queue(String name, User creator) {
    this.name = name;
    this.creator = creator;
    this.items = new ArrayList<>();
    this.id = this.name.hashCode();
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


  public void setId(Integer id){
      this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


  public void setCreator(User creator) {this.creator = creator;}

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
