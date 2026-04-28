package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからログインユーザー情報を取得（学校コード利用のため）
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        TeacherDao tDao = new TeacherDao();
        // 1. IDの重複チェック
        Teacher check = tDao.get(id);

        if (check == null) {
            // 2. 重複がなければ新しい教員Beanを作成して保存
            Teacher newTeacher = new Teacher();
            newTeacher.setId(id);
            newTeacher.setName(name);
            newTeacher.setPassword(password);
            newTeacher.setSchool(user.getSchool()); // ログインユーザーと同じ学校をセット

            // データベースに保存
            tDao.save(newTeacher);
            
            // 登録完了後、一覧画面へリダイレクト
            response.sendRedirect("LoginManage.action");
        } else {
            // 3. IDが重複している場合のエラー処理
            // エラーメッセージをリクエスト属性にセット
            request.setAttribute("error", "この教員IDはすでに登録されています。");
            
            // 入力内容を保持するためにリクエストに値を残す（任意）
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            
            // 登録画面（teacher_create.jsp）にフォワードして戻る
            request.getRequestDispatcher("teacher_create.jsp").forward(request, response);
        }
    }
}