import bean.*;
import java.sql.*;

public class StudentDAO3 {
  private final String URL = "jdbc:mysql://localhost/sampledb";
  private final String USER = "root";
  private final String PASS = "pass";
  private Connection con = null;

  public void connect(){
    try{
      // ①DBに接続
      con = DriverManager.getConnection(URL, USER, PASS);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public StudentDTO select() {
    Statement stmt = null;
    ResultSet rs = null;
    StudentDTO sdto = new StudentDTO();
    String sql = "SELECT * FROM student";
    try{
      connect();
      // ②ステートメントを生成
      stmt = con.createStatement();
      // ③SQL文を実行
      rs = stmt.executeQuery(sql);
      // ④検索結果の処理
      while(rs.next()){
        StudentBean sb = new StudentBean();
        sb.setNo(rs.getInt("no"));
        sb.setName(rs.getString("name"));
        sb.setScore(rs.getInt("score"));
        sdto.add(sb);
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
    return sdto;
  }
  
  public int insert(int no, String name, int score) {
    String sql = "INSERT INTO student VALUES ("
                   + no + ", '" + name + "', " + score + ")";
    return executeSql(sql);
  }
  
  public int update(int no, String name, int score) {
    String sql = "UPDATE student SET no = " + no + ", name = '" + name
                   + "', score = " + score + " WHERE no = " + no;
    return executeSql(sql);
  }
  
  public int delete(int no) {
    String sql = "DELETE FROM student WHERE no = " + no;
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

