<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>科目変更完了</title>
</head>
<body>

<h2>科目の変更が完了しました</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>科目コード</th>
        <td>${subject.cd}</td>
    </tr>
    <tr>
        <th>科目名</th>
        <td>${subject.name}</td>
    </tr>
</table>

<br>

<input type="button" value="科目一覧へ戻る" onclick="location.href='SubjectList.action'" />

</body>
</html>
