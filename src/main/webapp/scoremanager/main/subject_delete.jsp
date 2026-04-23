<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<div class="container">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

    <c:if test="${not empty errors}">
        <div class="error-messages">
            <c:forEach var="error" items="${errors}">
                <p style="color:red;">${error.value}</p>
            </c:forEach>
        </div>
    </c:if>

<p>「${subject.name}（${subject.cd }）」を削除してもよろしいでしょうか？</p>

<form action="SubjectDeleteExecute.action" method="post" style="display: inline;">
               
	<input type="hidden" name="cd" value="${subject.cd}">
                
    <button class="btn btn-danger">削除</button>
</form>
<br>
<br>
<a href="SubjectList.action"  style="margin-left: 15px;">戻る</a>


</c:param>
</c:import>
