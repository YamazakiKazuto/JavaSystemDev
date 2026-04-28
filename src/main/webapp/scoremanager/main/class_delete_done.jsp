<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス情報削除</h2>
            
            <%-- 緑色の完了バー --%>
            <div class="alert alert-success text-center mx-4" role="alert" style="background-color: #94c1a1; color: #155724; border: none;">
                削除が完了しました
            </div>

            <div class="mt-4 px-4">
                <a href="ClassCounts.action">クラス一覧</a>
            </div>
        </section>
    </c:param>
</c:import>