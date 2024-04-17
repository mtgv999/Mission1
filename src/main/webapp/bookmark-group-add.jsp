<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.dto.PosHistoryDto" %>
<%@ page import="zerobase.web.wifi.service.BookmarkService" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkGroupDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>
	<h1>북마크 그룹 추가</h1>
	<jsp:include page="inc_menu.jsp"/>


<div>
	<div>
		<form method="post" action="bookmark-group-add-submit.jsp">
			<table class="table-list">
				<colgroup>
					<col style="width: 20%;">
					<col style="width: 80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>북마크 이름</th>
					<td>
						<input type="text" name="name" required/>
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td>
						<input type="text" name="sortValue" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="td-center">
							<button type="submit">추가</button>
						</div>
					</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>

</div>

</body>
</html>