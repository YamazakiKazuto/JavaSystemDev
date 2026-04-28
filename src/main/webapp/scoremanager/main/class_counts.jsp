<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス別所属人数</h2>
            
            <div class="d-flex justify-content-between align-items-center mb-3 px-4">
                <div>検索結果：${classCounts.size()}件</div>
                <div>
                    <a href="ClassCreate.action" class="btn btn-outline-primary btn-sm">新規登録</a>
                </div>
            </div>

            <%-- エラーメッセージ表示エリア --%>
            <c:if test="${not empty error}">
                <div class="alert alert-danger mx-4" role="alert">
                    ${error}
                </div>
            </c:if>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>クラス番号</th>
                        <th class="text-center">所属人数</th>
                        <th class="text-center">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${classCounts}">
                        <tr>
                            <td>${entry.key}</td>
                            <td class="text-center">${entry.value}名</td>
                            <td class="text-center">
                                <%-- 青文字(text-primary) かつ 下線あり(text-decoration-underline) --%>
                                <a href="ClassDelete.action?class_num=${entry.key}" 
                                   class="text-primary text-decoration-underline">削除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </section>
    </c:param>
</c:import>