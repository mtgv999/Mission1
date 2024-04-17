<%@ page import="zerobase.web.wifi.service.WifiService" %>
<%@ page import="zerobase.web.wifi.dto.WifiInfoDto" %>
<%@ page import="java.util.List" %>
<%@ page import="zerobase.web.wifi.model.WifiInfoModel" %>
<%@ page import="zerobase.web.wifi.service.PosHistoryService" %>
<%@ page import="zerobase.web.wifi.model.PosHistoryModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	WifiInfoModel parameter = WifiInfoModel.getParameter(request);

	List<WifiInfoDto> wifiInfoList = null;
	if (!parameter.isEmpty()) {

		PosHistoryModel posHistoryModel = new PosHistoryModel();
		posHistoryModel.setLat(parameter.getLat());
		posHistoryModel.setLnt(parameter.getLnt());

		PosHistoryService posHistoryService = new PosHistoryService();
		posHistoryService.add(posHistoryModel);

		WifiService wifiService = new WifiService();
		wifiInfoList = wifiService.getList(parameter);
	}
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

<div class="position-info">
	<form action="">
		LAT: <input id="inputLat" type="text" name="lat" value="<%=parameter.getLat()%>" required/>
		,
		LNT: <input id="inputLnt" type="text" name="lnt" value="<%=parameter.getLnt()%>" required/>
		<button id="getPositionButton" type="button">내 위치 가져오기</button>
		<button type="submit"> 근처 WIPI 정보 보기</button>
	</form>
</div>

<div>
	<table class="table-list">
		<thead>
		<tr>
			<th>거리(Km)</th>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>

			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>

			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>WIFI접속환경</th>
			<th>X좌표</th>

			<th>Y좌표</th>
			<th>작업일자</th>

		</tr>
		</thead>
		<tbody>

		<%
			if (wifiInfoList != null && !wifiInfoList.isEmpty()) {
				for (WifiInfoDto x : wifiInfoList) {
		%>

		<tr>
			<td><%=x.getDistanceText()%></td>
			<td><%=x.getMgrNo()%></td>
			<td><%=x.getWrdofc()%></td>
			<td>
				<a href="detail.jsp?mgrNo=<%=x.getMgrNo()%>">
					<%=x.getMainNm()%>
				</a>
			</td>
			<td><%=x.getAdres1()%></td>

			<td><%=x.getAdres2()%></td>
			<td><%=x.getInstlFloor()%></td>
			<td><%=x.getInstlTy()%></td>
			<td><%=x.getInstlMby()%></td>
			<td><%=x.getSvcSe()%></td>

			<td><%=x.getCmcwr()%></td>
			<td><%=x.getCnstcYear()%></td>
			<td><%=x.getInoutDoor()%></td>
			<td><%=x.getRemars3()%></td>
			<td><%=x.getLat()%></td>

			<td><%=x.getLnt()%></td>
			<td><%=x.getWorkDttm()%></td>
		</tr>

		<%
			}
		} else {
		%>

		<tr>
			<td colspan="17">
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