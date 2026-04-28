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
        System.out.println(subjectCd);

        // 2. セッションから講師情報を取得（学校コード特定のため）
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        SubjectDao sDao = new SubjectDao();

        // --- バリデーション開始 ---

        // 入力内容を保持（エラーで戻った時に再入力させないため）
        request.setAttribute("return_cd", subjectCd);
        request.setAttribute("return_name", subjectName);
        
        // 【チェック2】文字数チェック（設計書の「3文字でなかった場合」）
        if (subjectCd.length() != 3 ) {
            request.setAttribute("cd_error", "科目コードは3文字で入力してください");
            request.getRequestDispatcher("subject_create.jsp")
            .forward(request, response);
     return;
        } 
        // 【チェック3】重複チェック（設計書の「重複していた場合」）
//        else if (sDao.isDuplicate(subjectCd, schoolCd)) {
//            request.setAttribute("error", "科目コードが重複しています");
//            url = "subject_create.jsp";
//        }

        Subject existing = sDao.get(subjectCd,user.getSchool());
        System.out.println(existing);
        if (existing != null) {
            request.setAttribute("cd_error", "科目コードが重複しています。");
            request.getRequestDispatcher("subject_create.jsp")
                   .forward(request, response);
            return;
        }
        // DBに保存を実行
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        subject.setName(subjectName);
        subject.setSchool(user.getSchool());
        sDao.save(subject);
        


        // 指定のJSPへフォワード
        request.getRequestDispatcher("subject_create_done.jsp")
        			.forward(request, response);
    }
}