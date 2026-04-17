package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 科目登録画面を表示するアクション
 */
public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // このアクションは、単に登録用のJSPを表示するだけなので
        // 特殊なビジネスロジック（DB取得など）は不要です。
        
        // 遷移先のJSPを指定してフォワード
        request.getRequestDispatcher("subject_create.jsp").forward(request, response);
    }
}