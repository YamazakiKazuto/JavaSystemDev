<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2 style="text-align:center;">学生成績一覧</h2>

<c:choose>

    <c:when test="${not empty error}">
        <div class="error-messages">
            <c:forEach var="error" items="${error}">
                <p style="color:red;">${error}</p>
            </c:forEach>
        </div>
    </c:when>

    <c:otherwise>
        <table border='1'>
            <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th>n回目</th>
            <th>点数</th>
            </tr>

            <c:forEach var="t" items="${tesstu}">
                <tr>
                	<td>${t.subjectCd}</td>
                    <td>${t.subjectName}</td>
                    <td>${t.num}</td>
                    <td>${t.point}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>

</c:choose>

</c:param>
</c:import>
