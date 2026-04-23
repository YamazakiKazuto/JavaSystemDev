<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
 
    <c:param name="scripts"></c:param>
 
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス別所属人数</h2>
            
            <%-- ★ここに追加：科目管理と同じデザインの新規登録リンク --%>
            <div class="my-2 text-end px-4">
                <a href="ClassCreate.action">新規登録</a>
            </div>

            <div class="my-3 px-4">
                検索結果：${classCounts.size()}件
            </div>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>クラス番号</th>
                        <th class="text-center">所属人数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${classCounts}">
                        <tr>
                            <td>${entry.key}</td>
                            <td class="text-center">${entry.value}名</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </c:param>
</c:import>