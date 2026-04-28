package scoremanager.main;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class StudentCreateAction extends Action {
 
    @Override
 
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        HttpSession session = request.getSession();
        Teacher user =(Teacher)session.getAttribute("user");
        
//        StudentDao studao =new StudentDao();
//        //daoでidに合致する番号をとる
//        Student student=studao.get(stu_id);
        ClassNumDao cladao =new ClassNumDao();
        //daoでidに合致する番号をとる
        List<String> classnum=cladao.filter(user.getSchool());
        
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year + 1; i++) {
            entYearSet.add(i);
        }
        
//        request.setAttribute("student",student);
        session.setAttribute("classnum",classnum);
        session.setAttribute("ent_year_set", entYearSet);
        
        request.getRequestDispatcher("student_create.jsp")
        .forward(request, response);
    }   
}
 