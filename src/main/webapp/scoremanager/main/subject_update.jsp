<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2>科目情報変更</h2>

<form action="SubjectUpdateExecute.action" method="post">
    <%-- エラーメッセージの表示 --%>
    

        <label>科目コード</label>
        <%-- value属性に ${cd} をセット --%>
        <input type="hidden" name="cd" value="${subject.cd}">
        <p>${subject.cd}</p>

	<c:if test="${not empty error}">
        <div style="color:#ff9933;">${error }</div>
		<br>    
    </c:if>
    
        <label class="form-label">科目名</label>
        <%-- value属性に ${name} をセット --%>
        <input type="text" name="name" class="form-control" value="${subject.name}" required>
    <br>

    <button type="submit" class="btn btn-primary">変更</button>
    <br>
    <br>
    <a href="SubjectList.action">戻る</a>
</form>

</c:param>
</c:import>