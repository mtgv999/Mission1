<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.dto.PosHistoryDto" %>
<%@ page import="zerobase.web.wifi.service.BookmarkService" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkGroupDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	BookmarkService bookmarkService = new BookmarkService();
	List<BookmarkGroupDto> bookmarkGroups = bookmarkService.getsGroup();
%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>
	<h1>북마크 그룹</h1>
	<jsp:include page="inc_menu.jsp"/>

<div>
	<div>
		<button onclick="location.href ='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
	</div>

	<table class="table-list">
		<thead>
		<tr>
			<th>ID</th>
			<th>북마크 이름</th>
			<th>순서</th>
			<th>등록일자</th>
			<th>수정일자</th>
			<th>비고</th>
		</tr>
		</thead>
		<tbody>

		<%
			if (bookmarkGroups != null && !bookmarkGroups.isEmpty()) {
				for (BookmarkGroupDto x : bookmarkGroups) {
		%>

		<tr>
			<td><%=x.getId()%></td>
			<td>
				<%=x.getName()%>
			</td>
			<td><%=x.getSortValue()%></td>
			<td><%=x.getRegDt()%></td>
			<td>
				<%=x.getUdtDt() != null ? x.getUdtDt() : ""%>
			</td>
			<td>
				<div class="td-center">
					<a href="bookmark-group-edit.jsp?id=<%=x.getId()%>">수정</a>
					<a href="bookmark-group-delete.jsp?id=<%=x.getId()%>">삭제</a>
				</div>
			</td>
		</tr>

		<%
			}
		} else {
		%>

		<tr>
			<td colspan="6">
				<p class="nothing">정보가 존재하지 않습니다.</p>
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