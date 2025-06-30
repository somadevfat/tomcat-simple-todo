import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import bean.TodoDTO;

@WebServlet("/editTodo")
public class EditTodoServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    String msg = "タスク情報を表示します";
    // 入力値(btn)を取得
    req.setCharacterEncoding("utf-8");
    String btn = req.getParameter("btn");
    // TodoDAOオブジェクトを生成
    TodoDAO tdao = new TodoDAO();
    // 各ボタンによる分岐処理
    if(btn.equals("追加")){
      int id = Integer.parseInt(req.getParameter("id"));
      String task = req.getParameter("task");
      int status = Integer.parseInt(req.getParameter("status"));
      tdao.insert(id, task, status);
      msg = "番号" + id + "のタスクを追加しました";
    } else if(btn.equals("修正")) {
      int id = Integer.parseInt(req.getParameter("id"));
      String task = req.getParameter("task");
      int status = Integer.parseInt(req.getParameter("status"));
      tdao.update(id, task, status);
      msg = "番号" + id + "のタスクを修正しました";
    } else if(btn.equals("削除")) {
      int id = Integer.parseInt(req.getParameter("id"));
      tdao.delete(id);
      msg = "番号" + id + "のタスクを削除しました";
    }
    // 全件選択
    TodoDTO tdto = tdao.select();
    // リクエストスコープにDTOとmsgを格納
    req.setAttribute("tdto", tdto);
    req.setAttribute("msg", msg);
    // JSPにフォワード
    RequestDispatcher rd = req.getRequestDispatcher("/editTodo.jsp");
    rd.forward(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    doPost(req, res);
  }
}

