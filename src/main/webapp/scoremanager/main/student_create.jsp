<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2>学生情報登録</h2>





<form action="StudentCreateExecute.action" method="post">


<div class="mb-3">
    <label class="form-label">入学年度</label>
    <%-- value属性に ${cd} をセット --%>
    <select class="form-select" id="student-f1-select" name="ent_year">
    	<option value="">--------</option>
        <c:forEach var="year" items="${ent_year_set}">
        <%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
        	<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
        </c:forEach>
        </select>
</div>

<c:if test="${not empty ent_year_error}">
    <div style="color:#ff9933;">${ent_year_error}</div>
</c:if>



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


<c:if test="${not empty no_error}">
    <div style="color:#ff9933;">${no_error}</div>
</c:if>



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

    

<button type="submit" class="btn btn-secondary">登録して終了</button>
<br>
<br>
<a href="StudentList.action">戻る</a>


</form>

</c:param>
</c:import>