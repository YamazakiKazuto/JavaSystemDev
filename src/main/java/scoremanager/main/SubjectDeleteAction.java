package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        String cd = request.getParameter("cd");
        
        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        Subject subject = null;
        if (teacher != null && cd != null) {
                   subject = sDao.get(cd, teacher.getSchool().getCd());
        }

       
        if (subject != null) {
            
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
        } else {
           
            errors.put("not_found", "削除対象の科目が見つかりませんでした。");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("SubjectList.action").forward(request, response);
        }
    }
}