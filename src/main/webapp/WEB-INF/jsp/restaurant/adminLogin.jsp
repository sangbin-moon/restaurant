<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
</head>
<body>

<h2>관리자 로그인</h2>

<form action="${pageContext.request.contextPath}/admin/login.do"
      method="post">

    <table>

        <tr>
            <td>아이디</td>
            <td>
                <input type="text"
                       name="loginId">
            </td>
        </tr>

        <tr>
            <td>비밀번호</td>
            <td>
                <input type="password"
                       name="loginPassword">
            </td>
        </tr>

        <c:if test="${not empty loginError}">
            <tr>
                <td colspan="2">
                    <div style="color: red;">
                        ${loginError}
                    </div>
                </td>
            </tr>
        </c:if>

        <tr>
            <td colspan="2">
                <button type="submit">
                    로그인
                </button>
            </td>
        </tr>

    </table>

</form>

</body>
</html>