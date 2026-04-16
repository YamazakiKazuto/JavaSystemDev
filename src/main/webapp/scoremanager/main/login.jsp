<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="/common/header.jsp" --%>

<section>
    <h2>ログイン</h2>

    <%-- ログイン失敗時のエラーメッセージ表示 --%>
    <c:if test="${not empty errors}">
        <p style="color: red;">${errors}</p>
    </c:if>

    <%-- actionを LoginExecute.action に変更 --%>
    <form action="LoginExecute.action" method="post">
        <p>
            ユーザーID<br>
            <input type="text" name="id" required placeholder="IDを入力してください">
        </p>
        <p>
            パスワード<br>
            <input type="password" name="password" required placeholder="パスワードを入力してください">
        </p>
        <p>
            <input type="submit" value="ログイン">
        </p>
    </form>
</section>

<%--<%@include file="/common/footer.jsp" --%>