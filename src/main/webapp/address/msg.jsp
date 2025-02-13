<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
 <%
	boolean flag = (Boolean)request.getAttribute("flag"); 
%>

<script>
    if(<%=flag%>){
		alert("입력성공(with MVC)");
	}else{
		alert("입력실패(with MVC)");
	}
	location.href="addr.do";
</script>