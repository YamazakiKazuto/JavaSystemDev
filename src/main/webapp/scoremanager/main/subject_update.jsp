<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>科目変更</title>
</head>
<body>

<h2>科目変更</h2>

<!-- ▼ エラーメッセージ表示 -------------------------------------------- -->
<c:if test="${not empty errors}">
    <div style="color:red;">
        <ul>
            <c:forEach var="e" items="${errors}">
                <li>${e.value}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<!-- ▲ エラーメッセージ表示 -------------------------------------------- -->

<form action="SubjectUpdateExecute.action" method="post">

    <table border="1" cellpadding="5">
        <tr>
            <th>科目コード</th>
            <td>
                <input type="text" name="cd" value="${subject.cd}" />
            </td>
        </tr>

        <tr>
            <th>科目名</th>
            <td>
                <input type="text" name="name" value="${subject.name}" />
            </td>
        </tr>
    </table>

    <br>

    <input type="submit" value="変更する" />
    <input type="button" value="戻る" onclick="location.href='SubjectList.action'" />

</form>

</body>
</html>
