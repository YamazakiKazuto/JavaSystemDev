<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">教員情報削除</h2>
            <div class="px-4">
                <p>「教員ID：${teacher.id} 氏名：${teacher.name}」を削除してもよろしいでしょうか？</p>
                
                <form action="TeacherDeleteExecute.action" method="post">
                    <%-- 削除するIDを隠しパラメータで送る --%>
                    <input type="hidden" name="id" value="${teacher.id}">
                    
                    <button type="submit" class="btn btn-danger">削除</button>
                </form>

                <div class="mt-3">
                    <a href="LoginManage.action">戻る</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>