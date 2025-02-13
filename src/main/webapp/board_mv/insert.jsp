<%@ page contentType="text/html;charset=utf-8" import="addr.mv.model.BoardDAO, addr.mv.model.BoardDTO" %>
<jsp:useBean id="boardDAO" class="addr.mv.model.BoardDAO" scope="application"/> <!-- 가장 상위 application 스코프는 4가지 -->
<!-- session --> <!-- request는 적합하지 않음, 객체가 많이 만들어짐 --> <!-- request랑 똑같음 PageScope -->
<jsp:useBean id="dto" class="addr.mv.model.BoardDTO" scope="request"/>
<jsp:setProperty name="dto" property="*"/>

<%
    boolean flag = boardDAO.insert(dto); 
%>

<script>
    if(<%=flag%>){
        alert("입력성공(with DBCP)");
    }else{
        alert("입력실패(with DBCP)");
    }
    location.href="list.jsp";
</script>

<!-- a.jsp, b.jsp, c.jsp 포워드 vs 리다이렉트 -->
<!-- < Scope 종류(4가지) : 서버측 메모리 '공간' 객체 >
- application
- session
- request
- page -->