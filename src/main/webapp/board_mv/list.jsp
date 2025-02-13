<%@ page contentType="text/html;charset=utf-8" 
    import="java.util.ArrayList, addr.mv.model.BoardDAO, addr.mv.model.BoardDTO"%>
<jsp:useBean id="BoardDAO" class="addr.mv.model.BoardDAO" scope="application"/>

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
    Board List(Model1)
</h1>
<a href='../'>Home</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href='input.jsp'>Write</a>
<table border='1' cellpadding='7' cellspacing='2' width='70%'>
    <tr>
        <th>SEQ</th>
        <th>WRITER</th>
        <th>EMAIL</th>
        <th>SUBJECT</th>
        <th>CONTENT</th>
        <th>RDATE</th>
        <th>Delete</th>
    </tr>
<%
    ArrayList<BoardDTO> list = BoardDAO.list();
    if (list != null) {
        if (list.size() != 0) {
            for (BoardDTO dto : list) {
%>
                <tr>
                    <td align='center'><%=dto.getSeq()%></td>
                    <td><%=dto.getWriter()%></td>
                    <td><%=dto.getEmail()%></td>
                    <td><a href='edit.jsp?seq=<%=dto.getSeq()%>'><%=dto.getSubject()%></a></td>
                    <td><%=dto.getContent()%></td>
                    <td><%=dto.getRdate()%></td>
                    <td align='center'><a href='del.jsp?seq=<%=dto.getSeq()%>'>Delete</a></td>
                </tr>
<%
            }
        }
    }
%> 
</table>
</center>
