<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

<form action="SubjectCreateExecute.action" method="post">
    <%-- エラーメッセージの表示 --%>
    

    <div class="mb-3">
        <label for="cd" class="form-label">科目コード（3文字）</label>
        <c:choose>
    	<c:when test="${not empty return_cd}">
        	<input type="text" name="cd" class="form-control" value="${return_cd}" required>
    	</c:when>

	    <c:otherwise>
    	    <input type="text" name="cd" class="form-control"
        	       placeholder="科目コードを入力してください" required>
    	</c:otherwise>
		</c:choose>
        <%-- value属性に ${cd} をセット --%>
    </div>
    <c:if test="${not empty cd_error}">
        <p style="color:#ff9933;">${cd_error}</p>
    </c:if>

    <div class="mb-3">
        <label for="name" class="form-label">科目名</label>
        <%-- value属性に ${name} をセット --%>
        <c:choose>
    	<c:when test="${not empty return_name}">
        	<input type="text" name="name" class="form-control" value="${return_name}" required>
    	</c:when>

	    <c:otherwise>
    	    <input type="text" name="name" class="form-control"
        	       placeholder="科目名を入力してください" required>
    	</c:otherwise>
		</c:choose>
     </div>
     

    <button type="submit" class="btn btn-primary">登録</button>
    <br>
    <br>
    <a href="SubjectList.action">戻る</a>
</form>

</c:param>
</c:import>