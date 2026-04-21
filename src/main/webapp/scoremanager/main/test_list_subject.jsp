<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2 style="text-align:center;">クラス・科目別成績一覧</h2>

<c:choose>

    <c:when test="${not empty error}">
        <div class="error-messages">
            <c:forEach var="error" items="${error}">
                <p style="color:red;">${error}</p>
            </c:forEach>
        </div>
    </c:when>

    <c:otherwise>
        <table border='1' style="margin-left: 170px; border-collapse: separate; border-spacing: 50px 5px;">
            <tr>
             <th>クラス</th>
            <th>科目</th>
            <th>学生番号</th>
            <th>名前</th>
            <th>n回目</th>
            <th>点数</th>
            </tr>

            <c:forEach var="t" items="${tescla}">
                <tr>
                    <td>${t.classNum}</td>
                    <td>${t.subject.cd}</td>
                	<td>${t.student.no}</td>
                    <td>${t.student.name}</td>
                    <td>${t.no}</td>
                    <td>${t.point}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>

</c:choose>

</c:param>
</c:import>
