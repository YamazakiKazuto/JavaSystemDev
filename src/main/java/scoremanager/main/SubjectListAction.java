package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
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

        if (teacher == null) {
            teacher = new Teacher();
            School school = new School();
            school.setCd("tes");   // ← 実在する学校コードに変更
            teacher.setSchool(school);
            session.setAttribute("user", teacher);
        }

        // --- 2. パラメータ取得（科目は絞り込み条件が少ない） ---
        String isAttendStr = request.getParameter("f1");  // 在学フラグ（必要なら）
        boolean isAttend = (isAttendStr != null);

        List<Subject> subjects = null;

        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        // --- 3. データ取得（SubjectDao に合わせて filter を呼ぶ） ---
        if (teacher != null) {
            subjects = sDao.filter(teacher.getSchool().getCd());
        }

        // --- 4. セット ---
        request.setAttribute("f1", isAttendStr);
        request.setAttribute("subjects", subjects);
        request.setAttribute("errors", errors);

        // --- 5. フォワード ---
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
