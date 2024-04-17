<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.dto.PosHistoryDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	PosHistoryService posHistoryService = new PosHistoryService();
	List<PosHistoryDto> list = posHistoryService.getList();
%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/index.js"></script>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<jsp:include page="inc_menu.jsp"/>

<div>
	<table class="table-list">
		<thead>
		<tr>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</tr>
		</thead>
		<tbody>

		<%
			if (list != null && !list.isEmpty()) {
				for (PosHistoryDto x : list) {
		%>

		<tr>
			<td><%=x.getId()%></td>
			<td><%=x.getLat()%></td>
			<td><%=x.getLnt()%></td>
			<td><%=x.getRegDt()%></td>
			<td>
				<div class="td-center">
					<button type="button">삭제</button>
				</div>
			</td>
		</tr>

		<%
			}
		} else {
		%>

		<tr>
			<td colspan="5">
				<p class="nothing">위치 정보를 입력한 후에 조회해 주세요.</p>
			</td>
		</tr>

		<%
			}
		%>

		</tbody>
	</table>

</div>

</body>
</html>