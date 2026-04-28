<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">教員情報削除</h2>
            
            <%-- 緑色の完了メッセージバー --%>
            <div class="alert alert-success mx-4" role="alert">
                削除が完了しました
            </div>

            <div class="px-4 mt-3">
                <a href="LoginManage.action">教員一覧へ戻る</a>
            </div>
        </section>
    </c:param>
</c:import>