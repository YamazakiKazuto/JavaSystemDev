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

public class SubjectUpdateAction extends Action {

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
        String mode = request.getParameter("mode");   // "edit" or "update"

        SubjectDao sDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        Subject subject = null;

        // --- 3. 編集画面表示（mode=edit） ---
        if (mode == null || mode.equals("edit")) {

            if (cd != null && !cd.isEmpty()) {
                subject = sDao.get(cd);
            }

            if (subject == null) {
                subject = new Subject();
                subject.setCd(cd);
            }

            request.setAttribute("subject", subject);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // --- 4. 更新処理（mode=update） ---
        if (mode.equals("update")) {

            // 入力チェック
            if (cd == null || cd.isEmpty()) {
                errors.put("cd", "科目コードを入力してください");
            }
            if (name == null || name.isEmpty()) {
                errors.put("name", "科目名を入力してください");
            }

            if (!errors.isEmpty()) {
                subject = new Subject();
                subject.setCd(cd);
                subject.setName(name);
                subject.setSchoolCd(teacher.getSchool().getCd());

                request.setAttribute("subject", subject);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("subject_update.jsp").forward(request, response);
                return;
            }

            // 更新用 Subject 作成
            subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchoolCd(teacher.getSchool().getCd());

            // DB 保存
            boolean result = sDao.save(subject);

            if (!result) {
                errors.put("system", "更新に失敗しました");
                request.setAttribute("subject", subject);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("subject_update.jsp").forward(request, response);
                return;
            }

            // 完了画面へ
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        }
    }
}
