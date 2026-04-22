<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<section>
    <h2 class=" h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 text-center">ログイン</h2>

    <%-- ログイン失敗時のエラーメッセージ表示 --%>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <%-- actionを LoginExecute.action に変更 --%>
    <form action="LoginExecute.action" method="post">

        <div class="mb-3">
        	<label for="cd" class="form-label">ユーザID</label>
        	
        	<input type="text" name="id" 
        	<c:if test="${not empty returnid}"> 	
   				value="${returnid }" 
			</c:if>
        	class="form-control" required>
    	</div>
    	

        <div class="mb-3">
        	<label for="name" class="form-label">パスワード</label>
        	
        	<input type="password" id="password" name="password"
       			value="${not empty returnpassword ? returnpassword : ''}"
       			class="form-control" required>
        	<div class="d-flex justify-content-center mt-2">
        		<input class="form-check-input" type="checkbox" id="showPassword" onclick="togglePassword()">
        		<label class="form-check-label" for="showPassword">
            		パスワードを表示
        		</label>
    		</div>
   	 	</div>
    	
    	
    	<script>	
			function togglePassword() {
    		const pw = document.getElementById("password");
    		pw.type = pw.type === "password" ? "text" : "password";
			}
		</script>

        <p class="text-center">
            <button type="submit" class="btn btn-primary">ログイン</button>
        </p>
    </form>
</section>

</c:param>
</c:import>
