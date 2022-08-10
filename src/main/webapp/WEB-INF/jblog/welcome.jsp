<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- 브라우저가 인증에 성공했는지 확인한다. -->
<sec:authorize access="isAuthenticated()">
    <!-- JSP 파일에서 사용할 인증과 관련한 변수를 초기화한다. -->
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
</head>
<body>
<center>

    <!-- 검색 화면 시작 -->
    <form action="/" method="get">
        <table width="720" height=200 border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="100%" colspan="10" align="center">
                    <img src="/images/logo.jpg" border="0">
                </td>
            </tr>
            <tr>
                <td width="70%" colspan="2" align="center">
                    <c:if test="${principal == null }">
                        <a href="/auth/loginForm"><b>로그인</b></a>&nbsp;&nbsp;
                    </c:if>
                    <c:if test="${principal != null }">
                        <a href="/auth/logout"><b>로그아웃</b></a>
                        <c:if test="${principal.getBlog() == null}">
                            <a href="/blog/insertBlog"><b>블로그등록</b></a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${principal.getBlog() != null}">
                            <a href="/blog/getBlog/${principal.getBlog().id}"><b>블로그바로가기</b></a>
                        </c:if>&nbsp;&nbsp;
                    </c:if>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <input type="text" name="searchKeyword" size="50" value="${param.searchKeyword}">
                    <input type="submit" value="검색">
                </td>
            </tr>
            <tr>
                <td align="center">

                    <input type="radio" name="searchCondition" value="TITLE"
                           <c:if test="${param.searchCondition eq 'TITLE'}">checked</c:if>>블로그 제목&nbsp;&nbsp;
                    <input type="radio" name="searchCondition" value="TAG"
                           <c:if test="${param.searchCondition eq 'TAG'}">checked</c:if>>태그
                </td>
            </tr>

        </table>
    </form>
    <!-- 검색 화면 종료 -->

    <!-- 블로그 목록 시작 -->
    <br><br>
    <table width="550" border="0" cellpadding="1" cellspacing="1">
        <tr bgcolor="#9DCFFF">
            <th height="30"><font color="white">블로그 제목</font></th>
            <th width="100"><font color="white">상태</font></th>
            <c:if test="${principal.getRole() == 'ADMIN'}">
                <th width="100"><font color="white">삭제</font></th>
            </c:if>
        </tr>
        <c:forEach var="blog" items="${blogList }">
            <tr>
                <td align="left"><a href="/blog/blogMain/${blog.id}">${blog.title}</a></td>
                <td align="center">${blog.status}</td>
                <c:if test="${principal.getRole() == 'ADMIN'}">
                    <c:if test="${blog.status == '운영'}">
                        <td align="center">-</td>
                    </c:if>
                    <c:if test="${blog.status == '삭제 요청'}">
                        <td align="center">
                            <a href="/blog/delete/${blog.id }"><img height="9" src="/images/delete.jpg"
                                                                    border="0"></a>
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <!-- 블로그 목록 종료 -->

</center>
</body>
</html>