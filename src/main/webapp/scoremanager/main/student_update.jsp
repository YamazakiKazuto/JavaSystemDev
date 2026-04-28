<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">


<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

<form action="StudentUpdateExecute.action" method="post">

	    <!-- 入学年度 -->
    <div>
        <label>入学年度</label>
        <p>　${student.entYear}</p>
        <input type="hidden" name="entYear" value="${student.entYear}">
    </div>
    
	
    <!-- 学籍番号 -->
    <div>
        <label>学生番号</label>
            <p>　${student.no}</p>
            <input type="hidden" name="no" value="${student.no}">
    </div>


    <!-- 氏名 -->
    <div>
        <label>氏名</label>
        <div>
            <input type="text" name="name" class="form-control" value="${student.name}" required>
        </div>
    </div>
    
	<br>
    
    <!-- クラス -->
    <div>
        <label>クラス</label>
        <select name="class_num" class="form-select">
            <c:forEach var="cn" items="${classnum}">
                <option value="${cn}"
                    <c:if test="${cn == student.classNum}">
                        selected
                    </c:if>>
                    ${cn}
                </option>
            </c:forEach>
        </select>
    </div>
	<br>
    <!-- 在学状況 -->
    <div>
        <label>在学中</label>
        <div class="form-check form-check-inline">
            <input type="checkbox" name="is_enrolled" value="true"
                <c:if test="${student.attend}">checked</c:if>
        </div>
    </div>

    <!-- ボタン -->
    <br>
    <br>
   
    
    <div>
        
        <button type="submit" class="btn btn-primary">
            変更
        </button>
        <br>
        <br>
        <a href="StudentList.action" class="ms-3">
            戻る
        </a>
    </div>

</form>

</c:param>
</c:import>