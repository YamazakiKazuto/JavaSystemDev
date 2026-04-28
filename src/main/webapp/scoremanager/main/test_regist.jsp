<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:if test="${empty sessionScope.user}">
    <c:redirect url="/scoremanager/main/Login.action" />
</c:if>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<body class="border rounded p-3">

<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

<!-- エラーメッセージ -->

<c:if test="${not empty errors}"> <div class="alert alert-danger">
<c:forEach var="error" items="${errors}"> <div>${error}</div>
</c:forEach> </div>
</c:if>

<!-- 検索フォーム -->

<form action="TestRegist.action" method="post" class="card p-3 mb-4">


<div class="row g-3">

    <!-- 入学年度 -->
    <div class="col-md-2">
        <label class="form-label">入学年度</label>
        <select name="ent_year" class="form-select">
            <option value="">--------</option>
            <c:forEach var="year" items="${ent_years}">
                <option value="${year}" ${year == ent_year ? 'selected' : ''}>${year}</option>
            </c:forEach>
        </select>
    </div>

    <!-- クラス -->
    <div class="col-md-2">
        <label class="form-label">クラス</label>
        <select name="class_num" class="form-select">
            <option value="0">--------</option>
            <c:forEach var="cn" items="${class_nums}">
                <option value="${cn}" ${cn == class_num ? 'selected' : ''}>${cn}</option>
            </c:forEach>
        </select>
    </div>

    <!-- 科目 -->
    <div class="col-md-3">
        <label class="form-label">科目</label>
        <select name="subject_code" class="form-select">
            <option value="">--------</option>
            <c:forEach var="sub" items="${subjects}">
                <option value="${sub.cd}" ${sub.cd == subject_code ? 'selected' : ''}>${sub.name}</option>
            </c:forEach>
        </select>
    </div>

    <!-- 回数 -->
    <div class="col-md-2">
        <label class="form-label">回数</label>
        <select name="no" class="form-select">
            <option value="">--------</option>
            <c:forEach var="i" begin="1" end="10">
                <option value="${i}" ${i == no ? 'selected' : ''}>${i}</option>
            </c:forEach>
        </select>
    </div>

    <!-- ボタン -->
    <div class="col-md-2 d-flex align-items-end">
	    <button type="submit" class="btn btn-secondary w-100">
	        検索
	    </button>
	</div>


</form>

<!-- 件数表示 -->

<c:if test="${tests != null}"> <p>件数：${fn:length(tests)}</p>
</c:if>

<!-- 成績入力テーブル -->

<c:if test="${tests != null}">

<form action="TestRegistExecute.action" method="post">


<input type="hidden" name="ent_year" value="${ent_year}">
<input type="hidden" name="class_num" value="${class_num}">
<input type="hidden" name="subject_code" value="${subject_code}">
<input type="hidden" name="no" value="${no}">

<table class="table table-bordered mt-3">
    <thead>
        <tr>
            <th>入学年度</th>
            <th>クラス</th>
            <th>学籍番号</th>
            <th>氏名</th>
            <th>点数</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="t" items="${tests}">
            <tr>
                <td>${ent_year}</td>
                <td>${class_num}</td>

                <td>
                    ${t.student.no}
                    <input type="hidden" name="student_no" value="${t.student.no}">
                </td>

                <td>${t.student.name}</td>

                <td>
                    <input type="number"
                           name="point"
                           value="${t.point}"
                           class="form-control"
                           min="0" max="100"
           				   required          				   
						   oninvalid="this.setCustomValidity('0～100の値で入力してください')"
           				   oninput="this.setCustomValidity('')">
           				                         
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="mt-3">
    <button type="submit" class="btn btn-success">登録して終了</button>
    <a href="menu.jsp" class="btn btn-secondary">戻る</a>
</div>

</form>
</c:if>

<!-- データなし -->

<c:if test="${tests != null and empty tests}"> <div class="alert alert-warning mt-3">
該当する学生が見つかりません </div>
</c:if>

</c:param>
</c:import>
