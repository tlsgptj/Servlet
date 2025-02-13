<%@ page contentType="text/html;charset=utf-8" 
    import="java.util.ArrayList, addr.mv.model.AddrDAO, addr.mv.model.AddrDTO"%>
<jsp:useBean id="AddrDAO" class="addr.mv.model.AddrDAO" scope="application"/>

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
	Address List(Model1)
</h1>
<a href='../'>홈</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href='input.html'>쓰기</a>
<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>주소</th>
		<th>날짜</th>
		<th>삭제</th>
	</tr>
<%
	ArrayList<AddrDTO> list = AddrDAO.list();
	if(list != null) {
		if(list.size() != 0) {
			for(AddrDTO dto: list) {
%>
              <tr>
				<td align='center'><%=dto.getSeq()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getAddr()%></td>
				<td><%=dto.getRdate()%></td>
				<td align='center'><a href='del.jsp?seq=<%=dto.getSeq()%>'>삭제</a></td>
			  </tr>
<%
			}
		}
	}
%> 
</table>
</center>