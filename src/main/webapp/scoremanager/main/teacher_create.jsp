<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">教員情報登録</h2>
            
            <%-- ID重複時などのエラーメッセージ表示エリア --%>
            <c:if test="${not empty error}">
                <div class="alert alert-danger mx-4 w-50" role="alert">
                    ${error}
                </div>
            </c:if>

            <form action="TeacherCreateExecute.action" method="post" class="px-4">
                <div class="mb-3 w-50">
                    <label class="form-label">教員ID</label>
                    <%-- valueに設定することで、エラー時に戻ってきても入力内容が保持されます --%>
                    <input type="text" name="id" class="form-control" 
                           value="${param.id}" placeholder="例：admin" 
                           maxlength="10" required>
                </div>

                <div class="mb-3 w-50">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control" 
                           value="${param.name}" placeholder="例：大原花子" 
                           maxlength="10" required>
                </div>

                <div class="mb-3 w-50">
                    <label class="form-label">パスワード</label>
                    <%-- セキュリティ上、パスワードは保持させないのが一般的です --%>
                    <input type="password" name="password" class="form-control" 
                           maxlength="30" required>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">登録</button>
                </div>

                <div class="mt-3">
                    <a href="LoginManage.action">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>