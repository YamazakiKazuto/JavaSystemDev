//制作者　勝見
package scoremanager.main;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	HttpSession session = request.getSession();
        // セッションからログインユーザー取得
        Teacher user = (Teacher) session.getAttribute("user");
        
        String student_no = request.getParameter("no");
        String subject_cd = request.getParameter("subCd");
        int no = Integer.parseInt(request.getParameter("num"));
        
        Test test = new Test();
        
        StudentDao sdao =new StudentDao(); 
        Student student = sdao.get(student_no);
        test.setStudent(student);
        
        SubjectDao subdao =new SubjectDao(); 
        Subject subject = subdao.get(subject_cd,user.getSchool());
        test.setSubject(subject);
        
        test.setNo(no);
        
        
        // 教員情報をリクエストセットして確認画面へ
        session.setAttribute("test", test);
        request.getRequestDispatcher("test_delete.jsp").forward(request, response);
    }
}