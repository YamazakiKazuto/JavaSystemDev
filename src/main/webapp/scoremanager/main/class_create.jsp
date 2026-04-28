<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>

<c:param name="content">
    <section class="me-4">
        <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス新規登録</h2>
        <p class="px-4">登録したいクラス番号を入力し、登録ボタンを押してください。</p>

        <%-- エラーメッセージ表示エリア --%>
        <c:if test="${not empty error}">
            <p class="error px-4" style="color:red;">※ ${error}</p>
        </c:if>

        <form action="ClassCreateExecute.action" method="post" class="px-4">
            <%-- クラス番号の手入力項目 --%>
            <div class="mb-3">
                <label class="form-label">クラス番号</label>
                <input type="text" name="class_num" class="form-control" style="width:200px;"
                       value="${class_num}" placeholder="数字3桁を入力" maxlength="3" required>
                <div class="form-text text-muted">例：101, 202 など</div>
            </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-primary">登録して終了</button>
                <a href="ClassCounts.action" class="btn btn-secondary ms-2">戻る</a>
            </div>
        </form>
    </section>
</c:param>
</c:import>