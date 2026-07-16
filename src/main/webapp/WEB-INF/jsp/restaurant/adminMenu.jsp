<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 화면 (메뉴 CRUD)</title>
</head>
<body>

<h2>관리자 화면</h2>

<div style="margin-bottom: 15px;">

    <a href="${pageContext.request.contextPath}/admin/logout.do">
        <button type="button">
            로그아웃
        </button>
    </a>

</div>

<form action="${pageContext.request.contextPath}/admin/menu.do"
      method="get">

    검색:
    <input type="text"
           name="searchKeyword"
           value="${searchKeyword}">

    <button type="submit">검색</button>
</form>

<br>

<table border="1">
    <tr>
        <th>메뉴명</th>
        <th>가격</th>
        <th>상품 이미지</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>

    <c:forEach var="menu" items="${menuList}">
        <tr>
            <td>
                <input type="text"
                       name="menuName"
                       value="${menu.menuName}"
                       form="updateForm${menu.menuId}">
            </td>

            <td>
                <input type="number"
                       name="price"
                       value="${menu.price}"
                       form="updateForm${menu.menuId}">
            </td>

			<td>
			    <c:choose>
					<c:when test="${menu.fileId != null}">
					    <div style="display: flex;
					                align-items: center;
					                gap: 10px;">
					
					        <img
					            src="${pageContext.request.contextPath}/menu/image.do?menuId=${menu.menuId}"
					            alt="${menu.menuName}"
					            width="120"
					            height="90">
					
					        <a href="${pageContext.request.contextPath}/admin/menu/image/download.do?menuId=${menu.menuId}">
					            <button type="button">다운로드</button>
					        </a>
					
					        <form action="${pageContext.request.contextPath}/admin/menu/image/upload.do"
					              method="post"
					              enctype="multipart/form-data"
					              style="display: flex;
					                     align-items: center;
					                     gap: 5px;
					                     margin: 0;">
					
					            <input type="hidden"
					                   name="menuId"
					                   value="${menu.menuId}">
					
					            <input type="file"
					                   name="imageFile"
					                   accept="image/*"
					                   required>
					
					            <button type="submit">이미지 변경</button>
					        </form>
					    </div>
					</c:when>
			
			        <c:otherwise>
			            <form action="${pageContext.request.contextPath}/admin/menu/image/upload.do"
			                  method="post"
			                  enctype="multipart/form-data">
			
			                <input type="hidden"
			                       name="menuId"
			                       value="${menu.menuId}">
			
			                <input type="file"
			                       name="imageFile"
			                       accept="image/*"
			                       required>
			
			                <button type="submit">업로드</button>
			            </form>
			        </c:otherwise>
			    </c:choose>
			</td>

            <td>
                <form id="updateForm${menu.menuId}"
                      action="${pageContext.request.contextPath}/admin/menu/update.do"
                      method="post">

                    <input type="hidden"
                           name="menuId"
                           value="${menu.menuId}">

                    <button type="submit">수정</button>
                </form>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/admin/menu/delete.do"
                      method="post">

                    <input type="hidden"
                           name="menuId"
                           value="${menu.menuId}">

                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<br>

<c:forEach var="pageNumber" begin="1" end="${totalPage}">
    <a href="${pageContext.request.contextPath}/admin/menu.do?page=${pageNumber}&searchKeyword=${searchKeyword}">
        ${pageNumber}
    </a>
</c:forEach>

<br>
<br>

<form:form
    action="${pageContext.request.contextPath}/admin/menu/add.do"
    method="post"
    enctype="multipart/form-data"
    modelAttribute="menuVO">

    메뉴명:
    <form:input path="menuName"/>

    <form:errors
        path="menuName"
        cssStyle="color: red;"/>

    가격:
    <form:input
        path="price"
        type="number"/>

    <form:errors
        path="price"
        cssStyle="color: red;"/>

    이미지:
    <input type="file"
           name="imageFile"
           accept="image/*"
           required>

    <button type="submit">추가</button>

</form:form>

</body>
</html>