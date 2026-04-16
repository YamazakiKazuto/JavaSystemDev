<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生新規登録</title>
</head>
<body>

<h2>学生新規登録</h2>

<p>
    新しく追加する学生情報を入力し登録ボタンを押してください。
</p>

<c:if test="${not empty error}">
    <p class="error" style="color:red;">${error}</p>
</c:if>

<form action="StudentCreateExecute.action" method="post">

    <p>
        入学年度：<br>
        <input type="number" name="ent_year"
               value="${param.ent_year}">
    </p>

    <p>
        学生番号：<br>
        <input type="text" name="no"
               value="${param.no}" required>
    </p>

    <p>
        氏名：<br>
        <input type="text" name="name"
               value="${param.name}" required>
    </p>

    <p>
    	クラス:<br>
     <select name="classnum">
     <%--　セッションに保存されているcourselistを拡張for文形式で --%>
    	<c:forEach var="num" items="${classnum}">
        	<option value="${num}">${num}</option>
    	</c:forEach>            
     </select>
    </p>

    <p>
        <input type="submit" value="登録">
    </p>
    <br>
    <p>
        <a href="StudentList.action">戻る</a>
    </p>

</form>

</body>
</html>