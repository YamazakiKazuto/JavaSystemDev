<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目削除</h2>
	<label style="display: block; background-color: #8fc1a9; color: #034732;
       text-align: center;">
    	<p>削除が完了しました</p>
	</label>
	
<br>
<br>
 
 <a href="SubjectList.action" style="margin-left: 30px;" >科目一覧</a>
  

</c:param>
</c:import>