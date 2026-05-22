<%--//制作者 内田 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">教員管理</h2>
            
            <div class="d-flex justify-content-between align-items-center mb-3 px-4">
                <div class="fs-5">教員一覧</div>
                <c:if test="${user.mode.role == '1' or user.mode.role == '2'}">
                	<div>
                    	<a href="TeacherCreate.action" class="btn btn-outline-primary btn-sm">新規登録</a>
                	</div>
                </c:if>
            </div>

            <div class="px-4">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>教員ID</th>
                            <th>氏名</th>
                            <th>所属学校</th>
                            <th>役職</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="teacher" items="${teachers}">
                            <tr>
                                <td>${teacher.id}</td>
                                <td>${teacher.name}</td>
                                <td>${teacher.school.cd}</td>
                                <td>${teacher.role.name}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <%-- ログイン中のユーザー（自分）の場合 --%>
                                        <c:when test="${teacher.id == user.id}">
                                            <span class="text-muted small">ログイン中</span>
                                            <a href="TeacherUpdate.action" class="ms-2 text-primary text-decoration-underline">変更</a>
                                        </c:when>
                                        
                                        <c:when test="${user.mode.role == '1' or user.mode.role == '2'}">
                                        	<c:if test="${(teacher.role.role != '1') or (user.mode.role == '1')}">
                                            	<a href="TeacherRoleUpdate.action?teacher_id=${teacher.id}" class="text-primary text-decoration-underline">役職の変更</a>	
	                                            <a href="TeacherDelete.action?id=${teacher.id}" class="text-primary text-decoration-underline"  style="margin-left: 30px;">削除</a>
	                                        </c:if>
                                        </c:when>
                                        
                                        <c:otherwise>  </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </c:param>
</c:import>