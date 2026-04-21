package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
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
    	  
//        School school = (School) request.getSession().getAttribute("school");


          String no = request.getParameter("no");

//        // Subject取得
//        SubjectDao subjectDao = new SubjectDao();
//        Subject subject = subjectDao.get(subjectCd, school);
//
//        // 成績一覧取得
//        TestDao testDao = new TestDao();
//        List<Test> list =
//            testDao.filter(entYear, classNum, subject, num, school);
        String entYear = request.getParameter("ent_year");
        String classnum = request.getParameter("classNum");
        
        String subjectCd = request.getParameter("subjectCd");
        
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
       
        if (tescla != null && tescla.isEmpty()) {
            request.setAttribute("error", "学生情報が存在しませんでした");
            session.removeAttribute("classList");
            session.removeAttribute("subjectlist");
            request.getRequestDispatcher("test_list_subject.jsp")
                   .forward(request, response);
      
        }
        request.setAttribute("tescla", tescla);
        session.removeAttribute("classList");
        session.removeAttribute("subjectlist");
        
        request.getRequestDispatcher("test_list_subject.jsp")
        .forward(request, response);
    }
}
