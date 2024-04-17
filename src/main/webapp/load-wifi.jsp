<%@ page import="zerobase.web.wifi.service.WifiService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	WifiService wifiService = new WifiService();
	int result = wifiService.saveWifiInfo();
%>
<!DOCTYPE html>
<html>
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="inc_menu.jsp"/>

	<div class="result-div">
		<h1><%=result%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
		<div>
			<a href="/">홈 으로 가기</a>
		</div>
	</div>
</body>
</html>