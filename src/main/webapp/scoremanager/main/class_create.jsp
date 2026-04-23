<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生新規登録</h2>
            
            <p class="px-4">新しく追加する学生情報を入力し登録ボタンを押してください。</p>

            <%-- エラーメッセージがある場合のみ赤文字で表示 --%>
            <c:if test="${not empty error}">
                <div class="text-danger mb-3 px-4">
                    <strong>※ ${error}</strong>
                </div>
            </c:if>

            <form action="ClassCreateExecute.action" method="post" class="px-4">
                
                <%-- 入学年度：プルダウン形式 --%>
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <select name="ent_year" class="form-select" style="width:200px;">
                        <option value="">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <%-- 登録失敗時に選択していた年度を保持 --%>
                            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>
                                ${year}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <%-- 学生番号：テキスト（maxlength="3"で入力制限） --%>
                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="no" value="${no}" maxlength="3" 
                           class="form-control" style="width:200px;" placeholder="数字3桁を入力">
                </div>

                <%-- 氏名：テキスト --%>
                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" value="${name}" 
                           class="form-control" style="width:400px;" placeholder="氏名を入力してください">
                </div>

                <%-- クラス：プルダウン形式 --%>
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select name="classnum" class="form-select" style="width:200px;">
                        <option value="">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <%-- 登録失敗時に選択していたクラスを保持 --%>
                            <option value="${num}" <c:if test="${num == classnum}">selected</c:if>>
                                ${num}
                            </option>
                        </c:forEach>            
                    </select>
                </div>

                <%-- ボタンエリア --%>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">登録</button>
                    <a href="StudentList.action" class="btn btn-secondary ms-2">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>