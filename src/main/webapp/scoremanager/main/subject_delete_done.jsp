<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<div class="container" style="text-align: center; margin-top: 50px;">
    <div class="done-box" style="border: 1px solid #ccc; padding: 30px; display: inline-block; border-radius: 10px;">
        <h2 style="color: #2c3e50;">削除完了</h2>
        
        <p style="margin: 20px 0; font-size: 1.1em;">
            科目の削除が正常に完了しました。
        </p>

        <div class="actions" style="margin-top: 30px;">
            <a href="SubjectList.action" 
               style="text-decoration: none; background-color: #3498db; color: white; padding: 10px 20px; border-radius: 5px;">
               科目一覧へ戻る
            </a>
        </div>
    </div>
</div>

</c:param>
</c:import>