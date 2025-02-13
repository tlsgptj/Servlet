<%@ page contentType="text/html;charset=utf-8" 
    import="addr.mv.model.BoardDAO, .mv.model.BoardDTO"%>
<%
    String strSeq = request.getParameter("seq");
    if (strSeq == null || strSeq.isEmpty()) {
        out.println("<script>alert('잘못된 접근입니다.'); history.back();</script>");
        return;
    }

    long seq = Long.parseLong(strSeq);
    BoardDTO dto = boardDAO.get(seq);

    if (dto == null) {
        out.println("<script>alert('게시글을 찾을 수 없습니다.'); history.back();</script>");
        return;
    }
%>

<html>
<head>
    <title>게시글 수정</title>
    <meta charset='utf-8'>
</head>
<body>
    <center>
        <h2>게시글 수정</h2>
        <form method="post" action="update.jsp">
            <input type="hidden" name="seq" value="<%=dto.getSeq()%>">
            <table border="1" width="600">
                <tr>
                    <td width="30%" align="center">글쓴이</td>
                    <td><input type="text" name="writer" value="<%=dto.getWriter()%>" size="60"></td>
                </tr>
                <tr>
                    <td align="center">이메일</td>
                    <td><input type="text" name="email" value="<%=dto.getEmail()%>" size="60"></td>
                </tr>
                <tr>
                    <td align="center">글제목</td>
                    <td><input type="text" name="subject" value="<%=dto.getSubject()%>" size="60"></td>
                </tr>
                <tr>
                    <td align="center">글내용</td>
                    <td><textarea name="content" rows="5" cols="53"><%=dto.getContent()%></textarea></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="수정하기">
                        <input type="button" value="취소" onclick="history.back()">
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
