package bean;

import java.io.Serializable;

public class TodoBean implements Serializable{
  private int id;
  private String task;
  private int status;
  
  public void setId(int id){
    this.id = id;
  }
  public void setTask(String task){
    this.task = task;
  }
  public void setStatus(int status){
    this.status = status;
  }
  public int getId(){
    return id;
  }
  public String getTask(){
    return task;
  }
  public int getStatus(){
    return status;
  }
}

