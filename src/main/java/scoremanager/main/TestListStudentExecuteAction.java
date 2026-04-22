package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
    	  
//        School school = (School) request.getSession().getAttribute("school");
//
//        int entYear = Integer.parseInt(request.getParameter("entYear"));
//        String classNum = request.getParameter("classNum");
//        String subjectCd = request.getParameter("subjectCd");
          String no = request.getParameter("no");

//        // Subject取得
//        SubjectDao subjectDao = new SubjectDao();
//        Subject subject = subjectDao.get(subjectCd, school);
//
//        // 成績一覧取得
//        TestDao testDao = new TestDao();
//        List<Test> list =
//            testDao.filter(entYear, classNum, subject, num, school);
        School school = new School();
        school.setCd(user.getSchool().getCd());
        
        Student student = new Student();
        student.setNo(no);
        student.setSchool(school);
        
        TestListStudentDao teststuDao = new TestListStudentDao();
        List<TestListStudent> tesstu = teststuDao.filter(student);
        
        StudentDao dao = new StudentDao();
    	Student stu = dao.get(no);
        request.setAttribute("studentone",stu);
        
        if (tesstu != null && tesstu.isEmpty()) {
        	
        	request.setAttribute("stuerror", "成績情報が存在しませんでした");
            request.getRequestDispatcher("test_list_student.jsp")
                   .forward(request, response);

        }
        request.setAttribute("tesstu", tesstu);

        request.getRequestDispatcher("test_list_student.jsp")
        .forward(request, response);
    }
}
