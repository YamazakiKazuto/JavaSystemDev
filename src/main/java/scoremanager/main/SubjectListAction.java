package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        // --- 1. ログインチェック（Teacher をセッションから取得） ---
        Teacher teacher = (Teacher) session.getAttribute("user");


        // --- 2. パラメータ取得（科目は絞り込み条件が少ない） ---
        String isAttendStr = request.getParameter("f1");  // 在学フラグ（必要なら）
        boolean isAttend = (isAttendStr != null);

        List<Subject> subjects = null;

        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();
        subjects = sDao.filter(teacher.getSchool());
        

        // --- 4. セット ---
        request.setAttribute("f1", isAttendStr);
        request.setAttribute("subjects", subjects);
        request.setAttribute("errors", errors);

        // --- 5. フォワード ---
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
