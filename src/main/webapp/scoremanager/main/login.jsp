<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<section class="d-flex justify-content-center mt-5">
    <div class="card shadow-sm" style="width: 600px;">
        
        <!-- ① ログイン見出し -->
        <h2 class="text-center h4 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">ログイン</h2>

        <div class="card-body px-4 w-100 mx-auto">

            <!-- エラーメッセージ -->
            <c:if test="${not empty error}">
                <li class="text-center">${error}</li>
            </c:if>

            <form action="LoginExecute.action" method="post">

      
                <div class="form-floating mb-3">
    				<input type="text"
           				class="form-control bg-primary bg-opacity-10" id="userId" name="id" placeholder="半角でご入力ください"
           				<c:if test="${not empty returnid}">
               				value="${returnid}"
           				</c:if>
           				
           				required>
    				<label for="userId">ID</label>
				</div>
                
                
				<div class="form-floating mb-3">
    				<input type="password"
           				class="form-control bg-primary bg-opacity-10" id="password" name="password" placeholder="30文字以内の半角英数字でご入力ください"
           				<c:if test="${not empty returnpassword}">
               				value="${returnpassword}"
           				</c:if>
           				required>
    				<label for="password">パスワード</label>
				</div>
              
       

                <!-- ④⑤ パスワード表示 -->
                <div class="form-check mb-3 d-flex justify-content-center">
                    <input class="form-check-input"
                           type="checkbox"
                           id="showPassword"
                           onclick="togglePassword()">
                    <label class="form-check-label" for="showPassword">
                        パスワードを表示
                    </label>
                </div>

                <!-- ⑥ ログインボタン -->
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">
                        ログイン
                    </button>
                </div>

            </form>
        </div>
    </div>
</section>

<script>
function togglePassword() {
    const pw = document.getElementById("password");
    pw.type = pw.type === "password" ? "text" : "password";
}
</script>

</c:param>
</c:import>
