package scoremanager.main;
 
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class StudentUpdateAction extends Action {
 
    @Override
 
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        HttpSession session = request.getSession();
        Teacher user =(Teacher)session.getAttribute("user");
        String stu_id=request.getParameter("no");
        StudentDao studao =new StudentDao();
        //daoでidに合致する番号をとる
        Student student=studao.get(stu_id);
        ClassNumDao cladao =new ClassNumDao();
        //daoでidに合致する番号をとる
        List<String> classnum=cladao.filter(student.getSchool());
        request.setAttribute("student",student);
        request.setAttribute("classnum",classnum);
        request.getRequestDispatcher("student_update.jsp")
        .forward(request, response);
    }   
}
 