
package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        String cd=request.getParameter("cd");
        
        SubjectDao subdao =new SubjectDao();
        //daoでidに合致する番号をとる
        Subject subject=subdao.get(cd,user.getSchool());
        request.setAttribute("subject",subject);
        
        request.getRequestDispatcher("subject_update.jsp")
        .forward(request, response);
    }
}
