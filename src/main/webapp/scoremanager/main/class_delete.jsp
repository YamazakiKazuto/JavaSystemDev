<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス情報削除</h2>
            <p class="px-4">「クラス：${class_num}」を削除してもよろしいでしょうか？</p>
            
            <form action="ClassDeleteExecute.action" method="post" class="px-4">
                <input type="hidden" name="class_num" value="${class_num}">
                <button type="submit" class="btn btn-danger">削除</button>
                <div class="mt-3">
                    <a href="ClassCounts.action">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>