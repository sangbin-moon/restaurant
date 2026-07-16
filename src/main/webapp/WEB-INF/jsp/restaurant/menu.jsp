<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연습용 요식업 웹페이지</title>
</head>
<body>

<h2>요식업 웹페이지</h2>

<a href="${pageContext.request.contextPath}/cart.do">카트</a>

<br><br>

<table border="1">
    <tr>
        <th>이미지</th>
        <th>메뉴</th>
        <th>가격</th>
        <th>수량</th>
        <th>담기</th>
    </tr>

    <c:forEach var="menu" items="${menuList}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${menu.fileId != null}">
                        <img
                            src="${pageContext.request.contextPath}/menu/image.do?menuId=${menu.menuId}"
                            alt="${menu.menuName}"
                            width="120"
                            height="90">
                    </c:when>

                    <c:otherwise>
                        등록된 이미지 없음
                    </c:otherwise>
                </c:choose>
            </td>

            <td>${menu.menuName}</td>

            <td>${menu.price}</td>

            <td>
                <button type="button" onclick="minus(this)">-</button>
                <span class="quantity">1</span>
                <button type="button" onclick="plus(this)">+</button>
            </td>

            <td>
                <button type="button"
                        onclick="addCart(this, ${menu.menuId})">
                    담기
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/js/menu.js"></script>

</body>
</html>