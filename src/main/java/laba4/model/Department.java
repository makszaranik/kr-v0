package laba4.model;

import java.util.ArrayList;

public class Department {
  private String name;
  private ArrayList<Student> listStudents;

  public Department(String name, ArrayList<Student> listStudents){
    this.name = name;
    this.listStudents = listStudents;
  }

  public Department(String name, Student student){
    this.name = name;
    this.listStudents = new ArrayList<>();
    this.listStudents.add(student);
  }

  public Department(String name){
    this.name = name;
    this.listStudents = new ArrayList<>();
  }

  public ArrayList<Student> getListStudents() {
    return listStudents;
  }

  public String getName() {
    return name;
  }

  public int getAmountStudents(){
    return this.listStudents.size();
  }

  public void addStudents(Student... students){
    for(Student student : students)
      listStudents.add(student);
  }

  public void addStudent(Student student) {
    listStudents.add(student);
  }

}