<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
<c:param name="title">
    得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">

<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>

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

<p>氏名　:　${studentone.name }（ ${studentone.no } ）</p>

<c:if test="${not empty stuerror}"> 	
   	<p>${stuerror }</p>
</c:if>




</c:param>
</c:import>
