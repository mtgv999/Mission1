<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.dto.PosHistoryDto" %>
<%@ page import="zerobase.web.wifi.service.BookmarkService" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkGroupDto" %>
<%@ page import="zerobase.web.wifi.model.BookmarkGroupModel" %>
<%@ page import="zerobase.web.wifi.model.BookmarkModel" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");

	BookmarkModel parameter = BookmarkModel.getParameter(request);

	BookmarkService bookmarkService = new BookmarkService();
	BookmarkDto detail = bookmarkService.get(parameter.getId());
%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<%if (detail == null) {%>
	<script>
		alert(' 북마크 정보가 존재하지 않습니다. ');
		history.back(-1);
	</script>
	<%}%>
</head>
<body>
	<h1>북마크 삭제</h1>
	<jsp:include page="inc_menu.jsp"/>

<div>

	<div>
		<p>
			북마크를 삭제하시겠습니까?
		</p>
	</div>

	<%if (detail != null) {%>
	<div>
		<form method="post" action="bookmark-delete-submit.jsp">
			<input type="hidden" name="id" value="<%=detail.getId()%>"/>
			<table class="table-list">
				<colgroup>
					<col style="width: 20%;">
					<col style="width: 80%;">
				</colgroup>
				<tbody>

				<tr>
					<th>북마크 이름</th>
					<td>
						<%=detail.getBookmarkGroupName()%>
					</td>
				</tr>
				<tr>
					<th>와이파이명</th>
					<td>
						<%=detail.getXSwifiMainNm()%>
					</td>
				</tr>
				<tr>
					<th>등록일자</th>
					<td>
						<%=detail.getRegDt()%>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="td-center">
							<a href="bookmark-list.jsp">돌아가기</a>
							|
							<button type="submit">삭제</button>
						</div>
					</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>
	<%}%>
</div>

</body>
</html>