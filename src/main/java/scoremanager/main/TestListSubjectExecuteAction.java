package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
    	  
        String entYear = request.getParameter("f1");
        String classnum = request.getParameter("f2");
        
        String subjectCd = request.getParameter("f3");
        
        boolean isAnyNull =
        	    (entYear == null || entYear.isEmpty()) ||
        	    (classnum == null || classnum.isEmpty()) ||
        	    (subjectCd == null || subjectCd.isEmpty());

        if (isAnyNull) {
        	request.setAttribute("claerror", "入学年度とクラスと科目を入力してください");
            request.getRequestDispatcher("test_list.jsp")
                   .forward(request, response);
   
        }
        int entyear = Integer.parseInt(entYear);
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        
        School school = user.getSchool();
                
        
        TestListSubjectDao testclaDao = new TestListSubjectDao();
        List<TestListSubject> tescla = testclaDao.filter(entyear,classnum,subject,school);
       
        SubjectDao dao = new SubjectDao();
        Subject sub = dao.get(subjectCd,user.getSchool());
        request.setAttribute("subjectone",sub);
        request.setAttribute("f1",entYear);
        request.setAttribute("f2",classnum);
        request.setAttribute("f3",sub.getCd());
        request.setAttribute("resubjectName",sub.getName());
        
        if (tescla != null && tescla.isEmpty()) {
            request.setAttribute("error", "学生情報が存在しませんでした");

            request.getRequestDispatcher("test_list_subject.jsp")
                   .forward(request, response);
      
        }
        request.setAttribute("tescla", tescla);

        
        request.getRequestDispatcher("test_list_subject.jsp")
        .forward(request, response);
    }
}
