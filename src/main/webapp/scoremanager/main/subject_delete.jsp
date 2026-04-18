<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<div class="container">
    <h2>科目削除</h2>

    <c:if test="${not empty errors}">
        <div class="error-messages">
            <c:forEach var="error" items="${errors}">
                <p style="color:red;">${error.value}</p>
            </c:forEach>
        </div>
    </c:if>

    <div class="confirmation-box">
        <p>以下の科目を削除します。よろしいですか？</p>

        
		<p>    科目コード　:　${subject.cd}</p>
		<p>    科目名　　:　${subject.name}</p>
	
        <div class="actions" style="margin-top: 20px;">
         
            <form action="SubjectDeleteExecute.action" method="post" style="display: inline;">
                
                <input type="hidden" name="cd" value="${subject.cd}">
                
                <input type="submit" value="削除">
            </form>
           <a href="SubjectList.action"  style="margin-left: 15px;">戻る</a>
        </div>
    </div>
</div>

</c:param>
</c:import>
