<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-body text-center">
                <h2 class="text-success mb-4">登録が完了しました</h2>
                
                <p class="mb-4">
                    新しい科目の登録が正常に終了しました。<br>
                    登録した内容は科目一覧画面から確認できます。
                </p>

                <%-- 次の画面（一覧）へ戻るボタン --%>
                <div class="d-grid gap-2 d-md-block">
                    <a href="SubjectList.action" class="btn btn-primary px-5">
                        科目一覧へ戻る
                    </a>
                </div>
            </div>
        </div>
    </div>

</c:param>
</c:import>