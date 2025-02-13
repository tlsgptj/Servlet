<%@ page contentType="text/html;charset=utf-8" import="board.domain.Board" %>
<%
    Board board = (Board) request.getAttribute("board");
%>
<html>
<head>
    <title>상세 글 보기</title>
    <meta charset='utf-8'>
    <script language="javascript">
       function check() {
           for (var i = 0; i < document.input.elements.length; i++) {
              if (document.input.elements[i].value == "") {
                 alert("모든 값을 입력 하셔야 합니다.");
                 return false;
              }
           }
           document.input.submit();
       }
    </script>
    <style>
        table, th, td {
           border: 1px solid black;
           border-collapse: collapse;
        }
        th, td {
           padding: 5px;
        }
        a { text-decoration: none }
    </style>
</head>
<body onload="document.input.writer.focus()">
    <center>
       <hr width="600" size="2" noshade>
          <h2>상세 글 보기 및 수정</h2>
          <a href='board.do'>글목록</a>
       <hr width="600" size="2" noshade>
    </center>
    <form name="input" method="post" action="board.do">
       <input type="hidden" name="m" value="update">
       <input type="hidden" name="seq" value="<%=board.getSeq()%>">
       <table border="1" width="600" align="center" cellpadding="3" cellspacing="1">
          <tr>
             <td width="30%" align="center">글쓴이</td>
             <td align="center"><input type="text" name="writer" size="60" value="<%=board.getWriter()%>"></td>
          </tr>
          <tr>
             <td align="center">이메일</td>
             <td align="center"><input type="text" name="email" size="60" value="<%=board.getEmail()%>"></td>
          </tr>
          <tr>
             <td align="center">글제목</td>
             <td align="center"><input type="text" name="subject" size="60" value="<%=board.getSubject()%>"></td>
          </tr>
          <tr>
             <td align="center">글내용</td>
             <td align="center"><textarea name="content" rows="5" cols="53"><%=board.getContent()%></textarea></td>
          </tr>
          <tr>
             <td colspan="2" align="center">
                <input type="button" value="전송" onclick="check()">
                <input type="button" value="다시입력" onclick="history.back();">
             </td>
          </tr>
       </table>
       <br>
       <hr width="600" size="2" noshade>
    </form>
</body>
</html>
