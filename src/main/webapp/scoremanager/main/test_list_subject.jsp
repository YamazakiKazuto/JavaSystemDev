<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>

<form action="TestListSubjectExecute.action" method="get">
	<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
		<div class="col-2"><h6>科目情報</h6></div>
		
    	<div class="col-2">
        	<label class="form-label">入学年度</label>
            	<select class="form-select" name="entYear">
                    <option value="">--------</option>
                    <c:forEach var="year" items="${entyearset}">
                    <%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
                	    <option value="${year}">${year}</option>
                    </c:forEach>
                </select>
         </div>
         <div class="col-2">
            <label class="form-label">クラス</label>
         	   <select class="form-select" name="classCd">
            	   <option value="">--------</option>
                   <c:forEach var="cla" items="${classlist}">
                   <%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
                	   <option value="${cla}">${cla}</option>
                   </c:forEach>
                </select>
         </div>
         <div class="col-3">
   		     <label class="form-label">科目</label>
        	     <select class="form-select" name="subjectCd">
            	     <option value="">--------</option>
                	     <c:forEach var="subject" items="${subjectlist}">
                         <%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
                      	    <option value="${subject.cd}">${subject.name}</option>
                   	     </c:forEach>
                 </select>
         </div>
         <div class="col-3 text-center">
  		     <button class="btn btn-secondary" id="filter-button">検索</button>
         </div>
         <c:if test="${not empty claerror}"> 
        	
			<div style="color:#ff9933;">
   				${claerror }
			</div>
        	
		 </c:if>
    </div>
</form>
 

 <form action="TestListStudentExecute.action" method="get">
	<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
    	<div class="col-2"><h6>学生情報</h6></div>
    	<div class="col-4">
  			<label class="form-label">学生番号</label>
  			<input type="text" name="no" class="form-control" required>
		</div>
    	
        
        <div class="col-3 text-center">
  		    <button class="btn btn-secondary" id="filter-button">検索</button>
        </div>
     
    </div>
</form>


<p>科目　:　${subjectone.name }</p>
<c:choose>

<c:when test="${not empty error}">
	<p>${error }</p>        
</c:when>

<c:otherwise>
    <table  class="table table-hover" ">
        <tr>
        <th>入学年度</th>
        <th>クラス</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>１回</th>
        <th>２回</th>
        </tr>

        <c:forEach var="t" items="${tescla}">
    <c:if test="${t.no == 1}">
        <tr>
            <td>${t.student.entYear}</td>
            <td>${t.classNum}</td>
            <td>${t.student.no}</td>
            <td>${t.student.name}</td>
            <td>${t.point}</td>

            <!-- 2回目の点数を探す -->
            <td>
                <c:set var="second" value="-" />

                <c:forEach var="u" items="${tescla}">
                    <c:if test="${u.student.no == t.student.no 
                                 and u.classNum == t.classNum
                                 and u.no == 2}">
                        <c:set var="second" value="${u.point}" />
                    </c:if>
                </c:forEach>

                ${second}
            </td>
        </tr>
    </c:if>
</c:forEach>
    </table>
</c:otherwise>

</c:choose>

</c:param>
</c:import>
