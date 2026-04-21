<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2>成績検索画面</h2>
<br>
<h6>生徒ごとの検索</h6>

<c:if test="${not empty stuerror}">
    <p class="error" style="color:red;">${stuerror}</p>
</c:if>

<form action="TestListStudentExecute.action" method="post">

    <p>
        学生番号：
        <input type="text" name="no" required>
    </p>
    <p>
        <input type="submit" value="検索">
    </p>
    

</form>

<br>
<h6>クラス・科目ごとの検索</h6>

<c:if test="${not empty claerror}">
    <p class="error" style="color:red;">${claerror}</p>
</c:if>

<form action="TestListSubjectExecute.action" method="post">

    <p>
        入学年度：
        <input type="number" name="ent_year">
    </p>


    <p>
    	クラス:
     <select name="classNum">
     <%--　セッションに保存されているcourselistを拡張for文形式で --%>
    	<c:forEach var="cla" items="${classlist}">
        	<option value="${cla}">${cla}</option>
    	</c:forEach>            
     </select>
    </p>

	 <p>
    	科目:
     <select name="subjectCd">
     <%--　セッションに保存されているcourselistを拡張for文形式で --%>
    	<c:forEach var="subject" items="${subjectlist}">
        	<option value="${subject.cd}">${subject.cd} : ${subject.name}</option>
    	</c:forEach>            
     </select>
    </p>
	
    <p>
        <input type="submit" value="検索">
    </p>
    

</form>



</c:param>
</c:import>