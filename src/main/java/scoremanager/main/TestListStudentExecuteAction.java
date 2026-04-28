package scoremanager.main;

import java.util.List;

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
    	  
        String no = request.getParameter("f4");

        StudentDao dao = new StudentDao();
    	Student stu = dao.get(no);
    	
    	request.setAttribute("f4",no);       

    	//DBに登録されていない学生番号、もしくは入力された学生番号がユーザの学校所属の者ではない
    	if (stu == null || (!stu.getSchool().getCd().trim().equals(user.getSchool().getCd().trim()))) {
    	    request.setAttribute("stuerror", "成績情報が存在しませんでした");
    	    request.getRequestDispatcher("test_list_student.jsp")
    	           .forward(request, response);
    	    return;
    	}
    	
        TestListStudentDao teststuDao = new TestListStudentDao();
        List<TestListStudent> tesstu = teststuDao.filter(stu);  
        

    	request.setAttribute("studentone",stu);        

    	//入力された学生番号はユーザの学校所属の者だが登録されたテストデータがない場合
        if (tesstu == null || tesstu.isEmpty()) {
            request.setAttribute("studeerror", "成績情報が存在しませんでした");
            request.getRequestDispatcher("test_list_student.jsp")
                   .forward(request, response);
            return;
        }

        request.setAttribute("tesstu", tesstu);

        request.getRequestDispatcher("test_list_student.jsp")
        .forward(request, response);
    }
}
