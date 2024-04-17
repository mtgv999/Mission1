<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.dto.PosHistoryDto" %>
<%@ page import="zerobase.web.wifi.service.BookmarkService" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkGroupDto" %>
<%@ page import="zerobase.web.wifi.model.BookmarkGroupModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");

	BookmarkGroupModel parameter = BookmarkGroupModel.getParameter(request);

	BookmarkService bookmarkService = new BookmarkService();
	BookmarkGroupDto detail = bookmarkService.getGroup(parameter.getId());
%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<%if (detail == null) {%>
	<script>
		alert(' 북마크 그룹 이름 정보가 존재하지 않습니다. ');
		history.back(-1);
	</script>
	<%}%>
</head>
<body>
	<h1>북마크 그룹 삭제</h1>
	<jsp:include page="inc_menu.jsp"/>


<div>
	<div>
		<p>
			북마크 그룹 이름을 삭제하시겠습니까?
		</p>
	</div>

	<%if (detail != null) {%>
	<div>
		<form method="post" action="bookmark-group-delete-submit.jsp">
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
						<input type="text" name="name" value="<%=detail.getName()%>" required/>
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td>
						<input type="text" name="sortValue" value="<%=detail.getSortValue()%>" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="td-center">
							<a href="bookmark-group.jsp">돌아가기</a>
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