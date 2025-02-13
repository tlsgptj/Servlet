<%@ page contentType="text/html;charset=utf-8" 
    import="java.util.ArrayList, mvc.domain.Address"%>

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
	Address List(Model2)
</h1>
<a href='../'>홈</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href='addr.do?m=input'>쓰기</a>
<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>주소</th>
		<th>날짜</th>
		<th>삭제</th>
	</tr>
<%
	ArrayList<Address> list = (ArrayList<Address>)request.getAttribute("list");
	if(list != null) {
		if(list.size() != 0) {
			for(Address address: list) {
%>
              <tr>
				<td align='center'><%=address.getSeq()%></td>
				<td><%=address.getName()%></td>
				<td><%=address.getAddr()%></td>
				<td><%=address.getRdate()%></td>
				<td align='center'><a href='addr.do?m=del&seq=<%=address.getSeq()%>'>삭제</a></td>
			  </tr>
<%
			}
		}
	}
%> 
</table>
</center>