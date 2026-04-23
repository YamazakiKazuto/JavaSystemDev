<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2>学生新規登録</h2>

<p>
    新しく追加する学生情報を入力し登録ボタンを押してください。
</p>

<c:if test="${not empty error}">
    <p class="error" style="color:red;">${error}</p>
</c:if>

<form action="StudentCreateExecute.action" method="post">


<div class="mb-3">
    <label class="form-label">入学年度</label>
    <%-- value属性に ${cd} をセット --%>
    <c:choose>
    <c:when test="${not empty entYEAR}">
        <input type="number" name="ent_year" class="form-control" value="${entYEAR}" >
    </c:when>

    <c:otherwise>
        <input type="number" name="ent_year" class="form-control"
               placeholder="---------" >
    </c:otherwise>
	</c:choose>
</div>


<div class="mb-3">
    <label class="form-label">学生番号</label>
    <%-- value属性に ${name} をセット --%>
    <c:choose>
    <c:when test="${not empty no}">
        <input type="number" name="no" class="form-control" value="${no}" required>
    </c:when>

    <c:otherwise>
        <input type="number" name="no" class="form-control"
               placeholder="学生番号を入力してください" required>
    </c:otherwise>
	</c:choose>
    
</div>


<div class="mb-3">
    <label class="form-label">氏名</label>
    <%-- value属性に ${name} をセット --%>
    <c:choose>
    <c:when test="${not empty name}">
        <input type="text" name="name" class="form-control" value="${name}" required>
    </c:when>

    <c:otherwise>
        <input type="text" name="name" class="form-control"
               placeholder="氏名を入力してください" required>
    </c:otherwise>
	</c:choose>
    
</div>



<div class="mb-3">
    <label class="form-label">クラス</label>
    <select name="classnumm" class="form-control">
        <c:forEach var="num" items="${classnum}">
            <option value="${num}" 
                <c:if test="${num == classnu}">selected</c:if>>
                ${num}
            </option>
        </c:forEach>
    </select>
</div>

    

<button type="submit" class="btn btn-primary">登録して終了</button>
<br>
<br>
<a href="StudentList.action">戻る</a>


</form>

</c:param>
</c:import>