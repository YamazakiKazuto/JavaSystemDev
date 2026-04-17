<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録完了 - 得点管理システム</title>
    <%-- 必要に応じてBootstrapなどのCSSを読み込んでください --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%-- 共通ヘッダーの取り込み --%>
    <c:import url="/common/header.jsp" />

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

    <%-- 共通フッターの取り込み（もしあれば） --%>
    <c:import url="/common/footer.jsp" />
</body>
</html>