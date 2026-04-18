<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2>科目情報更新</h2>

<form action="SubjectUpdateExecute.action" method="post">

    <p>
        科目ID： ${subject.cd}
        <input type="hidden" name="cd" value="${subject.cd}">
    </p>

   
    <p>
        科目名：
        <input type="text" name="name"
               value="${subject.name}" required>
    </p>

    <p>
        <input type="submit" value="更新"><a href="SubjectList.action" style="margin-left: 15px;">戻る</a>
    </p>


</form>

</c:param>
</c:import>