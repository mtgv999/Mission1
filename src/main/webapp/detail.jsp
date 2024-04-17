<%@ page import="zerobase.web.wifi.service.WifiService" %>
<%@ page import="zerobase.web.wifi.dto.WifiInfoDto" %>
<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.model.WifiInfoModel" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.model.PosHistoryModel" %>
<%@ page import="zerobase.web.wifi.model.WifiInfoDetailModel" %>
<%@ page import="zerobase.web.wifi.dto.BookmarkGroupDto" %>
<%@ page import="zerobase.web.wifi.service.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	WifiInfoDetailModel parameter = WifiInfoDetailModel.getParameter(request);

	WifiService wifiService = new WifiService();
	WifiInfoDto wifiInfoDetail = wifiService.getDetail(parameter);

	BookmarkService bookmarkService = new BookmarkService();
	List<BookmarkGroupDto> bookmarkGroups = bookmarkService.getsGroup();
%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/index.js"></script>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="inc_menu.jsp"/>


<%
	WifiInfoDto x = wifiInfoDetail;
%>

<div>

	<div>
		<form method="post" action="bookmark-add-submit.jsp">
			<input type="hidden" name="mgrNo" value="<%=x.getMgrNo()%>"/>
			<select name="bookMarkGroupId" required>
				<option value="">북마크 그룹 이름 선택</option>
				<%for(BookmarkGroupDto group : bookmarkGroups) {%>
				<option value="<%=group.getId()%>"><%=group.getName()%></option>
				<%}%>
			</select>
			<button type="submit">북마크 추가하기</button>
		</form>
	</div>

	<table class="table-list">
		<colgroup>
			<col style="width: 20%;"/>
			<col style="width: 80%;"/>
		</colgroup>
		<thead>
		<tr>
			<th>거리(Km)</th>
			<td><%=x.getDistanceText()%></td>
		</tr>
		<tr>
			<th>관리번호</th>
			<td><%=x.getMgrNo()%></td>
		</tr>
		<tr>
			<th>자치구</th>
			<td><%=x.getWrdofc()%></td>
		</tr>
		<tr>
			<th>와이파이명</th>

			<td>
				<a href="detail.jsp?id=<%=x.getMgrNo()%>">
					<%=x.getMainNm()%>
				</a>
			</td>
		</tr>
		<tr>
			<th>도로명주소</th>
			<td><%=x.getAdres1()%></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><%=x.getAdres2()%></td>
		</tr>
		<tr>
			<th>설치위치(층)</th>
			<td><%=x.getInstlFloor()%></td>
		</tr>
		<tr>
			<th>설치유형</th>
			<td><%=x.getInstlTy()%></td>
		</tr>
		<tr>
			<th>설치기관</th>
			<td><%=x.getInstlMby()%></td>
		</tr>
		<tr>
			<th>서비스구분</th>
			<td><%=x.getSvcSe()%></td>
		</tr>
		<tr>
			<th>망종류</th>
			<td><%=x.getCmcwr()%></td>
		</tr>
		<tr>
			<th>설치년도</th>
			<td><%=x.getCnstcYear()%></td>
		</tr>
		<tr>
			<th>실내외구분</th>
			<td><%=x.getInoutDoor()%></td>
		</tr>
		<tr>
			<th>WIFI접속환경</th>
			<td><%=x.getRemars3()%></td>
		</tr>
		<tr>
			<th>X좌표</th>
			<td><%=x.getLat()%></td>
		</tr>
		<tr>
			<th>Y좌표</th>
			<td><%=x.getLnt()%></td>
		</tr>
		<tr>
			<th>작업일자</th>
			<td><%=x.getWorkDttm()%></td>
		</tr>
		</thead>
		<tbody>
	</table>

</div>


</body>
</html>