package laba4.model;

public class Student{
  private String name;
  private String surname;
  private int numberScorecard;
  private int averageMark;


  public Student(String name, String surname){
    this.name = name;
    this.surname = surname;
    this.numberScorecard = 0;
    this.averageMark = 0;
  }

  public Student(String name, String surname, int numberScorecard, int averageMark){
    this.name = name;
    this.surname = surname;
    if(numberScorecard >= 0 && averageMark >= 0){
      this.numberScorecard = numberScorecard;
      this.averageMark = averageMark;
    }else{
      this.numberScorecard = 0;
      this.averageMark = 0;
    }
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }
  public String getInfo(){
    return this.name + " " + this.surname;
  }

  public int getAverageMark() {
    return averageMark;
  }

  public int getNumberScorecard() {
    return numberScorecard;
  }


  @Override
  public boolean equals(Object obj) {
    Student other = (Student)obj;
    return this.name.equals(other.name)
        && this.surname.equals(other.surname)
        && this.numberScorecard == other.numberScorecard
        && this.averageMark == other.averageMark;
  }

}