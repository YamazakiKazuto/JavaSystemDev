package scoremanager.main;

import java.util.Map;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCountsAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. セッションから教員情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 2. DAOを使って集計データを取得
        ClassNumDao sDao = new ClassNumDao();
        Map<String, Integer> counts = sDao.countStudentsByClass(teacher.getSchool());

        // 3. JSPで表示するためにリクエスト属性にセット
        request.setAttribute("classCounts", counts);

        // 4. JSPへフォワード
        // ※この名前のJSPファイルをWebContent(webapp)直下に作成してください
        request.getRequestDispatcher("class_counts.jsp").forward(request, response);
    }
}