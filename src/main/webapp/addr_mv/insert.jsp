<%@page import="addr.mv.model.AddrDTO"%>
<%@ page contentType="text/html;charset=utf-8" import="addr.mv.model.AddrDTO"%>
<jsp:useBean id="addrDAO" class="addr.mv.model.AddrDAO" scope="application"/>
<jsp:useBean id="dto" class="addr.mv.model.AddrDTO"/>
<!-- 
<jsp:setProperty name="dto" property="name" value="name"/>
<jsp:setProperty name="dto" property="addr" param="addr"/>
 -->
<jsp:setProperty property="dto" name="*"/>

<%
	boolean flag = addrDAO.insert(dto); 
%>

<script>
    if(<%=flag%>){
		alert("입력성공(with DBCP)");
	}else{
		alert("입력실패(with DBCP)");
	}
	location.href="list.jsp";
</script>