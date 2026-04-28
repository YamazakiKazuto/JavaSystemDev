<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
<br>



<div class="border mx-3 mb-3 py-3 px-3 rounded">


    <form action="TestListSubjectExecute.action" method="get">
        <div class="row align-items-center mb-3">

            <label class="col-2" style="padding-top:20px;"><p>科目情報</p></label>

            <div class="col-2">
                <label class="form-label">入学年度</label>
                <select class="form-select" name="f1">
                    <option value="">--------</option>
                    <c:forEach var="year" items="${entyearset}">
                        <option value="${year}">${year}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-2">
                <label class="form-label">クラス</label>
                <select class="form-select" name="f2">
                    <option value="">--------</option>
                    <c:forEach var="cla" items="${classlist}">
                        <option value="${cla}">${cla}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-3">
                <label class="form-label">科目</label>
                <select class="form-select" name="f3">
                    <option value="">--------</option>
                    <c:forEach var="subject" items="${subjectlist}">
                        <option value="${subject.cd}">${subject.name}</option>
                    </c:forEach>
                </select>
            </div>
            
            <input type="hidden" name="sj" value="${subject.cd}">

            <div class="col-3 text-center">
                <button class="btn btn-secondary">検索</button>
            </div>

        </div>

        <c:if test="${not empty claerror}">
            <div class="text-warning">入学年度とクラスと科目を選択してください</div>
        </c:if>
        
    </form>

	<%--二つの情報を仕切る線 --%>
    <div class="mx-auto my-3" style="width:90%; border-bottom:1px solid #ccc;"></div>

    <form action="TestListStudentExecute.action" method="get">
        <div class="row align-items-center">

            <label class="col-2" style="padding-top:20px;"><p>学生情報</p></label>

            <div class="col-4">
                <label class="form-label">学生番号</label>
                <c:choose>
                    <c:when test="${not empty f4}">
                        <input type="text" name="f4" value="${f4}" class="form-control" maxlength="10" required>
						<input type="hidden" name="st">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください" maxlength="10" required>
                    </c:otherwise>
                </c:choose>
            </div>
			
			<input type="hidden" name="st" value="${student.id}">
			
            <div class="col-3 text-center">
                <button class="btn btn-secondary">検索</button>
            </div>

        </div>
    </form>

</div>

<label style="color:#0dcaf0; margin: 8px 16px;">
	<p> 科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
</label>

     


</c:param>
</c:import>