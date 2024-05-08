package Queue.model;

import java.util.Objects;
import lombok.Getter;


@Getter
public class User {
  private String username;
  private String password;
  private int id;
  private String passwordHash;
  private Role.RoleType roleType;


  public User(Integer userId, String username, String password, String passwordHash, Role.RoleType roleType) {
    this.id = userId;
    this.username = username;
    this.password = password;
    this.passwordHash = passwordHash;
    this.roleType = roleType;
  }

  public User(String username, String password, Role.RoleType roleType) {
    this.username = username;
    this.password = password;
    this.roleType = roleType;
  }


  public void setUsername(String username) {
    this.username = username;
  }

  public int getUserId(){ return this.id;};

  public void setUserId(Integer id) {this.id = id;}


  public Role.RoleType getRole() {return roleType;}
  public void setRole(Role.RoleType role) {this.roleType = role;}


  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(username, user.username) && Objects.equals(password,
        user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

}