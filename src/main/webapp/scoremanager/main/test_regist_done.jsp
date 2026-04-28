<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
	<label style="display:block; background-color:#9fd0b3;">
    	<p style="margin:0; text-align:center;">
        登録が完了しました
    	</p>
	</label>
<br>
<br>
    <a href="TestRegist.action">戻る</a>
    <a href="TestList.action" style="margin-left: 30px;">成績参照</a>

</c:param>
</c:import>