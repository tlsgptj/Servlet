<%@ page contentType="text/html;charset=utf-8" import="board.mvc.model.BoardService, board.domain.Board"%>

<jsp:useBean id="boardService" class="board.mvc.model.BoardService" scope="application"/>
<jsp:useBean id="dto" class="board.domain.Board" scope="request"/>
<jsp:setProperty name="dto" property="*" />

<%
    String strSeq = request.getParameter("seq");
    if (strSeq == null || strSeq.isEmpty()) {
        out.println("<script>alert('잘못된 접근입니다.'); history.back();</script>");
        return;
    }
    
    dto.setSeq(Long.parseLong(strSeq));
    boolean flag = boardService.updateS(dto);
%>

<script>
    if (<%=flag%>) {
        alert("수정 성공!");
        location.href="board.do";
    } else {
        alert("수정 실패!");
        history.back();
    }
</script>
