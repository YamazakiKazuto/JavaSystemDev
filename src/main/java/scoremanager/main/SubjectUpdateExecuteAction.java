package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

        // リクエストパラメータ取得
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("user");
        String school_cd= teacher.getSchool().getCd();
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd,school_cd);

        // 更新対象のみセット
        subject.setName(name);

        // 更新実行
        dao.save(subject);
        
        request.setAttribute("subject",subject);
        request.getRequestDispatcher("subject_update_done.jsp")
        .forward(request, response);
    }
}