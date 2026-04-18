<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2>学生情報更新</h2>

<form action="StudentUpdateExecute.action" method="post">

    <!-- 学籍番号（更新不可・送信用に hidden） -->
    <p>
        学籍番号： ${student.no}
        <input type="hidden" name="no" value="${student.no}">
    </p>

    <!-- 学校（更新不可） -->
    <p>
        学校： ${student.school}
    </p>
    
        <!-- 氏名（更新可） -->
    <p>
        氏名：<br>
        <input type="text" name="name"
               value="${student.name}" required>
    </p>

    <!-- クラス（更新可） -->
    <p>
        クラス：<br>
        <select name="class_num">
            <c:forEach var="cn" items="${classnum}">
                <option value="${cn}"
                    <c:if test="${cn == student.classNum}">
                        selected
                    </c:if>>
                    ${cn}
                </option>
            </c:forEach>
        </select>
    </p>

    <!-- 在学中フラグ（更新可） -->
    <p>
        在学状況：<br>
        <label>
            <input type="radio" name="is_enrolled" value="true"
                <c:if test="${student.attend}">
                    checked
                </c:if>>
            〇
        </label>
        <br>
        <label>
            <input type="radio" name="is_enrolled" value="false"
                <c:if test="${!student.attend}">
                    checked
                </c:if>>
            ×
        </label>
    </p>

    <p>
        <input type="submit" value="更新"><a href="StudentList.action" style="margin-left: 15px;">戻る</a>
    </p>

</form>

</c:param>
</c:import>