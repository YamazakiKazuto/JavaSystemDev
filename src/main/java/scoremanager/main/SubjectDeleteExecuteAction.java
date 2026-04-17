package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        Teacher teacher = (Teacher) session.getAttribute("user");
        
        String cd = request.getParameter("cd");
        
        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

      
        if (teacher != null && cd != null) {
            
            try {
                boolean isSuccess = sDao.delete(cd, teacher.getSchool().getCd());
                
                if (!isSuccess) {
                    errors.put("delete_failed", "削除に失敗しました。対象のデータが存在しません。");
                }
            } catch (Exception e) {
                e.printStackTrace();
                errors.put("error", "システムエラーが発生しました。");
            }
        }
        if (errors.isEmpty()) {
            request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
        } else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("SubjectList.action").forward(request, response);
        }
    }
}