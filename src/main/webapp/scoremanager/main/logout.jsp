<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 1. JSTLを使用するために必須の宣言 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログアウト完了</title>
</head>
<body>
    <header>
		<c:if test="${not empty user}">
		    <div class="nav align-self-end">
		        <%-- .name と書けば、内部で getName() が呼ばれます --%>
		        <span class="nav-item px-2">${user.name}様</span>
		        <a class="nav-item px-2" href="Logout.action">ログアウト</a>
		    </div>
		</c:if>
    </header>

    <main>
        <h2>ログアウトしました</h2>
        <p>ご利用ありがとうございました。</p>
        
        <p><a href="Login.action">ログイン画面へ戻る</a></p>
    </main>
</body>
</html>