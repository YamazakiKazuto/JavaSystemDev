<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 1. JSTLを使用するために必須の宣言 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<section>

	<c:if test="${not empty user}">
		    <div class="nav align-self-end">
		        <%-- .name と書けば、内部で getName() が呼ばれます --%>
		        <span class="nav-item px-2">${user.name}様</span>
		        <a class="nav-item px-2" href="Logout.action">ログアウト</a>
		    </div>
		</c:if>
    <main>
        <h2 class=" h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4 ">ログアウト</h2>
		<div style="background-color: #8fc1a9; color: #034732; padding: 8px 16px;
    		border-radius: 4px; text-align: center; ">
    		ログアウトしました
		</div>
        
        <br>
        <br>
        <p><a href="Login.action">ログイン</a></p>
    </main>

    </section>

</c:param>
</c:import>