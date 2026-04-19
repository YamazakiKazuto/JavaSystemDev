<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<form action="SubjectCreateExecute.action" method="post">
    <%-- エラーメッセージの表示 --%>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <div class="mb-3">
        <label for="cd" class="form-label">科目コード（3文字）</label>
        <%-- value属性に ${cd} をセット --%>
        <input type="text" name="cd" id="cd" class="form-control" value="${cd}" required>
    </div>

    <div class="mb-3">
        <label for="name" class="form-label">科目名</label>
        <%-- value属性に ${name} をセット --%>
        <input type="text" name="name" id="name" class="form-control" value="${name}" required>
    </div>

    <button type="submit" class="btn btn-primary">登録</button>
    <a href="SubjectList.action" class="btn btn-secondary">戻る</a>
</form>

</c:param>
</c:import>