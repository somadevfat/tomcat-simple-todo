<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>
<jsp:useBean id ="tdto" scope="request" class="bean.TodoDTO" />
<jsp:useBean id ="msg" scope="request" class="java.lang.String" />
<html>
  <head>
    <title>表示画面</title>
  </head>
<body>
<h2><%= msg %></h2>
<table border="0">
  <tr>
    <th width="50">番号</th>
    <th width="50">タスク</th>
    <th width="50">ステータス</th>
  </tr>
<%
  for(int i = 0; i < tdto.size(); i++){
    TodoBean tb = tdto.get(i);
%>
  <tr>
    <td align="center"><%= tb.getId() %></td>
    <td align="center"><%= tb.getTask() %></td>
    <td align="center"><%= tb.getStatus() %></td>
  </tr>
<% } %>
</table><br />
<a href="/todotestdb/editTodo.html">戻る</a>
</body>
</html>
