<%@ page import="zerobase.web.wifi.service.BookmarkService" %>
<%@ page import="zerobase.web.wifi.model.BookmarkGroupModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
    request.setCharacterEncoding("utf-8");

    BookmarkGroupModel parameter = BookmarkGroupModel.getParameter(request);

    BookmarkService bookmarkService = new BookmarkService();
    boolean result = bookmarkService.editGroup(parameter);
%>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link href="/res/css/main.css" rel="stylesheet"/>
    <%if (result) {%>
    <script>
        alert(' 북마크 그룹 정보를 수정하였습니다. ');
        location.href = 'bookmark-group.jsp';
    </script>
    <%} else {%>
    <script>
        alert(' 북마크 그룹 정보를 수정하는데 실패하였습니다.');
        history.back(-1);
    </script>
    <%} %>
</head>
<body>
</body>
</html>