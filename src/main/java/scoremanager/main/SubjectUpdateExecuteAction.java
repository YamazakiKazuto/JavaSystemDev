
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

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        // --- 1. ログインチェック ---
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // --- 2. パラメータ取得 ---
        String cd = request.getParameter("cd");       // 科目コード
        String name = request.getParameter("name");   // 科目名

        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        // --- 3. 入力チェック ---
        if (cd == null || cd.isEmpty()) {
            errors.put("cd", "科目コードを入力してください");
        }
        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        // エラーがある場合は戻す
        if (!errors.isEmpty()) {
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchoolCd(teacher.getSchool().getCd());

            request.setAttribute("subject", subject);
            request.setAttribute("errors", errors);

            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // --- 4. 更新処理 ---
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchoolCd(teacher.getSchool().getCd());

        boolean result = sDao.save(subject);

        if (!result) {
            errors.put("system", "科目情報の更新に失敗しました");
            request.setAttribute("subject", subject);
            request.setAttribute("errors", errors);

            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // --- 5. 完了画面へ ---
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    }
}
