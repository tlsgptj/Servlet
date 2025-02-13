<%@ page contentType="text/html;charset=utf-8" import="board.mvc.model.BoardService, board.domain.Board" %>
<jsp:useBean id="boardService" class="board.mvc.model.BoardService" scope="application"/> <!-- 가장 상위 application 스코프는 4가지 -->
<!-- session --> <!-- request는 적합하지 않음, 객체가 많이 만들어짐 --> <!-- request랑 똑같음 PageScope -->
<jsp:useBean id="dto" class="board.domain.Board" scope="request"/>
<jsp:setProperty name="dto" property="*"/>

<%
    boolean flag = boardService.insertS(dto);
%>

<script>
    if(<%=flag%>){
        alert("입력 성공(with DBCP)");
    } else {
        alert("입력 실패(with DBCP)");
    }
    location.href="board.do";
</script>
