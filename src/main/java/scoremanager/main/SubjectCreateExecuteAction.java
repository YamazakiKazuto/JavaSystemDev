package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. パラメータの取得
        String subjectCd = request.getParameter("cd");
        String subjectName = request.getParameter("name");

        // 2. セッションから講師情報を取得（学校コード特定のため）
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        SubjectDao sDao = new SubjectDao();
        String url = "";

        // --- バリデーション開始 ---

        // 【チェック1】未入力チェック
        if (subjectCd == null || subjectCd.isEmpty() || subjectName == null || subjectName.isEmpty()) {
            request.setAttribute("error", "科目コードと科目名を入力してください");
            url = "subject_create.jsp";
        } 
        // 【チェック2】文字数チェック（設計書の「3文字でなかった場合」）
        else if (subjectCd.length() != 3) {
            request.setAttribute("error", "科目コードは3文字で入力してください");
            url = "subject_create.jsp";
        } 
        // 【チェック3】重複チェック（設計書の「重複していた場合」）
//        else if (sDao.isDuplicate(subjectCd, schoolCd)) {
//            request.setAttribute("error", "科目コードが重複しています");
//            url = "subject_create.jsp";
//        }

        Subject existing = sDao.get(subjectCd);

        if (existing != null) {
            request.setAttribute("error", "科目コードが重複しています。");
            request.getRequestDispatcher("subject_create.jsp")
                   .forward(request, response);
            return;
        }
        // --- 全チェック通過 ---
        else {
            // DBに保存を実行
        	Subject subject = new Subject();
        	subject.setCd(subjectCd);
        	subject.setName(subjectName);
        	subject.setSchoolCd(user.getSchool().getCd());
            sDao.save(subject);
            url = "subject_create_done.jsp";
        }

        // 入力内容を保持（エラーで戻った時に再入力させないため）
        request.setAttribute("cd", subjectCd);
        request.setAttribute("name", subjectName);

        // 指定のJSPへフォワード
        request.getRequestDispatcher(url).forward(request, response);
    }
}