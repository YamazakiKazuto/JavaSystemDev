<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- 見出し：背景色とパディングを追加 --%>
            <h1 style="color:red;">エラー</h1>
			<p>※　${errorMessage }</p>
			<h6>予期していないエラーが発生しました。管理者にお知らせください</h6>
        </section>
    </c:param>
</c:import>