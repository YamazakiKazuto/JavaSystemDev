<%--//制作者 山﨑 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員役職変更</h2>

<form action="TeacherRoleUpdateExecute.action" method="post">
    <%-- エラーメッセージの表示 --%>
    

   	<div class="mb-3 w-50">
    	<label class="form-label">教員情報</label>
		<p>　教員id：${teacher.id}　　　教員ネーム；${teacher.name }</p>
        <input type="hidden" name="id" value="${teacher.id}" required>
    </div>
    
    <div class="mb-3">
    	<label class="form-label">役職</label>
    	<select name="role_num" class="form-control">
        	<c:forEach var="role" items="${role_list}">
        		<option value="${role.role}" <c:if test="${teacher.role.role == role.role}">selected</c:if>>${role.name}</option>
        	</c:forEach>
    	</select>
	</div>
     

    <button type="submit" class="btn btn-primary">登録</button>
    <br>
    <br>
    <a href="LoginManage.action">戻る</a>
</form>

</c:param>
</c:import>