<%--//制作者　石川 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="/scoremanager/main/Login.action" />
</c:if>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
    <section class="me-4">
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
        メニュー
        
        <form action="ModeChange.action" method="post">
        <select class="form-select" id="student-f1-select" name="role_num">

        <c:forEach var="role" items="${role_list}">
        <option value="${role.role}" <c:if test="${user.mode.role==role.role}">selected</c:if>>${role.name}モード</option>
        </c:forEach>
    	</select>
    	<button type="submit" class="btn btn-secondary">モード変更</button>
    	</form>
        </h2>

	<c:if test="${not empty mode_changed}"> 
	<label style="color:#0dcaf0;">
		<p>${mode_changed }</p>
	</label>
	</c:if>
	<c:if test="${not empty becareful}"> 
	<div class="alert alert-danger">
	${becareful}
	<p>気を付けて操作を行ってください</p>
	</div>
	</c:if>
	
	
        <div class="row text-center px-4 fs-3 my-5">

            <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
                 style="height: 10rem; background-color: #e6c7c7;">
                <a href="StudentList.action">学生管理</a>
            </div>

            <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
                 style="height: 10rem; background-color: #bfe3bf;">
                <div>
                    <div>成績管理</div>
                    <div>
                        <a href="TestRegist.action">成績登録</a>
                    </div>
                    <div>
                        <a href="TestList.action">成績参照</a>
                    </div>
                </div>
            </div>

            <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
                 style="height: 10rem; background-color: #c8c9e6;">
                <a href="SubjectList.action">科目管理</a>
            </div>

            <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
                 style="height: 10rem; background-color: #d6d6e8;">
                <a href="ClassCounts.action">クラス管理</a>
            </div>
            <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
                style="height: 10rem; background-color: #f0f0f0;">
                <%-- リンク先（Action名）は必要に応じて作成してください --%>
                <a href="LoginManage.action" class="text-decoration-none">教員管理</a>
            </div>

        </div>
    </section>
</c:param>

</c:import>