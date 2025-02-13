<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, board.domain.Board, board.mvc.model.BoardService"%>

<meta charset='utf-8'>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td {
        padding: 5px;
    }
    a { text-decoration:none }
</style>
<center>
<h1>
    Board List(Model2)
</h1>
<a href='../'>Home</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href='board.do?m=input'>Write</a>
<table border='1' cellpadding='7' cellspacing='2' width='70%'>
    <tr>
        <th>WRITER</th>
        <th>EMAIL</th>
        <th>SUBJECT</th>
        <th>CONTENT</th>
        <th>RDATE</th>
        <th>Delete</th>
    </tr>
<%
    BoardService boardService = BoardService.getInstance();
    ArrayList<Board> list = boardService.listS();
    if (list != null && list.size() != 0) {
        for (Board dto : list) {
%>
            <tr>
                <td align='center'><%=dto.getWriter()%></td>
                <td><%=dto.getEmail()%></td>
                <td><a href='board.do?m=get&seq=<%=dto.getSeq()%>'><%=dto.getSubject()%></a></td>
                <td><%=dto.getContent()%></td>
                <td><%=dto.getRdate()%></td>
                <td align='center'><a href='board.do?m=del&seq=<%=dto.getSeq()%>'>삭제</a></td>
            </tr>
<%
        }
    }
%>
</table>
</center>
