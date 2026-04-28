<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">教員情報変更</h2>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger mx-4 w-50">${error}</div>
            </c:if>

            <form action="TeacherUpdateExecute.action" method="post" class="px-4">
                <div class="mb-3 w-50">
                    <label class="form-label">教員ID</label>
                    <input type="text" name="id" class="form-control" value="${user.id}" required>
                    <div class="form-text">※IDを変更すると次回から新しいIDでログインが必要です。</div>
                </div>

                <div class="mb-3 w-50">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control" value="${user.name}" required>
                </div>

                <div class="mb-3 w-50">
                    <label class="form-label">新しいパスワード</label>
                    <%-- 入力欄の背景色を画像に合わせて調整 --%>
                    <input type="password" name="password" id="passwordInput" 
                           class="form-control mb-2" style="background-color: #e9f0fe;" 
                           placeholder="変更する場合のみ入力">
                    
                    <%-- ★ボタンからチェックボックスに変更 --%>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="showPassword">
                        <label class="form-check-label" for="showPassword">
                            パスワードを表示
                        </label>
                    </div>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">変更を保存</button>
                </div>
                <div class="mt-3">
                    <a href="LoginManage.action">戻る</a>
                </div>
            </form>
        </section>

        <script>
            // チェックボックスの状態に合わせて表示・非表示を切り替える
            const passwordInput = document.getElementById('passwordInput');
            const showPassword = document.getElementById('showPassword');

            showPassword.addEventListener('change', function() {
                if (this.checked) {
                    passwordInput.type = 'text';
                } else {
                    passwordInput.type = 'password';
                }
            });
        </script>
    </c:param>
</c:import>