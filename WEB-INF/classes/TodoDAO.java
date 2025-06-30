import bean.*;
import java.sql.*;

public class TodoDAO {
  // データベース接続情報
  private final String URL = "jdbc:mysql://localhost/todo_app";
  private final String USER = "root";
  private final String PASS = "pass";
  // データベース接続情報
  private Connection con = null;

  // データベース接続
  public void connect(){
    try{
      // ①DBに接続
      con = DriverManager.getConnection(URL, USER, PASS);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public TodoDTO select() {
    Statement stmt = null;
    ResultSet rs = null;
    TodoDTO tdto = new TodoDTO();
    String sql = "SELECT * FROM tasks";
    try{
      connect();
      // ②ステートメントを生成
      stmt = con.createStatement();
      // ③SQL文を実行
      rs = stmt.executeQuery(sql);
      // ④検索結果の処理
      while(rs.next()){
        TodoBean tb = new TodoBean();
        tb.setId(rs.getInt("id"));
        tb.setTask(rs.getString("task"));
        tb.setStatus(rs.getInt("status"));
        tdto.add(tb);
      }
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      try{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    disconnect();
    return tdto;
  }
  
  public int insert(int id, String task, int status) {
    String sql = "INSERT INTO tasks VALUES ("
                   + id + ", '" + task + "', " + status + ")";
    return executeSql(sql);
  }
  
  public int update(int id, String task, int status) {
    String sql = "UPDATE tasks SET id = " + id + ", task = '" + task
                   + "', status = " + status + " WHERE id = " + id;
    return executeSql(sql);
  }
  
  public int delete(int id) {
    String sql = "DELETE FROM tasks WHERE id = " + id;
    return executeSql(sql);
  }
  
  public int executeSql(String sql) {
    Statement stmt = null;
    ResultSet rs = null;
    int result = 0;
    try{
      connect();
      // ②ステートメントを生成
      stmt = con.createStatement();
      // ③SQL文を実行
      result = stmt.executeUpdate(sql);
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      try{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    disconnect();
    return result;
  }

  public void disconnect(){
    try{
      // ⑤DB接続断
      if(con != null) con.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}

