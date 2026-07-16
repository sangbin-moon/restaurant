<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
<h2>장바구니</h2>
<a href="menu.do">홈</a>
<br><br>
<table border="1">
    <tr>
        <th>메뉴</th>
        <th>수량</th>
        <th>가격</th>
    </tr>

<c:forEach var="item" items="${cartList}">
    <tr data-menu-id="${item.menuId}">
        <td>${item.menuName}</td>

        <td>
            <button type="button" onclick="cartMinus(this)">-</button>
            <span class="cartQuantity">${item.quantity}</span>
            <button type="button" onclick="cartPlus(this)">+</button>
        </td>

        <td>
            <span class="price" data-unit-price="${item.price}">
                ${item.price * item.quantity}
            </span>원
        </td>
    </tr>
</c:forEach>
</table>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
</body>
</html>