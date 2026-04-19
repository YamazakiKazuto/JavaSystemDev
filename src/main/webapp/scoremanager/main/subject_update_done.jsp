<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2>科目の変更が完了しました</h2>
<p>更新後の情報は下記になります</p>
<p>    科目コード　:　${subject.cd}</p>
<p>    科目名　　:　${subject.name}</p>

<a href="SubjectList.action">科目一覧画面に戻る</a>

</c:param>
</c:import>
