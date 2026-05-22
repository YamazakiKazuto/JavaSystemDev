<%--//制作者 山﨑 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
 
    <c:param name="content">
        <section style="text-align:center; margin-top:40px;">
 
            
            <p style="margin-bottom:30px;">
                エラーが発生しました
            </p> 
 			<p><strong>メッセージ：</strong> ${errorMessage}</p>

<pre>
<%
    // スタックトレースを表示したい場合
    Exception ex = (Exception) request.getAttribute("errorMessage");
    if (ex != null) {
        ex.printStackTrace(new java.io.PrintWriter(out));
    }
%>
</pre>
        </section>
    </c:param>
</c:import>