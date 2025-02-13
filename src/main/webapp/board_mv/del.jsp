<%@ page contentType="text/html;charset=utf-8" import="board.mvc.model.BoardService"%>

<%
    String strSeq = request.getParameter("seq");
    long seq = Long.parseLong(strSeq);

    // BoardService 인스턴스 생성
    BoardService boardService = BoardService.getInstance();
    boolean flag = boardService.deleteS(seq);
%>
<script>
    if(<%=flag%>){
        alert("삭제 성공(with DBCP)");
    } else {
        alert("삭제 실패(with DBCP)");
    }
    location.href="list.jsp";
</script>
