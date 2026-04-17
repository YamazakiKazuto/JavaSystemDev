<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>科目削除</h2>

    <c:if test="${not empty errors}">
        <div class="error-messages">
            <c:forEach var="error" items="${errors}">
                <p style="color:red;">${error.value}</p>
            </c:forEach>
        </div>
    </c:if>

    <div class="confirmation-box">
        <p>以下の科目を削除します。よろしいですか？</p>

        <table border="1">
            <tr>
                <th>科目コード</th>
                <td>${subject.cd}</td>
            </tr>
            <tr>
                <th>科目名</th>
                <td>${subject.name}</td>
            </tr>
        </table>

        <div class="actions" style="margin-top: 20px;">
         
            <form action="SubjectDeleteExecute.action" method="post" style="display: inline;">
                
                <input type="hidden" name="cd" value="${subject.cd}">
                
                <input type="submit" value="削除" class="btn-delete">
            </form>
           <a href="SubjectList.action" class="btn-back" style="margin-left: 15px;">戻る</a>
        </div>
    </div>
</div>
