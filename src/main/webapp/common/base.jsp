<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<title>${param.title}</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
${param.scripts}
</head>
<body>
	<div id="wrapper" class="container">

    <!-- ヘッダー -->
    <header
        class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4
               border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">
        <c:import url="/common/header.jsp" />
    </header>

    <!-- メインレイアウト -->
    <div class="row">
    	<c:choose>
		<c:when test="${not empty user}">   
        <!-- 左メニュー -->
        	<aside class="col-3 sidebar">
    			<p><a href="Menu.action">メニュー画面</a></p>
	            <p><a href="StudentList.action">学生管理</a></p>
    	        <p>成績管理</p>
        	    <p><a href="TestRegist.action" style="margin-left: 15px;">成績登録</a></p>
            	<p><a href="TestList.action" style="margin-left: 20px;">成績参照</a></p>
            	<p><a href="SubjectList.action">科目管理</a></p>
            	<p><a href="ClassCounts.action">クラス管理</a></p>
    		</aside>

        <!-- 右コンテンツ -->
        <main class="col-9 border-start">
        	${param.content}
        </main>
        </c:when>
        <c:otherwise>
        	<main class="col-12">
        		${param.content}
        </c:otherwise>
		</c:choose>
    </div>
    

    <!-- フッター -->
    <footer class="py-2 my-4 bg-dark bg-opacity-10 border-top border-3">
        <c:import url="/common/footer.jsp" />
    </footer>

</div>
``
</body>
</html>
