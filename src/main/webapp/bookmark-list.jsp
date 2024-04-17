<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.dto.PosHistoryDto" %>
<%@ page import="zerobase.web.wifi.service.BookmarkService" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkGroupDto" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	BookmarkService bookmarkService = new BookmarkService();
	List<BookmarkDto> bookmarks = bookmarkService.gets();
%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>
	<h1>북마크 목록</h1>
	<jsp:include page="inc_menu.jsp"/>


<div>
	<table class="table-list">
		<thead>
		<tr>
			<th>ID</th>
			<th>북마크 이름</th>
			<th>와이파이명</th>
			<th>등록일자</th>
			<th>비고</th>
		</tr>
		</thead>
		<tbody>

		<%
			if (bookmarks != null && !bookmarks.isEmpty()) {
				for (BookmarkDto x : bookmarks) {
		%>

		<tr>
			<td><%=x.getId()%></td>
			<td>
				<%=x.getBookmarkGroupName()%>
			</td>
			<td>
				<a href="detail.jsp?mgrNo=<%=x.getXSwifiMgrNo()%>"><%=x.getXSwifiMainNm()%></a>
			</td>
			<td><%=x.getRegDt()%></td>

			<td>
				<div class="td-center">
					<a href="bookmark-delete.jsp?id=<%=x.getId()%>">삭제</a>
				</div>
			</td>
		</tr>

		<%
			}
		} else {
		%>

		<tr>
			<td colspan="5">
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